package ratplaguesurvivors.hud;

import ratplaguesurvivors.input.MouseHandler;
import ratplaguesurvivors.input.ScoreLoader;
import ratplaguesurvivors.input.SoundHandler;
import ratplaguesurvivors.interfaces.MouseInputListener;

public class GameMenu implements MouseInputListener {

    private static final String MENU_BUTTON_KEY = "menuButton";

    private ScoreLoader scoreLoader;
    private MouseHandler mouseHandler;
    private HUDComponent backgroundPicture;
    private HUDComponent newGameButton;
    private HUDComponent trophyForLeaderboard;
    private HUDComponent soundButton;

    private SoundHandler soundHandler;

    private boolean leaderboardRequested;
    private boolean startRequested;
    private boolean startButtonHovering;
    private boolean trophyHovering;
    private boolean soundOn;

    private final String leaderboardPath = "resources/Menu/Covers/LeaderBoard.png";
    private final String muteButtonPath = "resources/Menu/Buttons/MuteButton.png";
    private final String soundButtonPath = "resources/Menu/Buttons/SoundButton.png";
    private final String trophyForLeaderboardPath = "resources/Menu/Buttons/TrophyForLeaderboard.png";
    private final String startButtonHoverPath = "resources/Menu/Buttons/NewGameButtonHover.png";
    private final String startButtonPath = "resources/Menu/Buttons/NewGameButton.png";
    private final String backgroundPicturePath = "resources/Menu/Covers/Rat plague survivor.png";


    private final int leadboardX = 500;
    private final int leadboardY = 300;

    private final int nameLeaderboardX = 700;
    private final int nameLeaderboardY = 540;

    private final int killsLeaderboardX= 1100;
    private final int killsLeaderboardY = 540;

    private final int xpLeaderboardX = 1250;
    private final int xpLeaderboardY = 540;

    private final int soundButtonX = 1850;
    private final int soundButtonY = 30;
    private final int soundButtonHitboxX = 1750;
    private final int soundButtonHitboxY = 30;
    private final int soundButtonHitboxWidth = 200;
    private final int soundButtonHitboxHeight = 200;

    private final int buttonX = 745;
    private final int buttonY = 925;
    private final int buttonHoverX = 694;
    private final int buttonHoverY = 895;

    private final int buttonHoverHitboxX = 745;
    private final int buttonHoverHitboxY = 925;
    private final int buttonHoverHitboxWidth = 400;
    private final int buttonHoverHitboxHeight = 137;

    private final int trophyForLeaderboardX = 1750;
    private final int trophyForLeaderboardY = 950;

    private final int trophyForLeaderboardHoverHitboxX = 1722;
    private final int trophyForLeaderboardHoverHitboxY = 925;
    private final int trophyForLeaderboardHoverHitboxWidth = 250;
    private final int trophyForLeaderboardHoverHitboxHeight = 250;

    private int leaderboardKills;
    private int leaderboardXp;
    private String leaderboardName;

    public GameMenu(SoundHandler soundHandler) {
        scoreLoader = new ScoreLoader();
        startRequested = false;
        leaderboardRequested = false;
        startButtonHovering = false;
        trophyHovering = false;
        soundOn = true;
        leaderboardXp = scoreLoader.getXp();
        leaderboardName=scoreLoader.getName();
        leaderboardKills =scoreLoader.getKills();
        this.soundHandler = soundHandler;
    }

    public void show() {
        backgroundPicture = new HUDComponent(0, 0, backgroundPicturePath);
        backgroundPicture.draw();

        newGameButton = new HUDComponent(buttonX, buttonY, startButtonPath);
        newGameButton.draw();

        trophyForLeaderboard = new HUDComponent(trophyForLeaderboardX, trophyForLeaderboardY, trophyForLeaderboardPath);
        trophyForLeaderboard.draw();

        soundButton = new HUDComponent(soundButtonX, soundButtonY, soundButtonPath);
        soundButton.draw();

        soundOn = true;
        leaderboardRequested = false;
        startRequested = false;
        startButtonHovering = false;
        trophyHovering = false;

        mouseHandler = new MouseHandler(this); // só passa a si próprio
        mouseHandler.init();

    }


    public void hide() {
        if (mouseHandler != null) {
            mouseHandler.disableMouse();
            mouseHandler = null;
        }
        if (backgroundPicture != null) {
            backgroundPicture.clear();
            backgroundPicture = null;
        }
        if (newGameButton != null) {
            newGameButton.clear();
            newGameButton = null;
        }
        if (trophyForLeaderboard != null) {
            trophyForLeaderboard.clear();
            trophyForLeaderboard = null;
        }
        if (soundButton != null) {
            soundButton.clear();
            soundButton = null;
        }
        if (soundHandler != null) {
            soundHandler.stop(MENU_BUTTON_KEY);
        }
        startButtonHovering = false;
        trophyHovering = false;
        soundOn = true;
    }

