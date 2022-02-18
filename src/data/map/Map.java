package data.map;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/18 下午 08:17
 */
public abstract class Map {
    private boolean isBossAlive;

    public Map() {
        this.isBossAlive = true;
    }

    public boolean isBossAlive() {
        return isBossAlive;
    }

    public void setBossAlive(boolean bossAlive) {
        isBossAlive = bossAlive;
    }
}
