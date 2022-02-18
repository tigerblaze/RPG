package data.creature;

import data.item.*;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/15 下午 03:43
 */
public class Lion extends Enemy{
    private static final int HP = 4;
    private static final int AGILE = 4;
    private static final int STRENGTH = 7;
    private static final int HIT = 5;
    private static final int DEFENSE = 4;
    private static final int LEVEL = 1;
    private static final int EXP = 6;

    public Lion() {
        super(HP, AGILE, STRENGTH, HIT, DEFENSE, LEVEL, EXP, EnemyType.ANIMAL_NORMAL);
        Prop[] props = new Prop[2];
        props[0] = new AnimalSkin();
        props[1] = new LionClaw();
        setDrops(props);
    }

    @Override
    public String toString() {
        return "獅子";
    }

}
