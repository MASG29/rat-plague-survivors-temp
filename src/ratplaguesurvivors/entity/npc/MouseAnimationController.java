package ratplaguesurvivors.entity.npc;

import com.codeforall.online.simplegraphics.pictures.Picture;

import ratplaguesurvivors.interfaces.EnemyAnimationController;


class MouseAnimationController implements EnemyAnimationController {

    private static final int TICKS_PER_FRAME = 8; // Changes the image every 2 ticks

    private Picture sprite;
    private MouseSpriteType[] currentAnimation;
    private int currentFrame;
    private int tickCount;
    private boolean finished = false;

    // Arrays to each action
    private static final MouseSpriteType[] DOWN = {
            MouseSpriteType.DOWN_0, MouseSpriteType.DOWN_1, MouseSpriteType.DOWN_2
    };

    private static final MouseSpriteType[] LEFT = {
            MouseSpriteType.LEFT_0, MouseSpriteType.LEFT_1, MouseSpriteType.LEFT_2
    };

    private static final MouseSpriteType[] RIGHT = {
            MouseSpriteType.RIGHT_0, MouseSpriteType.RIGHT_1, MouseSpriteType.RIGHT_2
    };

    private static final MouseSpriteType[] UP = {
            MouseSpriteType.UP_0, MouseSpriteType.UP_1, MouseSpriteType.UP_2
    };


    public MouseAnimationController(Picture sprite) {
        this.sprite = sprite;
        this.currentAnimation = DOWN;
        this.currentFrame = 0;
        this.tickCount = 0;
    }


    public void update() {
        tickCount++;
        if (tickCount >= TICKS_PER_FRAME) {
            tickCount = 0;
            currentFrame = (currentFrame + 1) % currentAnimation.length;
            sprite.load(currentAnimation[currentFrame].getPath());
        }
    }

    public void setAnimation(MouseSpriteType[] newAnimation) {
        if (currentAnimation == newAnimation) return; // já está nesta animação, não faz nada
        currentAnimation = newAnimation;
        currentFrame = 0;
        tickCount = 0;
        sprite.load(currentAnimation[0].getPath());
    }


    @Override
    public void setDirection(int dx, int dy) {
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
    }


    // getters
    public boolean isFinished() {
        return finished;
    }

    public static MouseSpriteType[] getMouseDown() {
        return DOWN;
    }

    public static MouseSpriteType[] getMouseRight() {
        return RIGHT;
    }

    public static MouseSpriteType[] getMouseUp() {
        return UP;
    }

    public static MouseSpriteType[] getMouseLeft() {
        return LEFT;
    }
}