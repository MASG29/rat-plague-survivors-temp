package ratplaguesurvivors.entity.npc;

public enum MouseSpriteType {

    UP_0("resources/mouse/mouse_0_0.png"),
    UP_1("resources/mouse/mouse_0_1.png"),
    UP_2("resources/mouse/mouse_0_2.png"),

    RIGHT_0("resources/mouse/mouse_1_0.png"),
    RIGHT_1("resources/mouse/mouse_1_1.png"),
    RIGHT_2("resources/mouse/mouse_1_2.png"),

    DOWN_0("resources/mouse/mouse_2_0.png"),
    DOWN_1("resources/mouse/mouse_2_1.png"),
    DOWN_2("resources/mouse/mouse_2_2.png"),

    LEFT_0("resources/mouse/mouse_3_0.png"),
    LEFT_1("resources/mouse/mouse_3_1.png"),
    LEFT_2("resources/mouse/mouse_3_2.png");

    private final String path;

    MouseSpriteType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}