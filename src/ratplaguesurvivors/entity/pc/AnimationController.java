package ratplaguesurvivors.entity.pc;

import com.codeforall.online.simplegraphics.pictures.Picture;

class AnimationController {

    private static final int TICKS_PER_FRAME = 8; // Changes the image every 8 ticks

    private Picture sprite;
    private CatSpriteType[] currentAnimation;
    private int currentFrame;
    private int tickCount;

    // Arrays to each action
    private static final CatSpriteType[] IDLE_DOWN  = {
            CatSpriteType.IDLE_DOWN_0, CatSpriteType.IDLE_DOWN_1, CatSpriteType.IDLE_DOWN_2,
            CatSpriteType.IDLE_DOWN_3, CatSpriteType.IDLE_DOWN_4, CatSpriteType.IDLE_DOWN_5,
            CatSpriteType.IDLE_DOWN_6, CatSpriteType.IDLE_DOWN_7, CatSpriteType.IDLE_DOWN_8
    };

    private static final CatSpriteType[] IDLE_RIGHT = { CatSpriteType.IDLE_RIGHT_0 };
    private static final CatSpriteType[] IDLE_UP    = { CatSpriteType.IDLE_UP_0 };
    private static final CatSpriteType[] IDLE_LEFT  = { CatSpriteType.IDLE_LEFT_0 };

    private static final CatSpriteType[] RUN_DOWN  = {
            CatSpriteType.RUN_DOWN_0, CatSpriteType.RUN_DOWN_1, CatSpriteType.RUN_DOWN_2,
            CatSpriteType.RUN_DOWN_3, CatSpriteType.RUN_DOWN_4, CatSpriteType.RUN_DOWN_5,
            CatSpriteType.RUN_DOWN_6, CatSpriteType.RUN_DOWN_7
    };

    private static final CatSpriteType[] RUN_LEFT  = {
            CatSpriteType.RUN_LEFT_0, CatSpriteType.RUN_LEFT_1, CatSpriteType.RUN_LEFT_2,
            CatSpriteType.RUN_LEFT_3, CatSpriteType.RUN_LEFT_4, CatSpriteType.RUN_LEFT_5,
            CatSpriteType.RUN_LEFT_6, CatSpriteType.RUN_LEFT_7
    };

    private static final CatSpriteType[] RUN_RIGHT = {
            CatSpriteType.RUN_RIGHT_0, CatSpriteType.RUN_RIGHT_1, CatSpriteType.RUN_RIGHT_2,
            CatSpriteType.RUN_RIGHT_3, CatSpriteType.RUN_RIGHT_4, CatSpriteType.RUN_RIGHT_5,
            CatSpriteType.RUN_RIGHT_6, CatSpriteType.RUN_RIGHT_7
    };

    private static final CatSpriteType[] RUN_UP    = {
            CatSpriteType.RUN_UP_0, CatSpriteType.RUN_UP_1, CatSpriteType.RUN_UP_2,
            CatSpriteType.RUN_UP_3, CatSpriteType.RUN_UP_4, CatSpriteType.RUN_UP_5,
            CatSpriteType.RUN_UP_6, CatSpriteType.RUN_UP_7
    };

    private static final CatSpriteType[] ATTACK_DOWN  = {
            CatSpriteType.ATTACK_DOWN_0, CatSpriteType.ATTACK_DOWN_1, CatSpriteType.ATTACK_DOWN_2
    };

    private static final CatSpriteType[] ATTACK_LEFT  = {
            CatSpriteType.ATTACK_LEFT_0, CatSpriteType.ATTACK_LEFT_1, CatSpriteType.ATTACK_LEFT_2
    };

    private static final CatSpriteType[] ATTACK_RIGHT = {
            CatSpriteType.ATTACK_RIGHT_0, CatSpriteType.ATTACK_RIGHT_1, CatSpriteType.ATTACK_RIGHT_2
    };

    private static final CatSpriteType[] ATTACK_UP    = {
            CatSpriteType.ATTACK_UP_0, CatSpriteType.ATTACK_UP_1, CatSpriteType.ATTACK_UP_2
    };

    public AnimationController(Picture sprite) {
        this.sprite = sprite;
        this.currentAnimation = IDLE_DOWN;
        this.currentFrame = 0;
        this.tickCount = 0;
    }

    // Called every Gameloop tick
    public void update() {
        tickCount++;
        if (tickCount >= TICKS_PER_FRAME) {
            tickCount = 0;
            currentFrame = (currentFrame + 1) % currentAnimation.length;
            sprite.load(currentAnimation[currentFrame].getPath());
        }
    }

    // Called by PlayableCharacter when it moves or stops
    public void setAnimation(CatSpriteType[] newAnimation) {
        if (currentAnimation == newAnimation) return; // já está nesta animação, não faz nada
        currentAnimation = newAnimation;
        currentFrame = 0;
        tickCount = 0;
        sprite.load(currentAnimation[0].getPath());
    }

    // getters
    public static CatSpriteType[] getIdleDown()    { return IDLE_DOWN; }
    public static CatSpriteType[] getIdleRight()   { return IDLE_RIGHT; }
    public static CatSpriteType[] getIdleUp()      { return IDLE_UP; }
    public static CatSpriteType[] getIdleLeft()    { return IDLE_LEFT; }
    public static CatSpriteType[] getRunDown()     { return RUN_DOWN; }
    public static CatSpriteType[] getRunLeft()     { return RUN_LEFT; }
    public static CatSpriteType[] getRunRight()    { return RUN_RIGHT; }
    public static CatSpriteType[] getRunUp()       { return RUN_UP; }
    public static CatSpriteType[] getAttackDown()  { return ATTACK_DOWN; }
    public static CatSpriteType[] getAttackLeft()  { return ATTACK_LEFT; }
    public static CatSpriteType[] getAttackRight() { return ATTACK_RIGHT; }
    public static CatSpriteType[] getAttackUp()    { return ATTACK_UP; }
}
