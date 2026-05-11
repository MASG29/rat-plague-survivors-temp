package ratplaguesurvivors.entity.pc;

import com.codeforall.online.simplegraphics.pictures.Picture;
import ratplaguesurvivors.attacks.BaseAttack;
import ratplaguesurvivors.entity.Entity;
import ratplaguesurvivors.entity.npc.EnemyType;
import ratplaguesurvivors.entity.npc.Enemy;
import ratplaguesurvivors.input.Directions;
import ratplaguesurvivors.interfaces.Collidable;
import ratplaguesurvivors.interfaces.Moves;
import ratplaguesurvivors.utils.CollisionDetector;
import ratplaguesurvivors.utils.Position;
import ratplaguesurvivors.init.GameLoop;


public class PlayableCharacter extends Entity implements Moves {

    private PCType pcType;
    private Lvl lvl;
    private BaseAttack baseAttack;
    private Picture sprite;
    private AnimationController animationController;
    private int killCounter;
    private int mouseKillCount = 0;
    private int ratKillCount = 0;
    private Directions lastDirection = Directions.NONE;

    private static final int SPRITE_WIDTH = 160;
    private static final int SPRITE_HEIGHT = 160;
    private static final int INIT_X = GameLoop.WINDOW_WIDTH / 2;
    private static final int INIT_Y = GameLoop.WINDOW_HEIGHT / 2;


    public PlayableCharacter(PCType pcType) {
        super(pcType.getName(), new Position(
                INIT_X, INIT_Y ,SPRITE_WIDTH, SPRITE_HEIGHT),
                pcType.getBaseHealth(),
                new Position(
                        INIT_X + (SPRITE_WIDTH / 2) - (40 / 2),    // 115
                        INIT_Y + (SPRITE_HEIGHT / 2) - (60 / 2),   // 110
                        40, 60));


        this.sprite = new Picture(getPos().getX(), getPos().getY(), CatSpriteType.IDLE_DOWN_0.getPath());
        
        //this.sprite.draw();
        killCounter = 0;
        this.animationController = new AnimationController(sprite);
        this.pcType = pcType;
        this.baseAttack = pcType.getAttackType();
        this.lvl = new Lvl();
    }

    public Picture getSprite() {
        return sprite;
    }

    public void updateAnimation() {
        animationController.update();
    }

    public void draw(){
        sprite.draw();
    }

    public void attack(Directions direction) throws InterruptedException {
        if (direction != Directions.NONE){
            lastDirection = direction;
        }
        boolean wasAttacking = baseAttack.isAttacking();
        baseAttack.attack(getHitbox(), lastDirection);
        // Set body attack animation only on the tick the attack actually begins
        if (!wasAttacking && baseAttack.isAttacking()) {
            switch (lastDirection) {
                case UP   -> animationController.setAnimation(AnimationController.getAttackUp());
                case DOWN -> animationController.setAnimation(AnimationController.getAttackDown());
                case LEFT -> animationController.setAnimation(AnimationController.getAttackLeft());
                case RIGHT-> animationController.setAnimation(AnimationController.getAttackRight());
                case NONE -> attack(lastDirection);
            }
        }
    }

    @Override
    public void move(int dx, int dy){

        if (baseAttack.isAttacking()) return; // hold attack animation until the attack finishes

        // atualiza a animação consoante a direção
        if (dx == 0 && dy == 0) {
            animationController.setAnimation(AnimationController.getIdleDown());
        } else if (dy > 0) {
            animationController.setAnimation(AnimationController.getRunDown());
        } else if (dy < 0) {
            animationController.setAnimation(AnimationController.getRunUp());
        } else if (dx < 0) {
            animationController.setAnimation(AnimationController.getRunLeft());
        } else if (dx > 0) {
            animationController.setAnimation(AnimationController.getRunRight());
        }
    }

    public void killConfirmed(EnemyType enemyType){
        killCounter++;
        if(enemyType == EnemyType.RAT){
            ratKillCount++;
        } else if(enemyType == EnemyType.MOUSE){
            mouseKillCount++;
        }

    }

    public int getKillCounter() {
        return killCounter;
    }

    public PCType getPcType() {
        return pcType;
    }

    public Lvl getLvl() {
        return lvl;
    }


    public BaseAttack getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(BaseAttack baseAttack) {
        this.baseAttack = baseAttack;
    }

    @Override
    public boolean hasCollided(Collidable obj2) {
        return CollisionDetector.hasCollided(getHitbox(), obj2.getHitbox());
    }

    @Override
    public void collided(Collidable obj2) {
        if (obj2 instanceof Enemy) {
            Enemy enemy = (Enemy) obj2;
            if (enemy.getEnemyType() == EnemyType.GIGARAT) {
                return; // dano dado no GameLoop depois da animação terminar
            }
            if (enemy.cooldownReset()) {
                takeDamage(enemy.getDmg());
            }
        }
    }

    public int getMouseKillCount() {
        return mouseKillCount;
    }

    public int getRatKillCount() {
        return ratKillCount;
    }

    public boolean isAttacking(){
        return getBaseAttack().isAttacking();
    }

}
