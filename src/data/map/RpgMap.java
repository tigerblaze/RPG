package data.map;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/18 下午 08:17
 */
public abstract class RpgMap {
    private boolean isBossAlive;
    private int steps;

    public RpgMap() {
        this.isBossAlive = true;
        this.steps = 0;
    }
    public void go(){
        this.steps++;
    }

    public boolean isBossAlive() {
        return isBossAlive;
    }

    public void setBossAlive(boolean bossAlive) {
        isBossAlive = bossAlive;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
