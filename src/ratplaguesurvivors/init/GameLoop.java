package ratplaguesurvivors.init;

import java.util.EnumMap;

import ratplaguesurvivors.init.states.*;
import ratplaguesurvivors.interfaces.State;
import ratplaguesurvivors.interfaces.StateSwitcher;

/**
 * Composition root and driver: owns the while(true)/Thread.sleep tick and
 * the current GameState -> State mapping. Domain objects and per-state
 * behaviour live in GameContext / ratplaguesurvivors.init.states.
 */
public class GameLoop implements StateSwitcher {

    public static final int WINDOW_WIDTH = 1920;
    public static final int WINDOW_HEIGHT = 1080;

    private final GameContext context;
    private final EnumMap<GameState, State> states;
    private State currentState;

    public GameLoop() {
        context = new GameContext(this);
        states = new EnumMap<>(GameState.class);
        states.put(GameState.MENU, new MenuState(context, this));
        states.put(GameState.NAME_INPUT, new NameInputState(context, this));
        states.put(GameState.LOADING, new LoadingState(context, this));
        states.put(GameState.GAME, new PlayingState(context, this));
        states.put(GameState.OVER, new GameOverState(context, this));
        currentState = states.get(GameState.MENU);
    }

    public void init() {
        context.init();
    }

    public void start() throws InterruptedException {
        while (true) {
            currentState.updateState();
            Thread.sleep(10);
        }
    }

    @Override
    public void setState(GameState state) {
        currentState = states.get(state);
    }
}
