package data.creature;

import data.treasure.prop.Prop;

/**
 * @author Lillian
 * @Description 敵人
 * @date 2022/2/15 下午 03:30
 */
public class Enemy extends Creature{
    /**
     * 會遞落的掉落物
     */
    private Prop[] drops;
    /**
     *  敵人種類
     */
    private EnemyType enemyType;
    /**
     * 是否為主動怪
     */
    private boolean isActive = true;

    public Enemy(int hp, int agile, int strength, int hit, int defense, int level, int exp, EnemyType enemyType) {
        super(hp, agile, strength, hit, defense, level, exp);
        this.enemyType = enemyType;
    }

    public EnemyType getType() {
        return enemyType;
    }

    public void setType(EnemyType enemyType) {
        this.enemyType = enemyType;
    }

    public void setDrops(Prop[] drops){
        this.drops = drops;
    }

    public Prop[] getDrops(){
        return drops;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
