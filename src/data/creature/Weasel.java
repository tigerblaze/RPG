package data.creature;

import data.item.*;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/15 下午 03:43
 */
public class Weasel extends Enemy{
    private static final int HP = 4;
    private static final int AGILE = 5;
    private static final int STRENGTH = 6;
    private static final int HIT = 5;
    private static final int DEFENSE = 3;
    private static final int LEVEL = 1;
    private static final int EXP = 5;

    public Weasel() {
        super(HP, AGILE, STRENGTH, HIT, DEFENSE, LEVEL, EXP, EnemyType.MONSTER_NORMAL);
        Prop[] props = new Prop[2];
        props[0] = new GhostHeart();
        props[1] = new AnimalSkin();
        setDrops(props);
    }

    @Override
    public String toString() {
        return "黃大仙";
    }

}
