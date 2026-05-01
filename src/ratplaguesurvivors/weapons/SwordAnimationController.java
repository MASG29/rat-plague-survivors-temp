package ratplaguesurvivors.weapons;

import com.codeforall.online.simplegraphics.pictures.Picture;

class SwordAnimationController {

    private static final int TICKS_PER_FRAME = 2; // Changes the image every 8 ticks

    private Picture sprite;
    private SwordSpriteType[] currentAnimation;
    private int currentFrame;
    private int tickCount;
    private boolean finished = true;

    // Arrays to each action
    private static final SwordSpriteType[] DOWN = {
            SwordSpriteType.DOWN_0, SwordSpriteType.DOWN_1, SwordSpriteType.DOWN_2,
            SwordSpriteType.DOWN_3, SwordSpriteType.DOWN_4, SwordSpriteType.DOWN_5,
            SwordSpriteType.DOWN_6, SwordSpriteType.DOWN_7
    };

    private static final SwordSpriteType[] LEFT = {
            SwordSpriteType.LEFT_0, SwordSpriteType.LEFT_1, SwordSpriteType.LEFT_2,
            SwordSpriteType.LEFT_3, SwordSpriteType.LEFT_4, SwordSpriteType.LEFT_5,
            SwordSpriteType.LEFT_6, SwordSpriteType.LEFT_7
    };

    private static final SwordSpriteType[] RIGHT = {
            SwordSpriteType.RIGHT_0, SwordSpriteType.RIGHT_1, SwordSpriteType.RIGHT_2,
            SwordSpriteType.RIGHT_3, SwordSpriteType.RIGHT_4, SwordSpriteType.RIGHT_5,
            SwordSpriteType.RIGHT_6, SwordSpriteType.RIGHT_7
    };

    private static final SwordSpriteType[] UP = {
            SwordSpriteType.UP_0, SwordSpriteType.UP_1, SwordSpriteType.UP_2,
            SwordSpriteType.UP_3, SwordSpriteType.UP_4, SwordSpriteType.UP_5,
            SwordSpriteType.UP_6, SwordSpriteType.UP_7
    };


    public SwordAnimationController() {
        this.sprite = null;
        this.currentAnimation = DOWN;
        this.currentFrame = 0;
        this.tickCount = 0;
        this.finished = true; // no animation until setAnimation() is called for an actual attack
    }

    // Updates the animation frame every TICKS_PER_FRAME ticks.
    // When the last frame is reached, marks the animation as finished
    // instead of looping — attack animations only play once.
    public void update() {
        if (finished) return;

        tickCount++;
        if (tickCount >= TICKS_PER_FRAME) {
            tickCount = 0;

            if (currentFrame >= currentAnimation.length - 1) {
                // Last frame reached — signal that the attack animation is done
                finished = true;
                return;
            }

            currentFrame++;
            sprite.load(currentAnimation[currentFrame].getPath());
        }
    }

    // Sets a new animation and resets all counters.
    // Also updates the sprite reference in case a new Picture was created for this attack.
    public void setAnimation(SwordSpriteType[] newAnimation, Picture newSprite) {
        this.sprite = newSprite; // update sprite reference to the newly created Picture
        this.currentAnimation = newAnimation;
        this.currentFrame = 0;
        this.tickCount = 0;
        this.finished = false;
        sprite.load(currentAnimation[0].getPath());
    }


    // getters
    public boolean isFinished() {
        return finished;
    }

    public static SwordSpriteType[] getAttackDown() {
        return DOWN;
    }

    public static SwordSpriteType[] getAttackRight() {
        return RIGHT;
    }

    public static SwordSpriteType[] getAttackUp() {
        return UP;
    }

    public static SwordSpriteType[] getAttackLeft() {
        return LEFT;
    }
}