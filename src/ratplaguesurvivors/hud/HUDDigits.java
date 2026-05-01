package ratplaguesurvivors.hud;

public enum HUDDigits {
    ZERO('0',"resources/Menu/Numbers/Number0.png"),
    ONE('1',"resources/Menu/Numbers/Number1.png"),
    TWO('2',"resources/Menu/Numbers/Number2.png"),
    THREE('3',"resources/Menu/Numbers/Number3.png"),
    FOUR('4',"resources/Menu/Numbers/Number4.png"),
    FIVE('5',"resources/Menu/Numbers/Number5.png"),
    SIX('6',"resources/Menu/Numbers/Number6.png"),
    SEVEN('7',"resources/Menu/Numbers/Number7.png"),
    EIGHT('8',"resources/Menu/Numbers/Number8.png"),
    NINE('9',"resources/Menu/Numbers/Number9.png");

    private final char number;
    private final String path;

    HUDDigits(char number, String path) {
        this.number = number;
        this.path = path;
    }

    public static String getPathFor(char c) {
        for (HUDDigits digits : HUDDigits.values()) {
            if (digits.number == c) {
                return digits.path;
            }
        }
        return null;
    }

    public char getNumber() {
        return number;
    }
    public String getPath() {
        return path;
    }


}
