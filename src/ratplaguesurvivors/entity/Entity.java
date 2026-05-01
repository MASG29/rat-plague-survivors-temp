package ratplaguesurvivors.entity;


import ratplaguesurvivors.interfaces.Collidable;
import ratplaguesurvivors.utils.Position;


abstract public class Entity implements Collidable{

    private String name;
    private int currentHP;
    private Position position;
    private Position hitbox;

    public Entity(String name, Position position, int health, Position hitbox) {
        this.name = name;
        this.position = position;
        this.currentHP = health;
        this.hitbox = hitbox;
    }

    public int getHealth(){
        return currentHP;
    }

    public void takeDamage(int damage){
        currentHP -= damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public Position getPos() {
        return position;
    }



    public boolean isAlive() {
        if (getHealth() <= 0){
            return false;
        }else{
            return true;
        }
    }

    public Position getHitbox() {
        return hitbox;
    }


    public void setHitbox(int dx, int dy, int width, int height){
        this.hitbox = new Position(dx, dy, width, height);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
