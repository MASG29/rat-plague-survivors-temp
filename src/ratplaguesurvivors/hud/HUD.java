package ratplaguesurvivors.hud;

import java.util.ArrayList;

import com.codeforall.online.simplegraphics.graphics.Rectangle;

import ratplaguesurvivors.entity.pc.PlayableCharacter;
import ratplaguesurvivors.init.GameLoop;

public class HUD {

    private PlayableCharacter player;
    private int xpToNextLevel;
    private int currentXp;
    private int ratKillCount;
    private int mouseKillCount;

    private final String killScoreboardPath = "resources/Menu/HUD/KillScoreboard.png";
    private final String xpDisplayPath = "resources/Menu/HUD/XPHud.png";
    private final String backgroundPicturePath = "resources/Menu/HUD/BackgroundHud.png";
    
    private HUDComponent backgroundPicture;
    private HUDComponent xpDisplay;
    private HUDComponent killScoreboard;

    private ArrayList<HUDComponent> components;

    private final int screenWidth = GameLoop.WINDOW_WIDTH;
    private final int screenHeight = GameLoop.WINDOW_HEIGHT;

    private final int killScoreboardLocationX = 5;
    private final int killScoreboardLocationY = 5;
    private final int killCountRatLocationX = 127;
    private final int killCountRatLocationY = 170;
    private final int killCountMouseLocationX = 290;
    private final int killCountMouseLocationY = 170;
    private final int XpDisplayLocationX = screenWidth - 400;
    private final int XpDisplayLocationY = screenHeight - 1080;
    private final int currentXpLocationX = screenWidth - 185;
    private final int currentXpLocationY = screenHeight - 975;
    private final int xpToNextLevelLocationX =  screenWidth - 185;
    private final int xpToNextLevelLocationY = screenHeight - 950;
    private final int backgroundPictureLocationX = screenWidth - 510;
    private final int backgroundPictureLocationY = screenHeight - 200;
    private final int healthBarLocationX = screenWidth - 465;
    private final int healthBarLocationY = screenHeight - 72;
    private final int levelNumberLocationX = screenWidth - 110;
    private final int levelNumberLocationY = screenHeight - 60;
    private final int nameLocationX = screenWidth - 379;
    private final int nameLocationY = screenHeight - 112;

    private final int hpBarWidth = 330;
    private final int hpBarHeight = 45;

    public HUD(PlayableCharacter player) {

        this.player = player;
        this.xpToNextLevel = player.getLvl().getXpToLVL();
        this.currentXp = player.getLvl().getCurrentXp();
        this.ratKillCount = 0;
        this.mouseKillCount = 0;
        components = new ArrayList<>();
        init();
    }

    private void init() {
        xpDisplay = new HUDComponent(XpDisplayLocationX, XpDisplayLocationY, xpDisplayPath);
        xpDisplay.setNumberTxt(new HUDNumberText(xpToNextLevelLocationX, xpToNextLevelLocationY, 20));
        xpDisplay.setNumberTxt(new HUDNumberText(currentXpLocationX, currentXpLocationY, 20));
        xpDisplay.updateNumberTxt(new int[]{xpToNextLevel, currentXp});

        backgroundPicture = new HUDComponent(backgroundPictureLocationX, backgroundPictureLocationY, backgroundPicturePath);
        backgroundPicture.setBar(new Rectangle(healthBarLocationX, healthBarLocationY, hpBarWidth, hpBarHeight), player.getCurrentHP());
        backgroundPicture.setImageTxt(new HUDImageText(nameLocationX, nameLocationY, 23));
        backgroundPicture.updateImageTxt(player.getName());
        backgroundPicture.setNumberTxt(new HUDNumberText(levelNumberLocationX, levelNumberLocationY, 20));
        backgroundPicture.updateNumberTxt(player.getLvl().getCurrentLvl());

        killScoreboard = new HUDComponent(killScoreboardLocationX, killScoreboardLocationY, killScoreboardPath);
        killScoreboard.setNumberTxt(new HUDNumberText(killCountMouseLocationX, killCountMouseLocationY, 20));
        killScoreboard.setNumberTxt(new HUDNumberText(killCountRatLocationX, killCountRatLocationY, 20));
        killScoreboard.updateNumberTxt(new int[]{mouseKillCount, ratKillCount});

        components.add(xpDisplay);
        components.add(backgroundPicture);
        components.add(killScoreboard);

    }

    public void draw(){
        xpDisplay.draw();
        backgroundPicture.draw();
        killScoreboard.draw();
    }


    public void updateHud() {
        updateKillCounter();
        updateXpDisplay();
        updateBg();
    }

    private void updateKillCounter(){
        killScoreboard.updateNumberTxt(new int[]{player.getMouseKillCount(), player.getRatKillCount()});
    }

    private void updateXpDisplay(){
        xpDisplay.updateNumberTxt(new int[]{player.getLvl().getXpToLVL(), player.getLvl().getCurrentXp()});
    }

    private void updateBg(){
        backgroundPicture.updateNumberTxt(player.getLvl().getCurrentLvl());
        backgroundPicture.updateBar(player.getCurrentHP());
    }

    public void clearHud() {
        backgroundPicture.clear();
        xpDisplay.clear();
        killScoreboard.clear();
    }

    public ArrayList<HUDComponent> getComponents() {
        return components;
    }

}