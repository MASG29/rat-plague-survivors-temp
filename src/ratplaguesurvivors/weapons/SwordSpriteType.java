package ratplaguesurvivors.weapons;

public enum SwordSpriteType {

    //UP
    UP_0("resources/Attack_Frames/Attack_Up/1.png"),
    UP_1("resources/Attack_Frames/Attack_Up/2.png"),
    UP_2("resources/Attack_Frames/Attack_Up/3.png"),
    UP_3("resources/Attack_Frames/Attack_Up/4.png"),
    UP_4("resources/Attack_Frames/Attack_Up/5.png"),
    UP_5("resources/Attack_Frames/Attack_Up/6.png"),
    UP_6("resources/Attack_Frames/Attack_Up/7.png"),
    UP_7("resources/Attack_Frames/Attack_Up/8.png"),

    //DOWN
    DOWN_0("resources/Attack_Frames/Attack_Down/1.png"),
    DOWN_1("resources/Attack_Frames/Attack_Down/2.png"),
    DOWN_2("resources/Attack_Frames/Attack_Down/3.png"),
    DOWN_3("resources/Attack_Frames/Attack_Down/4.png"),
    DOWN_4("resources/Attack_Frames/Attack_Down/5.png"),
    DOWN_5("resources/Attack_Frames/Attack_Down/6.png"),
    DOWN_6("resources/Attack_Frames/Attack_Down/7.png"),
    DOWN_7("resources/Attack_Frames/Attack_Down/8.png"),

    //RIGHT
    RIGHT_0("resources/Attack_Frames/Attack_Right/1.png"),
    RIGHT_1("resources/Attack_Frames/Attack_Right/2.png"),
    RIGHT_2("resources/Attack_Frames/Attack_Right/3.png"),
    RIGHT_3("resources/Attack_Frames/Attack_Right/4.png"),
    RIGHT_4("resources/Attack_Frames/Attack_Right/5.png"),
    RIGHT_5("resources/Attack_Frames/Attack_Right/6.png"),
    RIGHT_6("resources/Attack_Frames/Attack_Right/7.png"),
    RIGHT_7("resources/Attack_Frames/Attack_Right/8.png"),

    //LEFT
    LEFT_0("resources/Attack_Frames/Attack_Left/1.png"),
    LEFT_1("resources/Attack_Frames/Attack_Left/2.png"),
    LEFT_2("resources/Attack_Frames/Attack_Left/3.png"),
    LEFT_3("resources/Attack_Frames/Attack_Left/4.png"),
    LEFT_4("resources/Attack_Frames/Attack_Left/5.png"),
    LEFT_5("resources/Attack_Frames/Attack_Left/6.png"),
    LEFT_6("resources/Attack_Frames/Attack_Left/7.png"),
    LEFT_7("resources/Attack_Frames/Attack_Left/8.png");

    private final String path;

    SwordSpriteType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}