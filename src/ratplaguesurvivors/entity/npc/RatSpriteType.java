package ratplaguesurvivors.entity.npc;

public enum RatSpriteType {

    UP_0("resources/rat/rat_0_0.png"),
    UP_1("resources/rat/rat_0_1.png"),
    UP_2("resources/rat/rat_0_2.png"),

    RIGHT_0("resources/rat/rat_1_0.png"),
    RIGHT_1("resources/rat/rat_1_1.png"),
    RIGHT_2("resources/rat/rat_1_2.png"),

    DOWN_0("resources/rat/rat_2_0.png"),
    DOWN_1("resources/rat/rat_2_1.png"),
    DOWN_2("resources/rat/rat_2_2.png"),

    LEFT_0("resources/rat/rat_3_0.png"),
    LEFT_1("resources/rat/rat_3_1.png"),
    LEFT_2("resources/rat/rat_3_2.png");

    private final String path;

    RatSpriteType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}