package ratplaguesurvivors.interfaces;

public interface EnemyAnimationController {
    void update();
    void setDirection(int dx, int dy);
    void startAttackAnim();
    default boolean wasAttackJustFinished() {
        return false;
    }
}
