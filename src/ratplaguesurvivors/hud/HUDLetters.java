package ratplaguesurvivors.hud;

public enum HUDLetters {
    A('A', "resources/Menu/Letters/LetterA.png"),
    B('B', "resources/Menu/Letters/LetterB.png"),
    C('C', "resources/Menu/Letters/LetterC.png"),
    D('D', "resources/Menu/Letters/LetterD.png"),
    E('E', "resources/Menu/Letters/LetterE.png"),
    F('F', "resources/Menu/Letters/LetterF.png"),
    G('G', "resources/Menu/Letters/LetterG.png"),
    H('H', "resources/Menu/Letters/LetterH.png"),
    I('I', "resources/Menu/Letters/LetterI.png"),
    J('J', "resources/Menu/Letters/LetterJ.png"),
    K('K', "resources/Menu/Letters/LetterK.png"),
    L('L', "resources/Menu/Letters/LetterL.png"),
    M('M', "resources/Menu/Letters/LetterM.png"),
    N('N', "resources/Menu/Letters/LetterN.png"),
    O('O', "resources/Menu/Letters/LetterO.png"),
    P('P', "resources/Menu/Letters/LetterP.png"),
    Q('Q', "resources/Menu/Letters/LetterQ.png"),
    R('R', "resources/Menu/Letters/LetterR.png"),
    S('S', "resources/Menu/Letters/LetterS.png"),
    T('T', "resources/Menu/Letters/LetterT.png"),
    U('U', "resources/Menu/Letters/LetterU.png"),
    V('V', "resources/Menu/Letters/LetterV.png"),
    W('W', "resources/Menu/Letters/LetterW.png"),
    X('X', "resources/Menu/Letters/LetterX.png"),
    Y('Y', "resources/Menu/Letters/LetterY.png"),
    Z('Z', "resources/Menu/Letters/LetterZ.png"),
    SPACE(' ',"resources/Menu/Letters/Space.png");

    private final char letter;
    private final  String path;

    HUDLetters(char letter, String path) {
        this.letter = letter;
        this.path = path;
    }

    public static String getPathForLetter(char letter) {
        char UpperCaseLetter = Character.toUpperCase(letter);
        for (HUDLetters letters : values()) {
            if (letters.letter == UpperCaseLetter) {
                return letters.path;
            }
        }
        return null;
    }

}
