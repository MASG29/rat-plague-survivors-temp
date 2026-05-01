package ratplaguesurvivors.entity.pc;

public class Lvl {
    private int currentXp;
    private int currentLvl;
    private int maxLvl = 100;
    private int xpToLVL;
    private int baseXpNeeded = 20;

    public Lvl(){
        this.currentLvl = 1;
        this.currentXp = 0;
        resetXpToLVL();
    }

    /*Sempre que adiciona xp verifica se o xp necessario para dar lvl up é 0 ou
    maior e se for ele retira o xp necessario para dar lvl up ao currentxp e incrementa
    o lvl aumentando assim tambem o xp necessario para dar lvl up.
     */
    public void addXp(int ammount){
        currentXp += ammount;
        resetXpToLVL();
        if (xpToLVL <= 0){
            currentLvl++;
            resetXpToLVL();
        }
    }

    private void resetXpToLVL(){
        xpToLVL = exponential(baseXpNeeded, currentLvl) - currentXp;
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

    public int getXpToLVL() {
        return xpToLVL;
    }

    public void setXpToLVL(int xpToLVL) {
        this.xpToLVL = xpToLVL;
    }

}
