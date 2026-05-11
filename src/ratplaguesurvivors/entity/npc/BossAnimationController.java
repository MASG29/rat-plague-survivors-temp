package ratplaguesurvivors.entity.npc;

import com.codeforall.online.simplegraphics.pictures.Picture;

import ratplaguesurvivors.interfaces.EnemyAnimationController;

public class BossAnimationController implements EnemyAnimationController {
    private static final int TICKS_PER_FRAME = 8; // Changes the image every 2 ticks

    private Picture sprite;
    private BossSpriteType[] currentAnimation;
    private int currentFrame;
    private int tickCount;
    private boolean finished = false;
    private boolean attackJustFinished = false;
    private boolean isAttacking = false;
    private int lastX;
    private int lastY;

    // Arrays to each action
    private static final BossSpriteType[] DOWN = {
            BossSpriteType.RUN_DOWN_0, BossSpriteType.RUN_DOWN_1, BossSpriteType.RUN_DOWN_2,
            BossSpriteType.RUN_DOWN_3, BossSpriteType.RUN_DOWN_4, BossSpriteType.RUN_DOWN_5,
            BossSpriteType.RUN_DOWN_6
    };

    private static final BossSpriteType[] LEFT = {
            BossSpriteType.RUN_LEFT_0, BossSpriteType.RUN_LEFT_1, BossSpriteType.RUN_LEFT_2,
            BossSpriteType.RUN_LEFT_3, BossSpriteType.RUN_LEFT_4, BossSpriteType.RUN_LEFT_5,
            BossSpriteType.RUN_LEFT_6
    };

    private static final BossSpriteType[] RIGHT = {
            BossSpriteType.RUN_RIGHT_0, BossSpriteType.RUN_RIGHT_1, BossSpriteType.RUN_RIGHT_2,
            BossSpriteType.RUN_RIGHT_3, BossSpriteType.RUN_RIGHT_4, BossSpriteType.RUN_RIGHT_5,
            BossSpriteType.RUN_RIGHT_6
    };

    private static final BossSpriteType[] UP = {
            BossSpriteType.RUN_UP_0, BossSpriteType.RUN_UP_1, BossSpriteType.RUN_UP_2,
            BossSpriteType.RUN_UP_3, BossSpriteType.RUN_UP_4, BossSpriteType.RUN_UP_5,
            BossSpriteType.RUN_UP_6
    };

    private static final BossSpriteType[] ATTACK_UP = {
            BossSpriteType.ATTACK_UP_0, BossSpriteType.ATTACK_UP_1, BossSpriteType.ATTACK_UP_2,
            BossSpriteType.ATTACK_UP_3, BossSpriteType.ATTACK_UP_4, BossSpriteType.ATTACK_UP_5,
            BossSpriteType.ATTACK_UP_6
    };

    private static final BossSpriteType[] ATTACK_DOWN = {
            BossSpriteType.ATTACK_DOWN_0, BossSpriteType.ATTACK_DOWN_1, BossSpriteType.ATTACK_DOWN_2,
            BossSpriteType.ATTACK_DOWN_3, BossSpriteType.ATTACK_DOWN_4, BossSpriteType.ATTACK_DOWN_5,
            BossSpriteType.ATTACK_DOWN_6
    };

    private static final BossSpriteType[] ATTACK_RIGHT = {
            BossSpriteType.ATTACK_RIGHT_0, BossSpriteType.ATTACK_RIGHT_1, BossSpriteType.ATTACK_RIGHT_2,
            BossSpriteType.ATTACK_RIGHT_3, BossSpriteType.ATTACK_RIGHT_4, BossSpriteType.ATTACK_RIGHT_5,
            BossSpriteType.ATTACK_RIGHT_6
    };

    private static final BossSpriteType[] ATTACK_LEFT = {
            BossSpriteType.ATTACK_LEFT_0, BossSpriteType.ATTACK_LEFT_1, BossSpriteType.ATTACK_LEFT_2,
            BossSpriteType.ATTACK_LEFT_3, BossSpriteType.ATTACK_LEFT_4, BossSpriteType.ATTACK_LEFT_5,
            BossSpriteType.ATTACK_LEFT_6
    };


    public BossAnimationController(Picture sprite) {
        this.sprite = sprite;
        this.currentAnimation = DOWN;
        this.currentFrame = 0;
        this.tickCount = 0;
    }


    public void update() {
        tickCount++;
        if (tickCount >= TICKS_PER_FRAME) {
            tickCount = 0;
            if (currentFrame >= currentAnimation.length - 1 && isAttacking) {
                isAttacking = false;
                finished = true;
                attackJustFinished = true;
                setDirection(lastX, lastY);
                return;
            }
            currentFrame = (currentFrame + 1) % currentAnimation.length;
            sprite.load(currentAnimation[currentFrame].getPath());
        }
    }

    public void setAnimation(BossSpriteType[] newAnimation) {
        if (currentAnimation == newAnimation) return;// já está nesta animação, não faz nada
        currentAnimation = newAnimation;
        currentFrame = 0;
        tickCount = 0;
        sprite.load(currentAnimation[0].getPath());
    }


    @Override
    public void setDirection(int dx, int dy) {
        //Save the last direction so the attack is on the direction of the player
        this.lastX = dx;
        this.lastY = dy;
        if (dy > 0) {
            setAnimation(DOWN);
        } else if (dy < 0) {
            setAnimation(UP);
        } else if (dx < 0) {
            setAnimation(LEFT);
        } else {
            setAnimation(RIGHT);
        }
    }

    @Override
    public void startAttackAnim() {
        //Start the attack animation based on the last direction
        this.isAttacking = true;
        this.finished = false;
        this.attackJustFinished = false;
        if (lastY > 0) {
            setAnimation(ATTACK_DOWN);
        } else if (lastY < 0) {
            setAnimation(ATTACK_UP);
        } else if (lastX < 0) {
            setAnimation(ATTACK_LEFT);
        } else {
            setAnimation(ATTACK_RIGHT);
        }
    }

    // getters
    public boolean wasAttackJustFinished(){
        if (attackJustFinished) {
            attackJustFinished = false;
            return true;
        }
        return false;
    }
    public boolean isAttacking(){
        return isAttacking;
    }
    public boolean isFinished() {
        return finished;
    }

    public static BossSpriteType[] getBossDown() {
        return DOWN;
    }

    public static BossSpriteType[] getBossRight() {
        return RIGHT;
    }

    public static BossSpriteType[] getBosseUp() {
        return UP;
    }

    public static BossSpriteType[] getBossLeft() {
        return LEFT;
    }
}
