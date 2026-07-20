package ratplaguesurvivors.init.states;

import ratplaguesurvivors.init.GameContext;
import ratplaguesurvivors.init.GameState;
import ratplaguesurvivors.interfaces.State;
import ratplaguesurvivors.interfaces.StateSwitcher;

public class MenuState implements State {

    private final GameContext context;
    private final StateSwitcher stateSwitcher;

    public MenuState(GameContext context, StateSwitcher stateSwitcher) {
        this.context = context;
        this.stateSwitcher = stateSwitcher;
    }

    @Override
    public void updateState() {
        if (context.getGameMenu().isStartRequested()) {
            context.getGameMenu().hide();
            context.getGameMenu().resetStartRequest();
            context.getPlayerName().show();
            stateSwitcher.setState(GameState.NAME_INPUT);
        }
    }

    @Override
    public void renderState() {
    }
}
