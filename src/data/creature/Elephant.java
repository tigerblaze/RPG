package data.creature;

import data.treasure.prop.*;

/**
 * @author Lillian
 * @Description 大象
 * @date 2022/2/15 下午 03:43
 */
public class Elephant extends Enemy{
    /**
     * 原始數值
     */
    private static final int HP = 10;
    private static final int AGILE = 3;
    private static final int STRENGTH = 6;
    private static final int HIT = 7;
    private static final int DEFENSE = 7;
    private static final int LEVEL = 2;
    private static final int EXP = 10;

    public Elephant() {
        super(HP, AGILE, STRENGTH, HIT, DEFENSE, LEVEL, EXP, EnemyType.ANIMAL_BOSS);
        Prop[] props = new Prop[2];
        props[0] = new AnimalSkin();
        props[1] = new AnimalSlaughterBadge();
        setDrops(props);
    }

    @Override
    public String toString() {
        return "大象";
    }

}
