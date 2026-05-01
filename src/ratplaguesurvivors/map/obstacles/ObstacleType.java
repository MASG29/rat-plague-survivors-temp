package ratplaguesurvivors.map.obstacles;

public enum ObstacleType {
    SMALL_PILLAR("resources/map/obstacles/smallpillar.png", 0, 0, 0, -32),
    GRATE("resources/map/obstacles/grate.png", 10, 14, -20, -68),
    RUINS_WALL("resources/map/obstacles/RuinsWall.png", 0, 0, 0, -56);

    private String oPath;
    private int offsetX;
    private int offsetY;
    private int offsetW;
    private int offsetH;

    ObstacleType(String oPath, int offsetX, int offsetY, int offsetW, int offsetH){
        this.oPath = oPath;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetW = offsetW;
        this.offsetH = offsetH;
    }

    public String getPath(){
        return oPath;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getOffsetW() {
        return offsetW;
    }

    public int getOffsetH() {
        return offsetH;
    }

    @Override
    public String toString(){
        return oPath + ": " + offsetX + ", " + offsetY + "/ " + offsetW + ", " + offsetH;
    }
}
