package data.map;

/**
 * @author Lillian
 * @Description 地圖
 * @date 2022/2/18 下午 08:17
 */
public abstract class RpgMap {
    /**
     * boss是否還存在
     */
    private boolean isBossAlive;
    /**
     * 腳色在地圖中走了幾步
     */
    private int steps;

    public RpgMap() {
        this.isBossAlive = true;
        this.steps = 0;
    }

    /**
     * 往前走一步
     */
    public void go(){
        this.steps++;
    }

    /**
     * 判斷地圖boss是否活著
     * @return 地圖boss是否活著
     */
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
