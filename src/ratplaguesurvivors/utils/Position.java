package ratplaguesurvivors.utils;


public class Position {

    private int x;
    private int y;
    private int h;
    private int w;
    private int width;
    private int height;

    public Position(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.w = x + width;
        this.h = y + height;
    }

    public Position(Position pos){
        x = pos.x;
        y = pos.y;
        width = pos.width;
        height = pos.height;
        w = pos.w;
        h = pos.h;
    }

    public void translate(int dx, int dy){
        x += dx;
        y += dy;
        w += dx;
        h += dy;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getW(){
        return w;
    }

    public int getH(){
        return h;
    }

    public int getWidth()  { 
        return width; 
    }

    public int getHeight() { 
        return height; 
    }



    public void setX(int newX) {
        this.x = newX;
        this.w = newX + width;
    }

    public void setY(int y){
        this.y = y;
        h = y + height;
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + "), (" + w + ", " + h + ")"; 
    }
}
