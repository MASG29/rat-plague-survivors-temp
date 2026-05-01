package ratplaguesurvivors.hud;

import com.codeforall.online.simplegraphics.pictures.Picture;

public class LoadingScreen {

    private final String loadingScreenPath = "resources/Menu/Buttons/HowToMove.png";
    private static final long DURATION_MIllIS = 5000;
    private final int loadingScreenX = 0;
    private final int loadingScreenY = 0;
    private Picture loadingScreen;

    private long startTime;


    private boolean active;

    public LoadingScreen() {

    }
    public void start(){
        stop();
        startTime = System.currentTimeMillis();
        active = true;
        this.loadingScreen = new Picture(loadingScreenX,loadingScreenY, loadingScreenPath);
        loadingScreen.draw();
        this.active = true;

    }

    public boolean update(){
        if(!active){
            return false;
        }
        long elapsedTime = System.currentTimeMillis() - startTime;

        if(elapsedTime >= DURATION_MIllIS){
            stop();
            return true;
        }
        return false;
    }

    public void stop() {
        if (loadingScreen != null) {
            loadingScreen.delete();
            loadingScreen = null;
        }
        active = false;
    }

    public boolean isActive(){
            return active;
    }
}
