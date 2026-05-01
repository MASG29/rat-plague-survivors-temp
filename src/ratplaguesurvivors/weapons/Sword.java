package ratplaguesurvivors.weapons;

import com.codeforall.online.simplegraphics.pictures.Picture;
import ratplaguesurvivors.attacks.BaseAttack;
import ratplaguesurvivors.input.Directions;
import ratplaguesurvivors.interfaces.Collidable;
import ratplaguesurvivors.utils.Position;

public class Sword extends BaseAttack {

    private Picture sprite;
    private static double atkSpeed = 1.5;
    private static int dmg = 15;
    private long delay = 1000;
    private long lasthit;
    private static final int ATTACK_HITBOX_WIDTH = 90;
    private static final int ATTACK_HITBOX_HEIGHT = 90;
    private SwordAnimationController animationController;
    private boolean attacking = false;

    public Sword() {
        super(dmg, atkSpeed);
        this.animationController = new SwordAnimationController();
    }

    // Called every game tick by the GameLoop to advance the attack animation.
    // When the animation finishes, cleans up the hitbox and sprite.
    public void update() {
        if (!attacking) {
            return;
        }

        animationController.update();

        if (animationController.isFinished()) {
            // Animation is done — remove the hitbox and sprite from the screen
            attacking = false;
            sprite.delete();
            attackHitbox = null;
            sprite = null;
        }
    }

    @Override
    public void attack(Position position, Directions direction) throws InterruptedException {
        // Only attack if the cooldown has passed and we're not already attacking
        if (System.currentTimeMillis() - lasthit > delay && !attacking) {

            attacking = true;

            switch (direction) {
                case UP -> {
                    attackHitbox = new Position(
                            position.getX() - ATTACK_HITBOX_WIDTH / 2,
                            position.getY() - ATTACK_HITBOX_HEIGHT,
                            ATTACK_HITBOX_WIDTH, ATTACK_HITBOX_HEIGHT);
                    sprite = new Picture(attackHitbox.getX(), attackHitbox.getY(),
                            "resources/Attack_Frames/Attack_Up/1.png");
                    sprite.draw();
                    // Pass the new sprite so the animation controller updates the correct Picture
                    animationController.setAnimation(SwordAnimationController.getAttackUp(), sprite);
                }
                case DOWN -> {
                    attackHitbox = new Position(
                            position.getX() - ATTACK_HITBOX_WIDTH / 2,
                            position.getY() + position.getHeight(),
                            ATTACK_HITBOX_WIDTH, ATTACK_HITBOX_HEIGHT);
                    sprite = new Picture(attackHitbox.getX(), attackHitbox.getY(),
                            "resources/Attack_Frames/Attack_Down/1.png");
                    sprite.draw();
                    animationController.setAnimation(SwordAnimationController.getAttackDown(), sprite);
                }
                case RIGHT -> {
                    attackHitbox = new Position(
                            position.getX() + position.getWidth(),
                            position.getY() - position.getHeight() / 2,
                            ATTACK_HITBOX_WIDTH, ATTACK_HITBOX_HEIGHT);

                    sprite = new Picture(attackHitbox.getX(), attackHitbox.getY(),
                            "resources/Attack_Frames/Attack_Right/1.png");
                    sprite.draw();

                    animationController.setAnimation(SwordAnimationController.getAttackRight(), sprite);
                }
                case LEFT -> {
                    attackHitbox = new Position(
                            position.getX() - ATTACK_HITBOX_WIDTH,
                            position.getY() - position.getHeight() / 2,
                            ATTACK_HITBOX_WIDTH, ATTACK_HITBOX_HEIGHT);

                    sprite = new Picture(attackHitbox.getX(), attackHitbox.getY(),
                            "resources/Attack_Frames/Attack_Left/1.png");
                    sprite.draw();

                    animationController.setAnimation(SwordAnimationController.getAttackLeft(), sprite);
                }
                case NONE -> {
                    attackHitbox = new Position(
                            position.getX() + position.getWidth(),
                            position.getY() - position.getHeight() / 2,
                            ATTACK_HITBOX_WIDTH, ATTACK_HITBOX_HEIGHT);
                    sprite = new Picture(attackHitbox.getX(), attackHitbox.getY(),
                            "resources/Attack_Frames/Attack_Right/1.png");
                    sprite.draw();
                    animationController.setAnimation(SwordAnimationController.getAttackRight(), sprite);
                }
            }
            lasthit = System.currentTimeMillis();
        }
    }

    @Override
    public Picture getWeaponSprite() {
        return sprite;
    }

    @Override
    public Position getAttackHitbox() {
        return attackHitbox;
    }

    public boolean isAttacking() {
        return attacking;
    }

    @Override
    public void collided(Collidable obj2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'collided'");
    }
}