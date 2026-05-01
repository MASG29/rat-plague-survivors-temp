package ratplaguesurvivors.entity.npc;

import ratplaguesurvivors.map.Map;
import ratplaguesurvivors.utils.CollisionDetector;
import ratplaguesurvivors.utils.Position;
import ratplaguesurvivors.utils.Util;
import ratplaguesurvivors.interfaces.*;
import java.util.ArrayList;
import java.util.List;

public class EnemySpawner implements Moves{
    private long spawnRate = 1000;
    private long lastSpawn;
    private int maxEnemyCount = 40;
    private int mouseMaxNumb;
    private int ratMaxNUmb;
    private int gigaRatMaxNumb;
    private boolean isSpawning = true;
    private int mousesAlive = 0;
    private int ratsAlive = 0;
    private int gigaRatsAlive = 0;



    private ArrayList<Enemy> enemyGroup = new ArrayList<Enemy>();

    public EnemySpawner(Map map){
        this.maxEnemyCount = map.getLevel().getMaxEnemyCount();
        this.mouseMaxNumb = map.getLevel().getMaxMouse();
        this.ratMaxNUmb = map.getLevel().getMaxRat();
        this.gigaRatMaxNumb = map.getLevel().getMaxBoss();
    }

    public void spawn(Map map){
        if (enemyGroup.size() >= maxEnemyCount || !isSpawning) {
            return;
        }
        //if the current time - last spawn is more than the spawnRate, spawn an enemy
        if (System.currentTimeMillis() - lastSpawn > spawnRate){

            List<Integer> available = new ArrayList<>();
            if (mousesAlive < mouseMaxNumb){
                available.add(0);
            }
            if (ratsAlive < ratMaxNUmb){
                available.add(1);
            }

            if (available.isEmpty()) {
                isSpawning = false;
                return;
            }


            int decider = available.get(Util.rand(available.size()));
            switch (decider){
                case 0:
                    spawn(EnemyType.MOUSE, map);
                    break;
                case 1:
                    spawn(EnemyType.RAT, map);
                    break;
            }
            this.lastSpawn = System.currentTimeMillis();
        }
    }

    public void spawn(EnemyType type, Map map){
        if (gigaRatsAlive == gigaRatMaxNumb && type == EnemyType.GIGARAT){
            return;
        }
        Enemy enemy = new Enemy(type, 0, 0);
        int dx = map.getPos(enemy.getHitbox()).getX() + Util.rand(map.getPos(enemy.getHitbox()).getWidth());
        int dy = map.getPos(enemy.getHitbox()).getY() + Util.rand(map.getPos(enemy.getHitbox()).getHeight());
        enemy.move(dx, dy);
        if (!overlapCheck(enemy, map)){
            enemyGroup.add(enemy);
            enemy.draw();
            switch (type){
                case MOUSE:
                    mousesAlive++;
                    break;
                case RAT:
                    ratsAlive++;
                    break;
                case GIGARAT:
                    gigaRatsAlive++;
                    break;
            }
        }
        else {
            spawn(type, map);
        }
    }

    public boolean overlapCheck(Enemy enemy, Map map){
        ArrayList<Collidable> overlap = new ArrayList<>();
        overlap.addAll(enemyGroup);
        overlap.addAll(map.getObstacles());

        for(Collidable cols : overlap){
            if (CollisionDetector.hasCollided(enemy.getPos(), cols.getPos())){
                return true;
            }
        }
        return false;
    }

    public void removeDeadEnemies(){
        enemyGroup.removeIf(enemy -> !enemy.isAlive());
    }

    public List<Enemy> getEnemies(){
        return enemyGroup;
    }

    public void clear(){
        enemyGroup.clear();
        isSpawning = false;
    }

    // makes cat smell like cheese so the rats go running for him

    public void updateEnemies(Position target){
        for (Enemy enemy : enemyGroup){
            enemy.chasePlayer(target);
        }

    }

    public int getMaxEnemyCount() {
        return maxEnemyCount;
    }

    public ArrayList<Enemy> getEnemyGroup() {
        return enemyGroup;
    }

    public int getGigaRatMaxNumb() {
        return gigaRatMaxNumb;
    }

    public int getGigaRatsAlive() {
        return gigaRatsAlive;
    }

    @Override
    public void move(int dx, int dy) {
        for (Enemy enemies : enemyGroup){
            enemies.move(dx, dy);
        }
    }

    public void decreaseAliveEnemies(EnemyType type){
        if (type == EnemyType.MOUSE){
            mousesAlive--;
        }
        else if (type == EnemyType.RAT){
            ratsAlive--;
        }
    }


}

