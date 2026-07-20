package ratplaguesurvivors.entity.pc;

public class Lvl {
    private int currentXp;
    private int currentLvl;
    private int maxLvl = 100;
    private int xpToLvl;
    private int baseXpNeeded = 20;

    public Lvl(){
        this.currentLvl = 1;
        this.currentXp = 0;
        resetXpToLvl();
    }

    /* Whenever xp is added, checks whether the xp needed for a level up has reached
    zero; if so it consumes it from currentXp and increments the level, which also
    recalculates the xp needed for the next level up.
     */
    public void addXp(int ammount){
        currentXp += ammount;
        resetXpToLvl();
        if (xpToLvl <= 0){
            currentLvl++;
            resetXpToLvl();
        }
    }

    private void resetXpToLvl(){
        xpToLvl = exponential(baseXpNeeded, currentLvl) - currentXp;
    }

    private int exponential(int base, int n){
        if (n == 1){
            return base;
        }
        return base * exponential(base, n - 1);
    }


    public int getCurrentXp() {
        return currentXp;
    }

    public void setCurrentXp(int currentXp) {
        this.currentXp = currentXp;
    }

    public int getCurrentLvl() {
        return currentLvl;
    }

    public void setCurrentLvl(int currentLvl) {
        this.currentLvl = currentLvl;
    }

    public int getMaxLvl() {
        return maxLvl;
    }

    public void setMaxLvl(int maxLvl) {
        this.maxLvl = maxLvl;
    }

    public int getXpToLvl() {
        return xpToLvl;
    }

    public void setXpToLvl(int xpToLvl) {
        this.xpToLvl = xpToLvl;
    }

}
