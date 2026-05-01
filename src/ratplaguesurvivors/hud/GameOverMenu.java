package ratplaguesurvivors.hud;

import com.codeforall.online.simplegraphics.pictures.Picture;
import ratplaguesurvivors.input.MouseHandler;

public class GameOverMenu {

    private MouseHandler mouseHandler;


    private Picture backgroundOverPicture;
    private Picture tryAgainButton;
    private Picture quitButton;



    private boolean tryAgainRequested;
    private boolean isQuitRequested;
    private boolean isHoveringTryAgain;
    private boolean isHoveringQuit;

    private final String backgroundOverPicturePath = "resources/Menu/Covers/GameOver.png";
    private final String tryAgainButtonPath = "resources/Menu/Buttons/TryAgainButton.png";
    private final String tryAgainButtonHoverPath = "resources/Menu/Buttons/TryAgainButtonHover.png";
    private final String quitButtonPath = "resources/Menu/Buttons/QuitButton.png";
    private final String quitButtonHoverPath = "resources/Menu/Buttons/QuitButtonHover.png";

    private final int tryAgainButtonX = 500;
    private final int tryAgainButtonY = 750;
    private final int tryAgainButtonHoverX = 451;
    private final int tryAgainButtonHoverY = 720;

    private final int quitButtonX = 1000;
    private final int quitButtonY = 730;
    private final int quitButtonHoverX = 951;
    private final int quitButtonHoverY = 700;

    private final int tryAgainButtonHoverHitBoxX = 500;
    private final int tryAgainButtonHoverHitBoxY = 750;
    private final int tryAgainButtonHoverHitBoxWidth = 400;
    private final int tryAgainButtonHoverHitBoxHeight = 167;

    private final int quitButtonHoverHitboxHoverX = 950;
    private final int quitButtonHoverHitboxHoverY = 705;
    private final int quitButtonHoverHitboxHoverWidth = 500;
    private final int quitButtonHoverHitboxHoverHeight = 254;

    public GameOverMenu() {
        resetRequested();
        isHoveringQuit = false;
        isHoveringTryAgain = false;

    }

    public void show() {

        resetRequested();
        isHoveringQuit = false;
        isHoveringTryAgain = false;

        mouseHandler = new MouseHandler(null, this,null);
        mouseHandler.init();

        backgroundOverPicture = new Picture(0, 0, backgroundOverPicturePath);
        backgroundOverPicture.draw();

        tryAgainButton = new Picture(tryAgainButtonX, tryAgainButtonY, tryAgainButtonPath);
        tryAgainButton.draw();

        quitButton = new Picture(quitButtonX, quitButtonY, quitButtonPath);
        quitButton.draw();
    }

    public void hide() {
        if (mouseHandler != null) {
            mouseHandler.disableMouse();
            mouseHandler = null;
        }
        if (backgroundOverPicture != null) {
            backgroundOverPicture.delete();
            backgroundOverPicture = null;
        }
        if (tryAgainButton != null) {
            tryAgainButton.delete();
            tryAgainButton = null;
        }
        if (quitButton != null) {
            quitButton.delete();
            quitButton = null;
        }
        isHoveringQuit = false;
        isHoveringTryAgain = false;
    }

    public boolean isOnTryAgainRequested(int mouseX, int mouseY) {
        return mouseX >= tryAgainButtonHoverHitBoxX
                && mouseX <= tryAgainButtonHoverHitBoxX + tryAgainButtonHoverHitBoxWidth
                && mouseY >= tryAgainButtonHoverHitBoxY
                && mouseY <= tryAgainButtonHoverHitBoxY + tryAgainButtonHoverHitBoxHeight;
    }

    public boolean isOnQuitRequested(int mouseX, int mouseY) {
        return mouseX >= quitButtonHoverHitboxHoverX
                && mouseX <= quitButtonHoverHitboxHoverX + quitButtonHoverHitboxHoverWidth
                && mouseY >= quitButtonHoverHitboxHoverY
                && mouseY <= quitButtonHoverHitboxHoverY + quitButtonHoverHitboxHoverHeight;
    }

    public void showNormalTryAgainButton() {
        if (tryAgainButton == null || !isHoveringTryAgain) {
            return;
        }
        tryAgainButton.delete();
        tryAgainButton = new Picture(tryAgainButtonX, tryAgainButtonY, tryAgainButtonPath);
        tryAgainButton.draw();
        isHoveringTryAgain = false;
    }

    public void showHoveringTryAgainButton() {
        if (tryAgainButton == null || isHoveringTryAgain) {
            return;
        }
        tryAgainButton.delete();
        tryAgainButton = new Picture(tryAgainButtonHoverX, tryAgainButtonHoverY, tryAgainButtonHoverPath);
        tryAgainButton.draw();
        isHoveringTryAgain = true;
    }

    public void showNormalQuitButton() {
        if (quitButton == null || !isHoveringQuit) {
            return;
        }
        quitButton.delete();
        quitButton = new Picture(quitButtonX, quitButtonY, quitButtonPath);
        quitButton.draw();
        isHoveringQuit = false;
    }

    public void showHoveringQuitButton() {
        if (quitButton == null || isHoveringQuit) {
            return;
        }
        quitButton.delete();
        quitButton = new Picture(quitButtonHoverX, quitButtonHoverY, quitButtonHoverPath);
        quitButton.draw();
        isHoveringQuit = true;
    }

    public void requestTryAgain() {
        tryAgainRequested = true;
    }

    public void requestQuit() {
        isQuitRequested = true;
        System.exit(0);
    }

    public boolean isTryAgainRequested() {
        return tryAgainRequested;
    }

    public void resetRequested() {
        isQuitRequested = false;
        tryAgainRequested = false;
    }

}
