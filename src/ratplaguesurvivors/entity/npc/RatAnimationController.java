package ratplaguesurvivors.entity.npc;

import com.codeforall.online.simplegraphics.pictures.Picture;

import ratplaguesurvivors.interfaces.EnemyAnimationController;


class RatAnimationController implements EnemyAnimationController {

    private static final int TICKS_PER_FRAME = 8; // Changes the image every 2 ticks

    private Picture sprite;
    private RatSpriteType[] currentAnimation;
    private int currentFrame;
    private int tickCount;
    private boolean finished = false;

    // Arrays to each action
    private static final RatSpriteType[] DOWN = {
            RatSpriteType.DOWN_0, RatSpriteType.DOWN_1, RatSpriteType.DOWN_2
    };

    private static final RatSpriteType[] LEFT = {
            RatSpriteType.LEFT_0, RatSpriteType.LEFT_1, RatSpriteType.LEFT_2
    };

    private static final RatSpriteType[] RIGHT = {
            RatSpriteType.RIGHT_0, RatSpriteType.RIGHT_1, RatSpriteType.RIGHT_2
    };

    private static final RatSpriteType[] UP = {
            RatSpriteType.UP_0, RatSpriteType.UP_1, RatSpriteType.UP_2
    };


    public RatAnimationController(Picture sprite) {
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

    public void setAnimation(RatSpriteType[] newAnimation) {
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

        public static RatSpriteType[] getRatDown() {
        return DOWN;
    }

    public static RatSpriteType[] getRatRight() {
        return RIGHT;
    }

    public static RatSpriteType[] getRatUp() {
        return UP;
    }

    public static RatSpriteType[] getRatLeft() {
        return LEFT;
    }
}