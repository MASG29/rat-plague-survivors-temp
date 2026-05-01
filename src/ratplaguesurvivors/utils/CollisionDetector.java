package ratplaguesurvivors.utils;


public class CollisionDetector {

    static public boolean hasCollided(Position a, Position b){
        return a.getX() < b.getW() &&
               a.getW() > b.getX() &&
               a.getY() < b.getH() &&
               a.getH() > b.getY();
    }

    static public  boolean hitboxHasCollided(Hitbox a, Hitbox b){
        return a.getX() < b.getX() + b.getWidth() &&
                a.getX() + a.getWidth() > b.getX() &&
                a.getY() < b.getY() + b.getHeight() &&
                a.getY() + a.getHeight() > b.getY();
    }

}
