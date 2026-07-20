package ratplaguesurvivors.init.states;

import ratplaguesurvivors.entity.pc.PlayableCharacter;
import ratplaguesurvivors.init.GameContext;
import ratplaguesurvivors.init.GameState;
import ratplaguesurvivors.interfaces.State;
import ratplaguesurvivors.interfaces.StateSwitcher;
import ratplaguesurvivors.output.ScoreWriter;

public class GameOverState implements State {

    private final GameContext context;
    private final StateSwitcher stateSwitcher;

    public GameOverState(GameContext context, StateSwitcher stateSwitcher) {
        this.context = context;
        this.stateSwitcher = stateSwitcher;
    }

    @Override
    public void updateState() {
        if (!context.isGameOverMenuVisible()) {
            PlayableCharacter player = context.getPlayer();
            int score = player.getKillCounter() + player.getLvl().getCurrentXp();
            if (context.getScoreLoader().getScore() < score) {
                ScoreWriter scoreWriter = new ScoreWriter();
                scoreWriter.write(player.getName(), score, player.getKillCounter(), player.getLvl().getCurrentXp());
            }
            context.clearMap();
            context.setPlayer(null);
            context.getGameOverMenu().show();
            context.setGameOverMenuVisible(true);
        }
        if (context.getGameOverMenu().isTryAgainRequested()) {
            context.restartGame();
            stateSwitcher.setState(GameState.GAME);
        }
    }

    @Override
    public void renderState() {
    }
}
