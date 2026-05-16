package ratplaguesurvivors.entity;

import com.codeforall.online.simplegraphics.pictures.Picture;

public  abstract class BaseAnimationController {

    protected static final int TICKS_PER_FRAME = 8;
    protected int currentFrame;
    protected int tickCount;
    protected Picture sprite;


    public BaseAnimationController(Picture sprite) {
        this.currentFrame = 0;
        this.tickCount = 0;
        this.sprite = sprite;
    }
    public void update(){
        tickCount++;
        if (tickCount >= TICKS_PER_FRAME) {
            tickCount = 0;
            advanceframe();
        }
    }

    public abstract void advanceframe();
}