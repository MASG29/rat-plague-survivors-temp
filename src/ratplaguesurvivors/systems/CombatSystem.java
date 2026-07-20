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
    private final EnemyRewardSystem rewardSystem;

    public CombatSystem(PlayableCharacter player, Map map, EnemySpawner spawner,
                         List<Collidable> collidables, CombatEventListener listener) {
        this.player = player;
        this.map = map;
        this.spawner = spawner;
        this.collidables = collidables;
        this.listener = listener;
        this.rewardSystem = new EnemyRewardSystem();
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

        for (Enemy enemy : spawner.getEnemyGroup()) {

            int[] chase = enemy.chasePlayer(player.getHitbox());
            dx = chase[0];
            dy = chase[1];

            if (player.isAttacking()) {
                if (enemy.hasCollided(player.getBaseAttack())) {
                    enemy.collided(player.getBaseAttack());
                    if (!enemy.isAlive()) {
                        rewardSystem.handleDefeat(enemy, player, spawner, collidables);
                        if (enemy.getEnemyType() == EnemyType.GIGARAT) {
                            listener.onBossDefeated();
                            return;
                        }
                    }
                }
            }

            // boss doesn't move while attacking
            if (enemy.getEnemyType() == EnemyType.GIGARAT) {
                BossAnimationController bossAnim = (BossAnimationController) enemy.getAnimationController();
                enemy.updateAnimation();
                // only moves if not in its attack animation
                if (!bossAnim.isAttacking()) {
                    moveTowardPlayer(enemy, dx, dy);
                }
                // damage is only dealt once the attack animation has finished AND it's still in contact
                if (bossAnim.wasAttackJustFinished() && enemy.hasCollided(player)) {
                    player.takeDamage(enemy.getDmg());
                }
                continue; // skip the regular enemy movement block below
            }

            moveTowardPlayer(enemy, dx, dy);
        }
    }

    private void moveTowardPlayer(Enemy enemy, int dx, int dy) {
        if (pathFind(enemy, dx, dy)) {
            enemy.chasePlayer(dx, dy);
        } else if (pathFind(enemy, dx != 0 ? dx : enemy.getSpeed(), 0) && dx + dy != 0) {
            enemy.chasePlayer(dx != 0 ? dx : enemy.getSpeed(), 0);
        } else if (pathFind(enemy, 0, dy != 0 ? dy : enemy.getSpeed()) && dx + dy != 0) {
            enemy.chasePlayer(0, dy != 0 ? dy : enemy.getSpeed());
        }
    }

    private boolean pathFind(Enemy enemy, int dx, int dy) {
        boolean col = false;

        enemy.move(dx, dy);

        if (enemy.hasCollided(player)) {
            player.collided(enemy);
            enemy.collided(player);
        }

        for (Collidable obs : collidables) {

            if (enemy != obs) {
                if (!col) {
                    col = (enemy.hasCollided(obs) || enemy.hasCollided(player) || !map.hasCollided(enemy)) && !(obs instanceof HUDComponent);
                }
                if (enemy.hasCollided(obs)) {
                    enemy.collided(obs);
                    obs.collided(enemy);
                }
            }
        }
        enemy.move(-dx, -dy);
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
