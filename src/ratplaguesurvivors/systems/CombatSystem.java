package ratplaguesurvivors.systems;

import java.util.List;

import ratplaguesurvivors.entity.npc.BossAnimationController;
import ratplaguesurvivors.entity.npc.Enemy;
import ratplaguesurvivors.entity.npc.EnemySpawner;
import ratplaguesurvivors.entity.npc.EnemyType;
import ratplaguesurvivors.entity.pc.PlayableCharacter;
import ratplaguesurvivors.hud.HUDComponent;
import ratplaguesurvivors.input.Directions;
import ratplaguesurvivors.interfaces.Collidable;
import ratplaguesurvivors.interfaces.CombatEventListener;
import ratplaguesurvivors.map.Map;

/**
 * Resolves per-tick physics and combat: player movement against
 * obstacles/enemies/map, enemy chase/attack behaviour, damage, XP and
 * enemy death cleanup. Player death and boss defeat are reported back
 * through {@link CombatEventListener} because only the caller owns
 * GameState transitions and level progression.
 */
public class CombatSystem {

    private static final int PLAYER_SPEED = 3;

    private final PlayableCharacter player;
    private final Map map;
    private final EnemySpawner spawner;
    private final List<Collidable> collidables;
    private final CombatEventListener listener;

    public CombatSystem(PlayableCharacter player, Map map, EnemySpawner spawner,
                         List<Collidable> collidables, CombatEventListener listener) {
        this.player = player;
        this.map = map;
        this.spawner = spawner;
        this.collidables = collidables;
        this.listener = listener;
    }

    public void resolveTick(Directions playerDirection) {
        playerMoves(playerDirection);
        checkEnemyCollisions();

        if (!player.isAlive()) {
            listener.onPlayerDied();
        }
    }

    private void checkEnemyCollisions() {
        int dx;
        int dy;

        for (Enemy enemies : spawner.getEnemyGroup()) {

            dx = enemies.chasePlayer(player.getHitbox())[0];
            dy = enemies.chasePlayer(player.getHitbox())[1];

            if (player.isAttacking()) {
                if (enemies.hasCollided(player.getBaseAttack())) {
                    enemies.collided(player.getBaseAttack());
                    if (!enemies.isAlive()) {
                        collidables.remove(enemies);
                        player.getLvl().addXp(enemies.xpDrop());
                        enemies.getSprite().delete();
                        player.killConfirmed(enemies.getEnemyType());
                        spawner.decreaseAliveEnemies(enemies.getEnemyType());
                        if (enemies.getEnemyType() == EnemyType.GIGARAT) {
                            listener.onBossDefeated();
                            return;
                        }
                    }
                }
            }

            // boss doesn't move while attacking
            if (enemies.getEnemyType() == EnemyType.GIGARAT) {
                BossAnimationController bossAnim = (BossAnimationController) enemies.getAnimationController();
                enemies.updateAnimation();
                // only moves if not in its attack animation
                if (!bossAnim.isAttacking()) {
                    if (pathFind(enemies, dx, dy)) {
                        enemies.chasePlayer(dx, dy);
                    } else if (pathFind(enemies, dx != 0 ? dx : enemies.getSpeed(), 0) && dx + dy != 0) {
                        enemies.chasePlayer(dx != 0 ? dx : enemies.getSpeed(), 0);
                    } else if (pathFind(enemies, 0, dy != 0 ? dy : enemies.getSpeed()) && dx + dy != 0) {
                        enemies.chasePlayer(0, dy != 0 ? dy : enemies.getSpeed());
                    }
                }
                // damage is only dealt once the attack animation has finished AND it's still in contact
                if (bossAnim.wasAttackJustFinished() && enemies.hasCollided(player)) {
                    player.takeDamage(enemies.getDmg());
                }
                continue; // skip the regular enemy movement block below
            }

            if (pathFind(enemies, dx, dy)) {
                enemies.chasePlayer(dx, dy);
            } else if (pathFind(enemies, dx != 0 ? dx : enemies.getSpeed(), 0) && dx + dy != 0) {
                enemies.chasePlayer(dx != 0 ? dx : enemies.getSpeed(), 0);
            } else if (pathFind(enemies, 0, dy != 0 ? dy : enemies.getSpeed()) && dx + dy != 0) {
                enemies.chasePlayer(0, dy != 0 ? dy : enemies.getSpeed());
            }
        }
    }

    private boolean pathFind(Enemy enemies, int dx, int dy) {
        boolean col = false;

        enemies.move(dx, dy);

        if (enemies.hasCollided(player)) {
            player.collided(enemies);
            enemies.collided(player);
        }

        for (Collidable obs : collidables) {

            if (enemies != obs) {
                if (!col) {
                    col = (enemies.hasCollided(obs) || enemies.hasCollided(player) || !map.hasCollided(enemies)) && !(obs instanceof HUDComponent);
                }
                if (enemies.hasCollided(obs)) {
                    enemies.collided(obs);
                    obs.collided(enemies);
                }
            }
        }
        enemies.move(-dx, -dy);
        return !col;
    }

    private void playerMoves(Directions dir) {

        int dx = dir.getDx() * PLAYER_SPEED;
        int dy = dir.getDy() * PLAYER_SPEED;

        player.getHitbox().translate(dx, dy);

        boolean collision = false;
        for (Collidable cols : collidables) {
            collision = player.hasCollided(cols);

            if (collision) {
                player.collided(cols);
                cols.collided(player);

                break;
            }

        }

        if (!collision && map.hasCollided(player)) {
            player.getHitbox().translate(-dx, -dy);
            map.move(-dx, -dy);
            player.move(dx, dy);
            spawner.move(-dx, -dy);
            return;
        }

        player.getHitbox().translate(-dx, -dy);
    }
}
