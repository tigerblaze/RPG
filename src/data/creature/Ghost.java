package data.creature;

import data.treasure.prop.*;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/15 下午 03:43
 */
public class Ghost extends Enemy{
    private static final int HP = 3;
    private static final int AGILE = 4;
    private static final int STRENGTH = 6;
    private static final int HIT = 4;
    private static final int DEFENSE = 3;
    private static final int LEVEL = 1;
    private static final int EXP = 5;

    public Ghost() {
        super(HP, AGILE, STRENGTH, HIT, DEFENSE, LEVEL, EXP, EnemyType.MONSTER_NORMAL);
        Prop[] props = new Prop[2];
        props[0] = new GhostHeart();
        props[1] = new Glass();
        setDrops(props);
    }

    @Override
    public String toString() {
        return "魑";
    }

}
