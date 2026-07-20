package ratplaguesurvivors.entity.npc;

import com.codeforall.online.simplegraphics.pictures.Picture;

import ratplaguesurvivors.interfaces.EnemyAnimationController;

public enum EnemyType {
    MOUSE("Mouse", 10, 20, 5, 1, MouseSpriteType.DOWN_0.getPath(), false),
    RAT("Rat", 20, 60, 10, 2, RatSpriteType.DOWN_0.getPath(), false),
    GIGARAT("GigaRat", 50, 20000, 30, 1, BossSpriteType.RUN_DOWN_0.getPath(), true);

    private String name;
    private int health;
    private int xpValue;
    private int damage;
    private int speed;
    private String spritePath;
    private boolean incrementOnChase;

    EnemyType(String name, int health, int xpValue, int damage, int speed, String spritePath, boolean incrementOnChase) {
        this.name = name;
        this.health = health;
        this.xpValue = xpValue;
        this.damage = damage;
        this.speed = speed;
        this.spritePath = spritePath;
        this.incrementOnChase = incrementOnChase;
    }
    public EnemyAnimationController createAnimationController(Picture sprite) {
        return switch (this) {
            case MOUSE -> new MouseAnimationController(sprite);
            case RAT -> new RatAnimationController(sprite);
            case GIGARAT -> new BossAnimationController(sprite);
        };
    }

    //Factory method
    public Enemy create(int dx, int dy){
        return new Enemy(this, dx, dy);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getXpValue() {
        return xpValue;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public boolean isIncrementOnChase() {
        return incrementOnChase;
    }
}
