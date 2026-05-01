package ratplaguesurvivors.map;

import ratplaguesurvivors.utils.Position;

public enum MapLevel {
    LEVEL1("resources/map/terrain/LEVEL1_terrain.png", 20, 100, 99, 0, 1),
    LEVEL2("resources/map/terrain/LEVEL2_terrain.png", 19, 120, 59, 60, 1),
    LEVEL3("resources/map/terrain/LEVEL3_terrain.png", 50, 150, 0, 149, 1);

    static public final Position MAP_POS = new Position(0, 0, 2880, 2880);

    private String mPath;
    private int obsCount;
    private int maxEnemyCount;
    private int maxMouse;
    private int maxRat;
    private int maxBoss;

    MapLevel(String mPath, int obsCount, int maxEnemyCount, int maxMouse, int maxRat, int maxBoss){
        this.mPath = mPath;
        this.obsCount = obsCount;
        this.maxEnemyCount = maxEnemyCount;
        this.maxMouse = maxMouse;
        this.maxRat = maxRat;
        this.maxBoss = maxBoss;
    }

    public String getPath(){
        return mPath;
    }

    public int getObsCount(){
        return obsCount;
    }

    public int getMaxEnemyCount() {
        return maxEnemyCount;
    }

    public int getMaxMouse() {
        return maxMouse;
    }

    public int getMaxRat() {
        return maxRat;
    }

    public int getMaxBoss() {
        return maxBoss;
    }

}
