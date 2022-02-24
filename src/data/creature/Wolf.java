package data.creature;

import data.treasure.prop.*;

/**
 * @author Lillian
 * @Description 狼
 * @date 2022/2/15 下午 03:43
 */
public class Wolf extends Enemy{
    /**
     * 原始數值
     */
    private static final int HP = 5;
    private static final int AGILE = 6;
    private static final int STRENGTH = 6;
    private static final int HIT = 5;
    private static final int DEFENSE = 3;
    private static final int LEVEL = 1;
    private static final int EXP = 5;

    public Wolf() {
        super(HP, AGILE, STRENGTH, HIT, DEFENSE, LEVEL, EXP, EnemyType.ANIMAL_NORMAL);
        Prop[] props = new Prop[2];
        props[0] = new AnimalSkin();
        props[1] = new WolfLeg();
        setDrops(props);
    }

    @Override
    public String toString() {
        return "狼";
    }

}
