package ratplaguesurvivors.init;

import java.util.ArrayList;

import com.codeforall.online.simplegraphics.graphics.Canvas;

import ratplaguesurvivors.entity.npc.*;
import ratplaguesurvivors.entity.pc.*;
import ratplaguesurvivors.hud.*;
import ratplaguesurvivors.input.*;
import ratplaguesurvivors.interfaces.Collidable;
import ratplaguesurvivors.interfaces.CombatEventListener;
import ratplaguesurvivors.interfaces.StateSwitcher;
import ratplaguesurvivors.map.*;
import ratplaguesurvivors.systems.CombatSystem;

/**
 * Domain objects and per-tick logic shared by every GameState (player, map,
 * hud, spawner, menus, combat...). Player-died/boss-defeated combat events
 * are reported here (not to GameLoop directly) since reacting to them means
 * touching this same state (lvl, render, mapUpdate); GameContext only asks
 * for a GameState transition through the narrow StateSwitcher callback.
 */
public class GameContext implements CombatEventListener {

    private static final long BOSS_SPAWN_DELAY_MS = 30000;

    private PlayableCharacter player;
    private EnemySpawner spawner;
    private Map map;
    private HUD hud;
    private KeyboardHandlers keyboardHandlers;
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
    private long mapTime;
    private MapLevel lvl;
    private int currentHP;
    private final StateSwitcher stateSwitcher;

    public GameContext(StateSwitcher stateSwitcher) {
        this.stateSwitcher = stateSwitcher;
        keyboardHandlers = new KeyboardHandlers();
        collidables = new ArrayList<>();
        gameOverMenu = new GameOverMenu();
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
        Canvas.setMaxX(GameLoop.WINDOW_WIDTH);
        Canvas.setMaxY(GameLoop.WINDOW_HEIGHT);
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

    public void mapUpdate() {
        clearMap();
        map = new Map(lvl, player);
        collidables.addAll(map.getObstacles());
        spawner = new EnemySpawner(map);
        hud = new HUD(player);
        collidables.addAll(hud.getComponents());
        combatSystem = new CombatSystem(player, map, spawner, collidables, this);
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


        if (System.currentTimeMillis() - mapTime > BOSS_SPAWN_DELAY_MS && spawner.getGigaRatMaxNumb() != spawner.getGigaRatsAlive()){
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
        stateSwitcher.setState(GameState.OVER);
    }

    @Override
    public void onBossDefeated() {
        if (lvl == MapLevel.values()[MapLevel.values().length - 1]) {
            stateSwitcher.setState(GameState.OVER);
            return;
        }
        for (int i = 0; i < MapLevel.values().length - 1; i++) {
            if (lvl == MapLevel.values()[i]) {
                lvl = MapLevel.values()[i + 1];
                break;
            }
        }
        stateSwitcher.setState(GameState.LOADING);
        loadingScreen.start();
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
    }

    public void clearMap() {

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

    public GameMenu getGameMenu() {
        return gameMenu;
    }

    public GameOverMenu getGameOverMenu() {
        return gameOverMenu;
    }

    public LoadingScreen getLoadingScreen() {
        return loadingScreen;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public KeyboardHandlers getKeyboardHandlers() {
        return keyboardHandlers;
    }

    public ScoreLoader getScoreLoader() {
        return scoreLoader;
    }

    public PlayableCharacter getPlayer() {
        return player;
    }

    public void setPlayer(PlayableCharacter player) {
        this.player = player;
    }

    public boolean isGameOverMenuVisible() {
        return gameOverMenuVisible;
    }

    public void setGameOverMenuVisible(boolean gameOverMenuVisible) {
        this.gameOverMenuVisible = gameOverMenuVisible;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public void setMapTime(long mapTime) {
        this.mapTime = mapTime;
    }
}
