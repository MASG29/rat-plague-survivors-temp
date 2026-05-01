package ratplaguesurvivors.utils;

import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;

public class Hitbox {
    private Rectangle hitbox;
    private int x;
    private int y;
    private int width;
    private int height;

    public Hitbox(int x, int y, int width, int height) {
        this.hitbox = new Rectangle(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void show(){
        hitbox.setColor(Color.GREEN);
        //hitbox.fill();
    }

    public void showBorder(){
        hitbox.setColor(Color.GREEN);
        hitbox.draw();
    }

    public void translate(int dx, int dy) {
        hitbox.translate(dx, dy);
        this.x += dx;
        this.y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void hide(){

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void delete(){
        hitbox.delete();
    }

}
