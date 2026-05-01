package ratplaguesurvivors.map;

import java.util.ArrayList;

import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;
import com.codeforall.online.simplegraphics.pictures.Picture;

import ratplaguesurvivors.entity.pc.PlayableCharacter;
import ratplaguesurvivors.init.GameLoop;
import ratplaguesurvivors.interfaces.Collidable;
import ratplaguesurvivors.interfaces.Moves;
import ratplaguesurvivors.map.obstacles.Obstacle;
import ratplaguesurvivors.map.obstacles.ObstacleType;
import ratplaguesurvivors.utils.CollisionDetector;
import ratplaguesurvivors.utils.Position;
import ratplaguesurvivors.utils.Util;

public class Map implements Moves, Collidable{
    
    private MapLevel diffLvl;
    private Picture map;
    private ArrayList<Obstacle> obstacles;
    private int obArea;
    private int mapArea;
    private Position pos;
    private Rectangle bg;
    PlayableCharacter player;

    public Map(MapLevel diffLvl, PlayableCharacter player){
        this.diffLvl = diffLvl;
        this.player = player;
        map = new Picture(MapLevel.MAP_POS.getX(), MapLevel.MAP_POS.getY(), diffLvl.getPath());
        bg = new Rectangle(0,0, GameLoop.WINDOW_WIDTH + 10, GameLoop.WINDOW_HEIGHT + 10);
        bg.setColor(Color.BLACK);
        bg.fill();
        mapArea = map.pixels();
        initObstacles(diffLvl.getObsCount());
        pos = new Position(MapLevel.MAP_POS);
    }

    private void initObstacles(int n){
        obstacles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            obstacles.add(new Obstacle(ObstacleType.values()[Util.rand(ObstacleType.values().length)]));
            obArea += obstacles.get(i).getPixels();
        }
        if (obArea > mapArea * .2) {
            obArea = 0;
            initObstacles(n / 2);
            return;
        } else {
            for (int i = 0; i < obstacles.size(); i++) {
                if (overlapCheck(obstacles.get(i))) {
                    i = 0;
                    continue;
                }
            }
        }
    }

    private boolean overlapCheck(Obstacle current){
        for (int i = obstacles.indexOf(current) + 1; i < obstacles.size(); i++){

            if (current.hasCollided(obstacles.get(i)) || CollisionDetector.hasCollided(current.getPos(), player.getPos())){
                current.collided();
                return true;
            }
            
        }
        return false;
    }

    public void draw(){
        map.draw();
        for (Obstacle obs : obstacles){
            obs.draw();
        }
    }

    public MapLevel getLevel(){
        return diffLvl;
    }

    public ArrayList<Obstacle> getObstacles(){
        return obstacles;
    }

    public Position getPos(){
        return pos;
    }

    public Position getPos(Position colPos){
        return new Position(pos.getX() + colPos.getWidth(), pos.getY() + colPos.getHeight(), 
                pos.getWidth() - colPos.getWidth() * 2, pos.getHeight() - colPos.getHeight() * 2);
    }

    @Override
    public void move(int dx, int dy) {
        pos.translate(dx, dy);
        map.translate(dx, dy);
        for (Obstacle obs : obstacles){
            obs.move(dx, dy);
        }
    }

    @Override
    public Position getHitbox() {
        return getPos();
    }

    @Override
    public boolean hasCollided(Collidable obj2) {
        return CollisionDetector.hasCollided(getPos(obj2.getHitbox()), obj2.getHitbox());
    }

    @Override
    public void collided(Collidable obj2) {
        
    }

}
