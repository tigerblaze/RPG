package data.creature;

import data.treasure.prop.*;

/**
 * @author Lillian
 * @Description 野豬
 * @date 2022/2/15 下午 03:43
 */
public class Boar extends Enemy{
    /**
     * 原始數值
     */
    private static final int HP = 6;
    private static final int AGILE = 4;
    private static final int STRENGTH = 5;
    private static final int HIT = 4;
    private static final int DEFENSE = 7;
    private static final int LEVEL = 1;
    private static final int EXP = 5;

    public Boar() {
        super(HP, AGILE, STRENGTH, HIT, DEFENSE, LEVEL, EXP, EnemyType.ANIMAL_NORMAL);
        Prop[] props = new Prop[2];
        props[0] = new AnimalSkin();
        props[1] = new Pork();
        setDrops(props);
    }

    @Override
    public String toString() {
        return "山豬";
    }
}
