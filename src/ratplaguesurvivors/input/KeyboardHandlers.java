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
        KeyboardEvent a = new KeyboardEvent();
        a.setKey(KeyboardEvent.KEY_A);
        a.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent b = new KeyboardEvent();
        b.setKey(KeyboardEvent.KEY_B);
        b.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent c = new KeyboardEvent();
        c.setKey(KeyboardEvent.KEY_C);
        c.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent d = new KeyboardEvent();
        d.setKey(KeyboardEvent.KEY_D);
        d.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent e = new KeyboardEvent();
        e.setKey(KeyboardEvent.KEY_E);
        e.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent f = new KeyboardEvent();
        f.setKey(KeyboardEvent.KEY_F);
        f.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent g = new KeyboardEvent();
        g.setKey(KeyboardEvent.KEY_G);
        g.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent h = new KeyboardEvent();
        h.setKey(KeyboardEvent.KEY_H);
        h.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent i = new KeyboardEvent();
        i.setKey(KeyboardEvent.KEY_I);
        i.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent j = new KeyboardEvent();
        j.setKey(KeyboardEvent.KEY_J);
        j.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent k = new KeyboardEvent();
        k.setKey(KeyboardEvent.KEY_K);
        k.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent l = new KeyboardEvent();
        l.setKey(KeyboardEvent.KEY_L);
        l.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent m = new KeyboardEvent();
        m.setKey(KeyboardEvent.KEY_M);
        m.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent n = new KeyboardEvent();
        n.setKey(KeyboardEvent.KEY_N);
        n.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent o = new KeyboardEvent();
        o.setKey(KeyboardEvent.KEY_O);
        o.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent p = new KeyboardEvent();
        p.setKey(KeyboardEvent.KEY_P);
        p.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent q = new KeyboardEvent();
        q.setKey(KeyboardEvent.KEY_Q);
        q.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent r = new KeyboardEvent();
        r.setKey(KeyboardEvent.KEY_R);
        r.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent s = new KeyboardEvent();
        s.setKey(KeyboardEvent.KEY_S);
        s.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent t = new KeyboardEvent();
        t.setKey(KeyboardEvent.KEY_T);
        t.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent u = new KeyboardEvent();
        u.setKey(KeyboardEvent.KEY_U);
        u.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent v = new KeyboardEvent();
        v.setKey(KeyboardEvent.KEY_V);
        v.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent w = new KeyboardEvent();
        w.setKey(KeyboardEvent.KEY_W);
        w.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent x = new KeyboardEvent();
        x.setKey(KeyboardEvent.KEY_X);
        x.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent y = new KeyboardEvent();
        y.setKey(KeyboardEvent.KEY_Y);
        y.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent z = new KeyboardEvent();
        z.setKey(KeyboardEvent.KEY_Z);
        z.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent esc = new KeyboardEvent();
        esc.setKey(KeyboardEvent.KEY_ESC);
        esc.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent enter = new KeyboardEvent();
        enter.setKey(KeyboardEvent.KEY_ENTER);
        enter.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent aRel = new KeyboardEvent();
        aRel.setKey(KeyboardEvent.KEY_A);
        aRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent bRel = new KeyboardEvent();
        bRel.setKey(KeyboardEvent.KEY_B);
        bRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent cRel = new KeyboardEvent();
        cRel.setKey(KeyboardEvent.KEY_C);
        cRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent dRel = new KeyboardEvent();
        dRel.setKey(KeyboardEvent.KEY_D);
        dRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent eRel = new KeyboardEvent();
        eRel.setKey(KeyboardEvent.KEY_E);
        eRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent fRel = new KeyboardEvent();
        fRel.setKey(KeyboardEvent.KEY_F);
        fRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent gRel = new KeyboardEvent();
        gRel.setKey(KeyboardEvent.KEY_G);
        gRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent hRel = new KeyboardEvent();
        hRel.setKey(KeyboardEvent.KEY_H);
        hRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent iRel = new KeyboardEvent();
        iRel.setKey(KeyboardEvent.KEY_I);
        iRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent jRel = new KeyboardEvent();
        jRel.setKey(KeyboardEvent.KEY_J);
        jRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent kRel = new KeyboardEvent();
        kRel.setKey(KeyboardEvent.KEY_K);
        kRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent lRel = new KeyboardEvent();
        lRel.setKey(KeyboardEvent.KEY_L);
        lRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent mRel = new KeyboardEvent();
        mRel.setKey(KeyboardEvent.KEY_M);
        mRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent nRel = new KeyboardEvent();
        nRel.setKey(KeyboardEvent.KEY_N);
        nRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent oRel = new KeyboardEvent();
        oRel.setKey(KeyboardEvent.KEY_O);
        oRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent pRel = new KeyboardEvent();
        pRel.setKey(KeyboardEvent.KEY_P);
        pRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent qRel = new KeyboardEvent();
        qRel.setKey(KeyboardEvent.KEY_Q);
        qRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent rRel = new KeyboardEvent();
        rRel.setKey(KeyboardEvent.KEY_R);
        rRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent sRel = new KeyboardEvent();
        sRel.setKey(KeyboardEvent.KEY_S);
        sRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent tRel = new KeyboardEvent();
        tRel.setKey(KeyboardEvent.KEY_T);
        tRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent uRel = new KeyboardEvent();
        uRel.setKey(KeyboardEvent.KEY_U);
        uRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent vRel = new KeyboardEvent();
        vRel.setKey(KeyboardEvent.KEY_V);
        vRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent wRel = new KeyboardEvent();
        wRel.setKey(KeyboardEvent.KEY_W);
        wRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent xRel = new KeyboardEvent();
        xRel.setKey(KeyboardEvent.KEY_X);
        xRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent yRel = new KeyboardEvent();
        yRel.setKey(KeyboardEvent.KEY_Y);
        yRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent zRel = new KeyboardEvent();
        zRel.setKey(KeyboardEvent.KEY_Z);
        zRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent spaceRel = new KeyboardEvent();
        spaceRel.setKey(KeyboardEvent.KEY_SPACE);
        spaceRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent escRel = new KeyboardEvent();
        escRel.setKey(KeyboardEvent.KEY_ESC);
        escRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent enterRel = new KeyboardEvent();
        enterRel.setKey(KeyboardEvent.KEY_ENTER);
        enterRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent upRel = new KeyboardEvent();
        upRel.setKey(KeyboardEvent.KEY_UP);
        upRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent downRel = new KeyboardEvent();
        downRel.setKey(KeyboardEvent.KEY_DOWN);
        downRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent leftRel = new KeyboardEvent();
        leftRel.setKey(KeyboardEvent.KEY_LEFT);
        leftRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent rightRel = new KeyboardEvent();
        rightRel.setKey(KeyboardEvent.KEY_RIGHT);
        rightRel.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        keyboard.addEventListener(a);
        keyboard.addEventListener(b);
        keyboard.addEventListener(c);
        keyboard.addEventListener(d);
        keyboard.addEventListener(e);
        keyboard.addEventListener(f);
        keyboard.addEventListener(g);
        keyboard.addEventListener(h);
        keyboard.addEventListener(i);
        keyboard.addEventListener(j);
        keyboard.addEventListener(k);
        keyboard.addEventListener(l);
        keyboard.addEventListener(m);
        keyboard.addEventListener(n);
        keyboard.addEventListener(o);
        keyboard.addEventListener(p);
        keyboard.addEventListener(q);
        keyboard.addEventListener(r);
        keyboard.addEventListener(s);
        keyboard.addEventListener(t);
        keyboard.addEventListener(u);
        keyboard.addEventListener(v);
        keyboard.addEventListener(w);
        keyboard.addEventListener(x);
        keyboard.addEventListener(y);
        keyboard.addEventListener(z);
        keyboard.addEventListener(space);
        keyboard.addEventListener(esc);
        keyboard.addEventListener(enter);
        keyboard.addEventListener(up);
        keyboard.addEventListener(down);
        keyboard.addEventListener(left);
        keyboard.addEventListener(right);

        keyboard.addEventListener(aRel);
        keyboard.addEventListener(bRel);
        keyboard.addEventListener(cRel);
        keyboard.addEventListener(dRel);
        keyboard.addEventListener(eRel);
        keyboard.addEventListener(fRel);
        keyboard.addEventListener(gRel);
        keyboard.addEventListener(hRel);
        keyboard.addEventListener(iRel);
        keyboard.addEventListener(jRel);
        keyboard.addEventListener(kRel);
        keyboard.addEventListener(lRel);
        keyboard.addEventListener(mRel);
        keyboard.addEventListener(nRel);
        keyboard.addEventListener(oRel);
        keyboard.addEventListener(pRel);
        keyboard.addEventListener(qRel);
        keyboard.addEventListener(rRel);
        keyboard.addEventListener(sRel);
        keyboard.addEventListener(tRel);
        keyboard.addEventListener(uRel);
        keyboard.addEventListener(vRel);
        keyboard.addEventListener(wRel);
        keyboard.addEventListener(xRel);
        keyboard.addEventListener(yRel);
        keyboard.addEventListener(zRel);
        keyboard.addEventListener(spaceRel);
        keyboard.addEventListener(escRel);
        keyboard.addEventListener(enterRel);
        keyboard.addEventListener(upRel);
        keyboard.addEventListener(downRel);
        keyboard.addEventListener(leftRel);
        keyboard.addEventListener(rightRel);

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




