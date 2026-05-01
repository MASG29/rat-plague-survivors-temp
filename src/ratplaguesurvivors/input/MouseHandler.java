package ratplaguesurvivors.input;

import com.codeforall.online.simplegraphics.mouse.Mouse;
import com.codeforall.online.simplegraphics.mouse.MouseEvent;
import com.codeforall.online.simplegraphics.mouse.MouseEventType;
import ratplaguesurvivors.hud.GameMenu;
import ratplaguesurvivors.hud.GameOverMenu;

public class MouseHandler implements com.codeforall.online.simplegraphics.mouse.MouseHandler {

    private Mouse mouse;
    private GameMenu gameMenu;
    private GameOverMenu gameOverMenu;
    private PlayerName  playerName;

    public MouseHandler(GameMenu gameMenu,GameOverMenu gameOverMenu,PlayerName playerName) {
        this.gameMenu = gameMenu;
        this.gameOverMenu = gameOverMenu;
        this.playerName = playerName;
    }

    public void init() {
        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    public void disableMouse() {
        if (mouse != null) {
            mouse.removeEventListener(MouseEventType.MOUSE_MOVED);
            mouse.removeEventListener(MouseEventType.MOUSE_CLICKED);
            mouse = null;
        }
    }
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        if(gameMenu !=null) {
            if (gameMenu.isOnStartButton(mouseX, mouseY)) {
                gameMenu.showHoverButton();
            } else {
                gameMenu.showNormalButton();
            }
            if (gameMenu.isOnTrophyForLeaderboard(mouseX, mouseY)) {
                gameMenu.showLeaderboardHoverButton();
            } else {
                gameMenu.showLeaderboardButton();
            }
        }
        if(gameOverMenu !=null) {

            if (gameOverMenu.isOnTryAgainRequested(mouseX, mouseY)) {
                gameOverMenu.showHoveringTryAgainButton();
            } else {
                gameOverMenu.showNormalTryAgainButton();
            }
            if (gameOverMenu.isOnQuitRequested(mouseX, mouseY)) {
                gameOverMenu.showHoveringQuitButton();
            } else {
                gameOverMenu.showNormalQuitButton();
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int mouseX = (int) mouseEvent.getX();
        int mouseY = (int) mouseEvent.getY();

        if(gameMenu !=null) {
            if (gameMenu.isOnStartButton(mouseX, mouseY)) {
                gameMenu.requestStart();
                return;
            }
            if (gameMenu.isOnTrophyForLeaderboard(mouseX, mouseY)) {
                gameMenu.LeaderboardRequested();
                return;
            }
            if(gameMenu.isOnSound(mouseX, mouseY)){
                gameMenu.updateSoundButton();
                return;
            }
        }
        if(gameOverMenu !=null) {
            if (gameOverMenu.isOnQuitRequested(mouseX, mouseY)) {
                gameOverMenu.requestQuit();
            }
            if (gameOverMenu.isOnTryAgainRequested(mouseX, mouseY)) {
                gameOverMenu.requestTryAgain();
            }
        }
    if(playerName!=null && playerName.isActive()) {
        if (playerName.isOnStartButton(mouseX, mouseY)) {
            playerName.requestStart();
        }
    }}

}