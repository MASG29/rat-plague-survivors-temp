package ratplaguesurvivors.input;

import com.codeforall.online.simplegraphics.keyboard.KeyboardHandler;
import com.codeforall.online.simplegraphics.keyboard.Keyboard;
import com.codeforall.online.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.online.simplegraphics.keyboard.KeyboardEventType;

public class KeyboardHandlers implements KeyboardHandler {

    private Keyboard keyboard;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean hpCheat;
    private Directions directions;


    private boolean menuRequested;
    private boolean initialized;
    private PlayerName playerName;


    public KeyboardHandlers() {
        this.directions = Directions.NONE;
        this.menuRequested = false;
        this.initialized = false;
    }

    public void init() {
        if (initialized){
            return;
        }

        keyboard = new Keyboard(this);
        eventKey();
    }

    public void setPlayerName(PlayerName playerName) {
        this.playerName = playerName;
    }


    public void eventKey(){
        int[] keys = {
            KeyboardEvent.KEY_A, KeyboardEvent.KEY_B, KeyboardEvent.KEY_C, KeyboardEvent.KEY_D,
            KeyboardEvent.KEY_E, KeyboardEvent.KEY_F, KeyboardEvent.KEY_G, KeyboardEvent.KEY_H,
            KeyboardEvent.KEY_I, KeyboardEvent.KEY_J, KeyboardEvent.KEY_K, KeyboardEvent.KEY_L,
            KeyboardEvent.KEY_M, KeyboardEvent.KEY_N, KeyboardEvent.KEY_O, KeyboardEvent.KEY_P,
            KeyboardEvent.KEY_Q, KeyboardEvent.KEY_R, KeyboardEvent.KEY_S, KeyboardEvent.KEY_T,
            KeyboardEvent.KEY_U, KeyboardEvent.KEY_V, KeyboardEvent.KEY_W, KeyboardEvent.KEY_X,
            KeyboardEvent.KEY_Y, KeyboardEvent.KEY_Z,
            KeyboardEvent.KEY_SPACE, KeyboardEvent.KEY_ESC, KeyboardEvent.KEY_ENTER,
            KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_LEFT, KeyboardEvent.KEY_RIGHT
        };

        for (int key : keys) {
            KeyboardEvent pressed = new KeyboardEvent();
            pressed.setKey(key);
            pressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(pressed);
        }

        for (int key : keys) {
            KeyboardEvent released = new KeyboardEvent();
            released.setKey(key);
            released.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            keyboard.addEventListener(released);
        }

        initialized = true;
    }
    @Override
    public void keyPressed(KeyboardEvent e) {

        if(playerName != null && playerName.isActive()) {
            handleNameInput(e.getKey());
            return;
        }

        switch (e.getKey()) {

            case KeyboardEvent.KEY_UP:
                up = true;
                break;

            case  KeyboardEvent.KEY_DOWN:
                down = true;
                break;

            case KeyboardEvent.KEY_LEFT:
                left = true;
                break;

            case KeyboardEvent.KEY_RIGHT:
                right = true;
                break;

            case KeyboardEvent.KEY_W:
                up = true;
                break;

            case KeyboardEvent.KEY_S:
                down = true;
                break;

            case KeyboardEvent.KEY_A:
                left = true;
                break;

            case KeyboardEvent.KEY_D:
                right = true;
                break;

            case KeyboardEvent.KEY_ESC:
                menuRequested = true;
                break;
            case KeyboardEvent.KEY_I:
                hpCheat = !hpCheat;
                break;
        }

        updateDirection();
    }

    @Override
    public void keyReleased(KeyboardEvent e) {

        if(playerName != null && playerName.isActive()) {
            return;
        }

        switch (e.getKey()) {

            case KeyboardEvent.KEY_UP:
                up = false;
                break;


            case KeyboardEvent.KEY_DOWN:
                down = false;
                break;

            case KeyboardEvent.KEY_LEFT:
                left = false;
                break;

            case KeyboardEvent.KEY_RIGHT:
                right = false;
                break;

            case KeyboardEvent.KEY_W:
                up = false;
                break;

            case KeyboardEvent.KEY_S:
                down = false;
                break;

            case KeyboardEvent.KEY_A:
                left = false;
                break;

            case KeyboardEvent.KEY_D:
                right = false;
                break;
        }

        updateDirection();
    }

    private void handleNameInput(int key) {

        switch(key){


            case KeyboardEvent.KEY_A -> playerName.addLetter('A');

            case KeyboardEvent.KEY_B -> playerName.addLetter('B');

            case KeyboardEvent.KEY_C -> playerName.addLetter('C');

            case KeyboardEvent.KEY_D -> playerName.addLetter('D');

            case KeyboardEvent.KEY_E -> playerName.addLetter('E');

            case KeyboardEvent.KEY_F -> playerName.addLetter('F');

            case KeyboardEvent.KEY_G -> playerName.addLetter('G');

            case KeyboardEvent.KEY_H -> playerName.addLetter('H');

            case KeyboardEvent.KEY_I -> playerName.addLetter('I');

            case KeyboardEvent.KEY_J -> playerName.addLetter('J');

            case KeyboardEvent.KEY_K -> playerName.addLetter('K');

            case KeyboardEvent.KEY_L -> playerName.addLetter('L');

            case KeyboardEvent.KEY_M -> playerName.addLetter('M');

            case KeyboardEvent.KEY_N -> playerName.addLetter('N');

            case KeyboardEvent.KEY_O -> playerName.addLetter('O');

            case KeyboardEvent.KEY_P -> playerName.addLetter('P');

            case KeyboardEvent.KEY_Q -> playerName.addLetter('Q');

            case KeyboardEvent.KEY_R -> playerName.addLetter('R');

            case KeyboardEvent.KEY_S -> playerName.addLetter('S');

            case KeyboardEvent.KEY_T -> playerName.addLetter('T');

            case KeyboardEvent.KEY_U -> playerName.addLetter('U');

            case KeyboardEvent.KEY_V -> playerName.addLetter('V');

            case KeyboardEvent.KEY_W -> playerName.addLetter('W');

            case KeyboardEvent.KEY_X -> playerName.addLetter('X');

            case KeyboardEvent.KEY_Y -> playerName.addLetter('Y');

            case KeyboardEvent.KEY_Z -> playerName.addLetter('Z');

            case KeyboardEvent.KEY_SPACE -> playerName.addSpacing();

            case KeyboardEvent.KEY_ESC -> playerName.deleteLastLetter();

            case KeyboardEvent.KEY_ENTER -> playerName.requestStart();
        }
    }

    private void updateDirection() {

        if (up) {
            directions = Directions.UP;
        } else if (down) {
            directions = Directions.DOWN;
        } else if (left) {
            directions = Directions.LEFT;
        } else if (right) {
            directions = Directions.RIGHT;
        } else {
            directions = Directions.NONE;
        }
    }
    public boolean isMenuRequested() {
        if(!menuRequested){
            return false;
        }
        menuRequested = false;
        return true;
    }


    public Directions getDirection() {
        return directions;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public boolean isHpCheat() {
        return hpCheat;
    }

    public void setDirection(Directions directions) {
        this.directions = directions;}




}