    public boolean isOnStartButton(int mouseX, int mouseY) {
        return mouseX >= buttonHoverHitboxX
                && mouseX <= buttonHoverHitboxX + buttonHoverHitboxWidth
                && mouseY >= buttonHoverHitboxY
                && mouseY <= buttonHoverHitboxY + buttonHoverHitboxHeight;
    }

    public void requestStart() {
        startRequested = true;
    }

    public boolean isStartRequested() {
        return startRequested;
    }

    //
    public void showNormalButton() {
        if (newGameButton == null || !startButtonHovering) {
            return;
        }
        newGameButton.clear();
        newGameButton = new HUDComponent(buttonX, buttonY, startButtonPath);
        newGameButton.draw();
        startButtonHovering = false;
    }

    public void showHoverButton() {
        if (newGameButton == null || startButtonHovering) {
            return;
        }
        newGameButton.clear();
        newGameButton = new HUDComponent(buttonHoverX, buttonHoverY, startButtonHoverPath);
        newGameButton.draw();
        startButtonHovering = true;
    }

    public void resetStartRequest() {

        startRequested = false;

    }

    public boolean isOnTrophyForLeaderboard(int mouseX, int mouseY) {
        return mouseX >= trophyForLeaderboardHoverHitboxX
                && mouseX <= trophyForLeaderboardHoverHitboxX + trophyForLeaderboardHoverHitboxWidth
                && mouseY >= trophyForLeaderboardHoverHitboxY
                && mouseY <= trophyForLeaderboardHoverHitboxY + trophyForLeaderboardHoverHitboxHeight;
    }

    public void LeaderboardRequested() {
        leaderboardRequested = true;
    }

    public boolean isLeaderboardRequested() {
        return leaderboardRequested;
    }

    public void showLeaderboardButton() {

        if (trophyForLeaderboard == null || !trophyHovering) {
            return;
        }

        trophyForLeaderboard.clear();
        trophyForLeaderboard = new HUDComponent(trophyForLeaderboardX, trophyForLeaderboardY, trophyForLeaderboardPath);
        trophyForLeaderboard.draw();
        trophyHovering = false;
    }

    public void showLeaderboardHoverButton() {
        if (trophyForLeaderboard == null || trophyHovering) {
            return;
        }
        trophyForLeaderboard.clear();
        trophyForLeaderboard = new HUDComponent(leadboardX,leadboardY, leaderboardPath);
        trophyForLeaderboard.setNumberTxt(new HUDNumberText(xpLeaderboardX,xpLeaderboardY, 20));
        trophyForLeaderboard.setNumberTxt(new HUDNumberText(killsLeaderboardX,killsLeaderboardY, 20));
        trophyForLeaderboard.setImageTxt(new HUDImageText(nameLeaderboardX,nameLeaderboardY, 20));
        trophyForLeaderboard.updateNumberTxt(new int[]{leaderboardXp,leaderboardKills});
        trophyForLeaderboard.updateImageTxt(leaderboardName);
        trophyForLeaderboard.draw();
        trophyHovering = true;
    }

    public boolean isOnSound(int mouseX, int mouseY) {
        return mouseX >= soundButtonHitboxX
                && mouseX <= soundButtonHitboxX + soundButtonHitboxWidth
                && mouseY >= soundButtonHitboxY
                && mouseY <= soundButtonHitboxY + soundButtonHitboxHeight;
    }

    public void soundChange() {
        soundOn = !soundOn;
    }

    public void getSoundButton() {
        String currentPath = soundOn ? soundButtonPath : muteButtonPath;
        soundButton = new HUDComponent(soundButtonX, soundButtonY, currentPath);
        soundButton.draw();

    }

    public void updateSoundButton() {
        if (soundButton != null) {
            soundButton.clear();
            soundButton = null;
        }
        toogleSound();
        getSoundButton();
    }

    public void toogleSound() {
        soundChange();

        if (!soundOn) {
            soundHandler.mute();
        } else {
            soundHandler.unmute();
            soundHandler.loop("theme");
        }
    }

    public void loadScore(){
        scoreLoader = new ScoreLoader();
        leaderboardKills = scoreLoader.getKills();
        leaderboardName = scoreLoader.getName();
        leaderboardXp = scoreLoader.getXp();
    }

    @Override
    public void onMouseMoved(int x, int y) {
        if (isOnStartButton(x, y)) showHoverButton();
        else showNormalButton();

        if (isOnTrophyForLeaderboard(x, y)) showLeaderboardHoverButton();
        else showLeaderboardButton();
    }

    public void onMouseClicked(int x, int y) {
        if (isOnStartButton(x, y))          { requestStart(); return; }
        if (isOnTrophyForLeaderboard(x, y)) { LeaderboardRequested(); return; }
        if (isOnSound(x, y))                { updateSoundButton(); }
    }
}




