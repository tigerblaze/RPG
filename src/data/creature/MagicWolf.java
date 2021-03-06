package data.creature;

import data.treasure.prop.*;

/**
 * @author Lillian
 * @Description 魔狼
 * @date 2022/2/15 下午 03:43
 */
public class MagicWolf extends Enemy{
    /**
     * 原始數值
     */
    private static final int HP = 2;
    private static final int AGILE = 4;
    private static final int STRENGTH = 7;
    private static final int HIT = 5;
    private static final int DEFENSE = 2;
    private static final int LEVEL = 1;
    private static final int EXP = 5;

    public MagicWolf() {
        super(HP, AGILE, STRENGTH, HIT, DEFENSE, LEVEL, EXP, EnemyType.MONSTER_NORMAL);
        Prop[] props = new Prop[2];
        props[0] = new GhostHeart();
        props[1] = new WolfTooth();
        setDrops(props);
    }

    @Override
    public String toString() {
        return "魔狼";
    }

}
