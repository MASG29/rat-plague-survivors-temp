package ratplaguesurvivors.attacks;

import com.codeforall.online.simplegraphics.pictures.Picture;
import ratplaguesurvivors.interfaces.Attacks;
import ratplaguesurvivors.interfaces.Collidable;
import ratplaguesurvivors.utils.*;

public abstract class BaseAttack implements Attacks, Collidable {
    private int radius;
    private int range;
    private double baseAtkSpd = 1;
    private int baseDmg = 5;
    protected Position attackHitbox;



    public BaseAttack(int dmg, double atkSpeed){
        baseDmg = dmg;
        baseAtkSpd = atkSpeed;
    }

    public abstract void update();

    public abstract Picture getWeaponSprite();

    public abstract Position getAttackHitbox();

    public abstract boolean isAttacking();

    public int getRadius() {
        return radius;
    }

    public int getRange() {
        return range;
    }

    public int getBaseDmg() {
        return baseDmg;
    }

    @Override
    public Position getPos() {
        return getHitbox();
    }

    @Override
    public Position getHitbox() {
        return attackHitbox;
    }

    @Override
    public boolean hasCollided(Collidable obj2) {
        return CollisionDetector.hasCollided(this.getHitbox(), obj2.getHitbox());
    }


}
