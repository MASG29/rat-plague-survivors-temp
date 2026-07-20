package ratplaguesurvivors.init.states;

import ratplaguesurvivors.init.GameContext;
import ratplaguesurvivors.init.GameState;
import ratplaguesurvivors.interfaces.State;
import ratplaguesurvivors.interfaces.StateSwitcher;

public class LoadingState implements State {

    private final GameContext context;
    private final StateSwitcher stateSwitcher;

    public LoadingState(GameContext context, StateSwitcher stateSwitcher) {
        this.context = context;
        this.stateSwitcher = stateSwitcher;
    }

    @Override
    public void updateState() {
        if (context.getLoadingScreen().update()) {
            context.mapUpdate();
            context.render();
            stateSwitcher.setState(GameState.GAME);
            context.setMapTime(System.currentTimeMillis());
        }
    }

    @Override
    public void renderState() {
    }
}
