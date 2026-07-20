package ratplaguesurvivors.init;

import static ratplaguesurvivors.init.GameState.LOADING;
import static ratplaguesurvivors.init.GameState.OVER;

import java.util.ArrayList;

import com.codeforall.online.simplegraphics.graphics.Canvas;

import ratplaguesurvivors.entity.npc.*;
import ratplaguesurvivors.entity.pc.*;
import ratplaguesurvivors.hud.*;
import ratplaguesurvivors.input.*;
import ratplaguesurvivors.interfaces.Collidable;
import ratplaguesurvivors.interfaces.CombatEventListener;
import ratplaguesurvivors.map.*;
import ratplaguesurvivors.output.ScoreWriter;
import ratplaguesurvivors.systems.CombatSystem;


public class GameLoop implements CombatEventListener {

    public static final int WINDOW_WIDTH = 1920;
    public static final int WINDOW_HEIGHT = 1080;

    private PlayableCharacter player;
    private EnemySpawner spawner;
    private Map map;
    private HUD hud;
    private KeyboardHandlers keyboardHandlers;
    private GameState gameState;
    private ArrayList<Collidable> collidables;
    private CombatSystem combatSystem;
    private GameMenu gameMenu;
    private GameOverMenu gameOverMenu;
    private boolean gameOverMenuVisible;
    private LoadingScreen loadingScreen;
    private SoundHandler soundHandler;
    private PlayerName playerName;
    private String namePlayer;

    private ScoreLoader scoreLoader;
    private ScoreWriter scoreWriter;
    private long mapTime;
    private MapLevel lvl;
    private int currentHP;

    public GameLoop() {
        keyboardHandlers = new KeyboardHandlers();
        collidables = new ArrayList<>();
        gameOverMenu = new GameOverMenu();
        gameState = GameState.MENU;
        gameOverMenuVisible = false;
        loadingScreen = new LoadingScreen();
        soundHandler = new SoundHandler();
        gameMenu = new GameMenu(soundHandler);
        playerName = new PlayerName();
        namePlayer = playerName.getName();
        keyboardHandlers.setPlayerName(playerName);
        scoreLoader = new ScoreLoader();
        lvl = MapLevel.LEVEL1;
    }

    public void init() {
        Canvas.setMaxX(WINDOW_WIDTH);
        Canvas.setMaxY(WINDOW_HEIGHT);
        gameMenu.show();
        gameOverMenu.hide();
        keyboardHandlers.init();
        soundHandler.loadSound("theme", "resources/sound/v3-8bits.wav");
        soundHandler.loop("theme");
    }



    public void gameInit() {
        gameMenu.loadScore();
        clearMap();
        player = new PlayableCharacter(PCType.CAT);
        player.setName(namePlayer);
    }

    private void mapUpdate(){
        clearMap();
        map = new Map(lvl, player);
        collidables.addAll(map.getObstacles());
        spawner = new EnemySpawner(map);
        hud = new HUD(player);
        collidables.addAll(hud.getComponents());
        combatSystem = new CombatSystem(player, map, spawner, collidables, this);
    }

    public void start() throws InterruptedException {

        while (true) {

            switch (gameState) {
                case MENU:
                    if (gameMenu.isStartRequested()) {
                        gameMenu.hide();
                        gameMenu.resetStartRequest();
                        playerName.show();
                        gameState = GameState.NAME_INPUT;
                    }
                    break;

                case NAME_INPUT:
                    if(playerName.isStartRequested()){
                        namePlayer = playerName.getName();
                        playerName.clear();
                        loadingScreen.start();
                        gameState = GameState.LOADING;
                        gameInit();
                    }
                    break;

                case LOADING:
                    if(loadingScreen.update()){
                        mapUpdate();
                        render();
                        gameState = GameState.GAME;
                        mapTime = System.currentTimeMillis();
                    }
                    break;

                case GAME:
                    if(keyboardHandlers.isMenuRequested()){
                        clearMap();
                        gameMenu.show();
                        gameState = GameState.MENU;
                        break;
                    }
                    update();
                   break;

                case OVER:
                    if(!gameOverMenuVisible) {
                        int score = player.getKillCounter() + player.getLvl().getCurrentXp();
                        if (scoreLoader.getScore() < score){
                            scoreWriter = new ScoreWriter();
                            scoreWriter.write(player.getName(), score, player.getKillCounter(), player.getLvl().getCurrentXp());
                        }
                        clearMap();
                        player = null;
                        gameOverMenu.show();
                        gameOverMenuVisible = true;
                    }
                    if (gameOverMenu.isTryAgainRequested()) {
                        restartGame();
                    }
                    break;
            }
            Thread.sleep(10);

        }
    }

    public void update() throws InterruptedException {
        Directions dir = keyboardHandlers.getDirection();
        Enemy lastAdded;

        if (keyboardHandlers.isHpCheat() && player.getCurrentHP() <= player.getPcType().getBaseHealth()){
            currentHP = player.getCurrentHP();
            player.takeDamage(-9999999);
        }
        else if (!keyboardHandlers.isHpCheat() && player.getCurrentHP() > player.getPcType().getBaseHealth()){
            player.takeDamage(player.getCurrentHP() - currentHP);
        }

        combatSystem.resolveTick(dir);
        player.updateAnimation();
        player.attack(dir);


        if (System.currentTimeMillis() - mapTime > 30000 && spawner.getGigaRatMaxNumb() != spawner.getGigaRatsAlive()){
            spawner.spawn(EnemyType.GIGARAT, map);
        }
        else{
            spawner.spawn(map);
        }
        player.getBaseAttack().update();
        spawner.removeDeadEnemies();
        if (spawner.getEnemyGroup().size() > 0){
            lastAdded = spawner.getEnemyGroup().get(spawner.getEnemyGroup().size() - 1);

            if (!collidables.contains(lastAdded)){
                collidables.add(lastAdded);
            }
        }

        hud.updateHud();

    }

    public void render() {
        map.draw();
        player.draw();
        hud.draw();
    }

    @Override
    public void onPlayerDied() {
        setState(OVER);
    }

    @Override
    public void onBossDefeated() {
        if (lvl == MapLevel.values()[MapLevel.values().length - 1]) {
            setState(OVER);
            return;
        }
        for (int i = 0; i < MapLevel.values().length - 1; i++) {
            if (lvl == MapLevel.values()[i]) {
                lvl = MapLevel.values()[i + 1];
                break;
            }
        }
        setState(LOADING);
        loadingScreen.start();
    }

    public void setState(GameState state) {
        gameState = state;
    }


    public void restartGame() {
        if (gameOverMenu != null) {
            gameOverMenu.hide();
        }
        gameOverMenu = new GameOverMenu();
        gameOverMenuVisible = false;
        gameInit();
        lvl = MapLevel.LEVEL1;
        mapUpdate();
        render();
        gameState = GameState.GAME;
    }

    private void clearMap(){

        if (hud != null) {
            hud.clearHud();
            hud = null;
        }
        if (player != null) {
            if (player.getBaseAttack() != null && player.getBaseAttack().getWeaponSprite() != null) {
                player.getBaseAttack().getWeaponSprite().delete();
            }
            if (player.getSprite() != null) {
                player.getSprite().delete();
            }
        }
        if (spawner != null) {
            for (Enemy enemy : spawner.getEnemyGroup()) {
                if (enemy.getSprite() != null) {
                    enemy.getSprite().delete();
                }
            }
            spawner.clear();
            spawner = null;
        }
        if (map != null && map.getObstacles() != null) {
            map.getObstacles().clear();
            map = null;
        }
        collidables.clear();
    }

}






