package tests;

import ratplaguesurvivors.init.GameLoop;

public class TestMapGen {
    public static void main(String[] args) throws InterruptedException {
        GameLoop start = new GameLoop();
        start.init();
        start.start();
    }
}
