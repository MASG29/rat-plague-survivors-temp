import ratplaguesurvivors.init.GameLoop;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        GameLoop start = new GameLoop();
        start.init();
        start.start();
        
    }
}
