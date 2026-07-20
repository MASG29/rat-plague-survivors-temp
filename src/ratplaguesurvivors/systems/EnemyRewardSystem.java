package ratplaguesurvivors.systems;

import java.util.List;

import ratplaguesurvivors.entity.npc.Enemy;
import ratplaguesurvivors.entity.npc.EnemySpawner;
import ratplaguesurvivors.entity.pc.PlayableCharacter;
import ratplaguesurvivors.interfaces.Collidable;

/**
 * Bookkeeping for a defeated (non-boss) enemy: XP, kill count, sprite
 * cleanup and spawner accounting. Split out of {@link CombatSystem} so
 * collision detection doesn't stay mixed with reward handling.
 */
public class EnemyRewardSystem {

    public void handleDefeat(Enemy enemy, PlayableCharacter player, EnemySpawner spawner,
                              List<Collidable> collidables) {
        collidables.remove(enemy);
        player.getLvl().addXp(enemy.xpDrop());
        enemy.getSprite().delete();
        player.killConfirmed(enemy.getEnemyType());
        spawner.decreaseAliveEnemies(enemy.getEnemyType());
    }
}
