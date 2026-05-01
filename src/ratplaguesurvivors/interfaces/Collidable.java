package ratplaguesurvivors.interfaces;

import ratplaguesurvivors.utils.Position;

public interface Collidable {
    Position getPos();
    Position getHitbox();
    boolean hasCollided(Collidable obj2);
    void collided(Collidable obj2);
}
