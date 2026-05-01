package ratplaguesurvivors.map.obstacles;

import com.codeforall.online.simplegraphics.pictures.Picture;

import ratplaguesurvivors.interfaces.Collidable;
import ratplaguesurvivors.interfaces.Moves;
import ratplaguesurvivors.map.MapLevel;
import ratplaguesurvivors.utils.CollisionDetector;
import ratplaguesurvivors.utils.Position;
import ratplaguesurvivors.utils.Util;

public class Obstacle implements Collidable, Moves{
    private Position pos;
    private Position hitbox;
    private Picture body;
    private String bodyPath;
    private ObstacleType type;
    private int obArea;

    public Obstacle (ObstacleType type){
        this.type = type;
        this.bodyPath = this.type.getPath();
        Picture tmp = new Picture(0, 0, bodyPath);
        body = new Picture(Util.rand(MapLevel.MAP_POS.getW() - tmp.getWidth()), 
                Util.rand(MapLevel.MAP_POS.getW() - tmp.getHeight()), bodyPath);
        obArea = body.pixels();
        pos = new Position(body.getX(), body.getY(), body.getWidth(), body.getHeight());
        hitbox = new Position(pos.getX() + type.getOffsetX(), pos.getY() + type.getOffsetY(), 
                pos.getWidth() + type.getOffsetW(), pos.getHeight() + type.getOffsetH());
    }


    public void collided(){
        pos.setX(Util.rand(MapLevel.MAP_POS.getW() - body.getWidth()));
        pos.setY(Util.rand(MapLevel.MAP_POS.getH() - body.getHeight()));
        hitbox = new Position(pos.getX() + type.getOffsetX(), pos.getY() + type.getOffsetY(),
            pos.getWidth() + type.getOffsetW(), pos.getHeight() + type.getOffsetH());
        body = new Picture(pos.getX(), pos.getY(), bodyPath);
    }

    public void draw(){
        body.draw();
    }

    public int getPixels(){
        return obArea;
    }

    @Override
    public Position getPos() {
        return pos;
    }

    @Override
    public boolean hasCollided(Collidable obj2) {
        return CollisionDetector.hasCollided(getPos(), obj2.getPos());
    }

    @Override
    public void collided(Collidable obj2) {
        if (!(obj2 instanceof Obstacle)){
            return;
        }
        collided();
    }


    @Override
    public void move(int dx, int dy) {
        pos.translate(dx, dy);
        hitbox.translate(dx, dy);
        body.translate(dx, dy);
    }


    @Override
    public Position getHitbox() {
        return hitbox;
    }

}
