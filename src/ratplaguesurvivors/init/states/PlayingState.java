package ratplaguesurvivors.init.states;

import ratplaguesurvivors.init.GameContext;
import ratplaguesurvivors.init.GameState;
import ratplaguesurvivors.interfaces.State;
import ratplaguesurvivors.interfaces.StateSwitcher;

public class PlayingState implements State {

    private final GameContext context;
    private final StateSwitcher stateSwitcher;

    public PlayingState(GameContext context, StateSwitcher stateSwitcher) {
        this.context = context;
        this.stateSwitcher = stateSwitcher;
    }

    @Override
    public void updateState() throws InterruptedException {
        if (context.getKeyboardHandlers().isMenuRequested()) {
            context.clearMap();
            context.getGameMenu().show();
            stateSwitcher.setState(GameState.MENU);
            return;
        }
        context.update();
    }

    @Override
    public void renderState() {
    }
}
