package ratplaguesurvivors.entity.npc;

public enum BossSpriteType {


    // Run
    RUN_DOWN_0  ("resources/Golem/Walking_frames/walk_down_00.png"),
    RUN_DOWN_1  ("resources/Golem/Walking_frames/walk_down_01.png"),
    RUN_DOWN_2  ("resources/Golem/Walking_frames/walk_down_02.png"),
    RUN_DOWN_3  ("resources/Golem/Walking_frames/walk_down_03.png"),
    RUN_DOWN_4  ("resources/Golem/Walking_frames/walk_down_04.png"),
    RUN_DOWN_5  ("resources/Golem/Walking_frames/walk_down_05.png"),
    RUN_DOWN_6  ("resources/Golem/Walking_frames/walk_down_06.png"),


    RUN_LEFT_0  ("resources/Golem/Walking_frames/walk_left_00.png"),
    RUN_LEFT_1  ("resources/Golem/Walking_frames/walk_left_01.png"),
    RUN_LEFT_2  ("resources/Golem/Walking_frames/walk_left_02.png"),
    RUN_LEFT_3  ("resources/Golem/Walking_frames/walk_left_03.png"),
    RUN_LEFT_4  ("resources/Golem/Walking_frames/walk_left_04.png"),
    RUN_LEFT_5  ("resources/Golem/Walking_frames/walk_left_05.png"),
    RUN_LEFT_6  ("resources/Golem/Walking_frames/walk_left_06.png"),


    RUN_RIGHT_0 ("resources/Golem/Walking_frames/walk_right_00.png"),
    RUN_RIGHT_1 ("resources/Golem/Walking_frames/walk_right_01.png"),
    RUN_RIGHT_2 ("resources/Golem/Walking_frames/walk_right_02.png"),
    RUN_RIGHT_3 ("resources/Golem/Walking_frames/walk_right_03.png"),
    RUN_RIGHT_4 ("resources/Golem/Walking_frames/walk_right_04.png"),
    RUN_RIGHT_5 ("resources/Golem/Walking_frames/walk_right_05.png"),
    RUN_RIGHT_6 ("resources/Golem/Walking_frames/walk_right_06.png"),


    RUN_UP_0    ("resources/Golem/Walking_frames/walk_up_00.png"),
    RUN_UP_1    ("resources/Golem/Walking_frames/walk_up_01.png"),
    RUN_UP_2    ("resources/Golem/Walking_frames/walk_up_02.png"),
    RUN_UP_3    ("resources/Golem/Walking_frames/walk_up_03.png"),
    RUN_UP_4    ("resources/Golem/Walking_frames/walk_up_04.png"),
    RUN_UP_5    ("resources/Golem/Walking_frames/walk_up_05.png"),
    RUN_UP_6    ("resources/Golem/Walking_frames/walk_up_06.png"),


    // Attack
    ATTACK_DOWN_0  ("resources/Golem/Attacking_Frames/attack_down_00.png"),
    ATTACK_DOWN_1  ("resources/Golem/Attacking_Frames/attack_down_01.png"),
    ATTACK_DOWN_2  ("resources/Golem/Attacking_Frames/attack_down_02.png"),
    ATTACK_DOWN_3  ("resources/Golem/Attacking_Frames/attack_down_03.png"),
    ATTACK_DOWN_4  ("resources/Golem/Attacking_Frames/attack_down_04.png"),
    ATTACK_DOWN_5  ("resources/Golem/Attacking_Frames/attack_down_05.png"),
    ATTACK_DOWN_6  ("resources/Golem/Attacking_Frames/attack_down_06.png"),

    ATTACK_LEFT_0  ("resources/Golem/Attacking_Frames/attack_left_00.png"),
    ATTACK_LEFT_1  ("resources/Golem/Attacking_Frames/attack_left_01.png"),
    ATTACK_LEFT_2  ("resources/Golem/Attacking_Frames/attack_left_02.png"),
    ATTACK_LEFT_3  ("resources/Golem/Attacking_Frames/attack_left_03.png"),
    ATTACK_LEFT_4  ("resources/Golem/Attacking_Frames/attack_left_04.png"),
    ATTACK_LEFT_5  ("resources/Golem/Attacking_Frames/attack_left_05.png"),
    ATTACK_LEFT_6  ("resources/Golem/Attacking_Frames/attack_left_06.png"),

    ATTACK_RIGHT_0 ("resources/Golem/Attacking_Frames/attack_right_00.png"),
    ATTACK_RIGHT_1 ("resources/Golem/Attacking_Frames/attack_right_01.png"),
    ATTACK_RIGHT_2 ("resources/Golem/Attacking_Frames/attack_right_02.png"),
    ATTACK_RIGHT_3 ("resources/Golem/Attacking_Frames/attack_right_03.png"),
    ATTACK_RIGHT_4 ("resources/Golem/Attacking_Frames/attack_right_04.png"),
    ATTACK_RIGHT_5 ("resources/Golem/Attacking_Frames/attack_right_05.png"),
    ATTACK_RIGHT_6 ("resources/Golem/Attacking_Frames/attack_right_06.png"),

    ATTACK_UP_0    ("resources/Golem/Attacking_Frames/attack_up_00.png"),
    ATTACK_UP_1    ("resources/Golem/Attacking_Frames/attack_up_01.png"),
    ATTACK_UP_2    ("resources/Golem/Attacking_Frames/attack_up_02.png"),
    ATTACK_UP_3    ("resources/Golem/Attacking_Frames/attack_up_03.png"),
    ATTACK_UP_4    ("resources/Golem/Attacking_Frames/attack_up_04.png"),
    ATTACK_UP_5    ("resources/Golem/Attacking_Frames/attack_up_05.png"),
    ATTACK_UP_6    ("resources/Golem/Attacking_Frames/attack_up_06.png");


    private final String path;

    BossSpriteType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}