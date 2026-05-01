package ratplaguesurvivors.interfaces;

import ratplaguesurvivors.input.Directions;
import ratplaguesurvivors.utils.Position;

public interface Attacks {

    void attack(Position position, Directions direction) throws InterruptedException;

}
