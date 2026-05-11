package ratplaguesurvivors.entity.npc;

import com.codeforall.online.simplegraphics.pictures.Picture;
import ratplaguesurvivors.attacks.BaseAttack;
import ratplaguesurvivors.entity.Entity;
import ratplaguesurvivors.entity.pc.PlayableCharacter;
import ratplaguesurvivors.hud.HUDComponent;
import ratplaguesurvivors.utils.CollisionDetector;
import ratplaguesurvivors.utils.Position;
import ratplaguesurvivors.interfaces.*;

public class Enemy extends Entity implements Moves{
    private static final int SPRITE_WIDTH = 47;
    private static final int SPRITE_HEIGHT = 80;
    protected int xpValue;
    private Picture hitBy;
    private Picture sprite;
    private int speed;
    private int dmg;
    private int attackTickCount;
    private EnemyAnimationController animationController;
    private EnemyType enemyType;
    private boolean hudCol;
    private boolean isAttacking = false;



    public Enemy(EnemyType type, int dx, int dy) {
        super(type.getName(),
                new Position(dx, dy, SPRITE_WIDTH, SPRITE_HEIGHT),
                type.getHealth(),
                new Position(dx + 40, dy + 36, SPRITE_WIDTH, SPRITE_HEIGHT));

        this.xpValue = type.getXpvalue();
        this.sprite = new Picture(dx, dy, type.getSpritePath());
        this.speed = type.getSpeed();
        this.animationController = type.createAnimationController(sprite);
        this.dmg = type.getDamage();
        this.attackTickCount = 0;
        this.enemyType = type;
        if (type == EnemyType.GIGARAT) {
            super.setHitbox(dx + 40, dy + 10, 192 - 80, 192 - 10);
        }
        if (type == EnemyType.GIGARAT) {
            super.setPosition(new Position(dx, dy, 192, 192));
        }

    }

    public void draw() {
        sprite.draw();
    }

    public Picture getSprite() {
        return sprite;
    }

    // Getters and Setters
    public boolean isAlive() {
        return super.isAlive();
    }

    public boolean cooldownReset() {
        if (this.attackTickCount >= 60) {
            this.attackTickCount = 0;
            return true;
        }
        return false;
    }

    public int[] chasePlayer(Position target) {

        Position enemyPos = getPos();
        //Getting the difference between the player pos and the rat pos
        // so we can calculate de vectorial distance to the player
        double xDiff = target.getX() - enemyPos.getX();
        double yDiff = target.getY() - enemyPos.getY();
        //calculates de distance
        double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        //only moves if it isn't already inside the player pos
        if (distance > 1) {
            int dxMove = (int) Math.round((xDiff / distance) * speed);
            int dyMove = (int) Math.round((yDiff / distance) * speed);

            return new int[] {dxMove, dyMove};
        }

        return new int[] {0, 0};
    }

    public void chasePlayer(int dx, int dy) {
        if (enemyType.isIncrementOnChase()){
            attackTickCount++;
        }
        if (!hudCol){
            animationController.setDirection(dx, dy);
            updateAnimation();
        }
        move(dx, dy);
        hudCol = false;
    }

    public int xpDrop(){
        if (isAlive()){
            return 0;
        }else{
            return xpValue;
        }
    }
    public void updateAnimation() {
        animationController.update();
    }

    public int getDmg() {
        return dmg;
    }

    public int getSpeed() {
        return speed;
    }

    public void move(int dx, int dy){
        getPos().translate(dx, dy);
        getHitbox().translate(dx, dy);
        sprite.translate(dx, dy);
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public boolean hasCollided(Collidable obj2) {
        return CollisionDetector.hasCollided(getHitbox(), obj2.getHitbox());
    }

    @Override
    public void collided(Collidable obj2) {
        if (obj2 instanceof PlayableCharacter){
            if (!enemyType.isIncrementOnChase()){
                attackTickCount++;
            }

            //If the enemy that collided is the boss, start its attack animation
            if (this.enemyType == EnemyType.GIGARAT){
                BossAnimationController bac = (BossAnimationController) animationController;
                if (!bac.isAttacking()){
                    bac.startAttackAnim();
                }
            }
        }
        if (obj2 instanceof BaseAttack){
            BaseAttack tmp = (BaseAttack)obj2;
            if (hitBy != null && hitBy == tmp.getWeaponSprite()){
                return;
            }
            hitBy = tmp.getWeaponSprite();
            takeDamage(tmp.getBaseDmg());
        }
        if (obj2 instanceof HUDComponent){
            hudCol = true;
        }
    }

    public EnemyAnimationController getAnimationController(){
        return animationController;
    }
}