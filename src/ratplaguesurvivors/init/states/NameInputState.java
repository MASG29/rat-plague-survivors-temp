package ratplaguesurvivors.init.states;

import ratplaguesurvivors.init.GameContext;
import ratplaguesurvivors.init.GameState;
import ratplaguesurvivors.interfaces.State;
import ratplaguesurvivors.interfaces.StateSwitcher;

public class NameInputState implements State {

    private final GameContext context;
    private final StateSwitcher stateSwitcher;

    public NameInputState(GameContext context, StateSwitcher stateSwitcher) {
        this.context = context;
        this.stateSwitcher = stateSwitcher;
    }

    @Override
    public void updateState() {
        if (context.getPlayerName().isStartRequested()) {
            context.setNamePlayer(context.getPlayerName().getName());
            context.getPlayerName().clear();
            context.getLoadingScreen().start();
            stateSwitcher.setState(GameState.LOADING);
            context.gameInit();
        }
    }

    @Override
    public void renderState() {
    }
}
