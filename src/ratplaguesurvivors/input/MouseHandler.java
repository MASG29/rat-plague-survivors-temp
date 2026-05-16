package ratplaguesurvivors.input;

import com.codeforall.online.simplegraphics.mouse.Mouse;
import com.codeforall.online.simplegraphics.mouse.MouseEvent;
import com.codeforall.online.simplegraphics.mouse.MouseEventType;
import ratplaguesurvivors.interfaces.MouseInputListener;

import java.util.ArrayList;
import java.util.List;

public class MouseHandler implements com.codeforall.online.simplegraphics.mouse.MouseHandler {

    private Mouse mouse;
    private final List<MouseInputListener> listeners = new ArrayList<>();

    public MouseHandler(MouseInputListener... listeners) {
        for (MouseInputListener l : listeners) {
            if (l != null) this.listeners.add(l);
        }
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
    public void mouseMoved(MouseEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        for (MouseInputListener l : listeners) l.onMouseMoved(x, y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = (int) e.getX(), y = (int) e.getY();
        for (MouseInputListener l : listeners) l.onMouseClicked(x, y);
    }
}