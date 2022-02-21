package data.creature;

import data.treasure.prop.*;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/15 下午 03:43
 */
public class Bahamut extends Enemy {
    private static final int HP = 7;
    private static final int AGILE = 5;
    private static final int STRENGTH = 8;
    private static final int HIT = 7;
    private static final int DEFENSE = 4;
    private static final int LEVEL = 2;
    private static final int EXP = 10;


    public Bahamut() {
        super(HP, AGILE, STRENGTH, HIT, DEFENSE, LEVEL, EXP, EnemyType.MONSTER_BOSS);
        Prop[] props = new Prop[2];
        props[0] = new GhostHeart();
        props[1] = new DemonSlaughterBadge();
        setDrops(props);
    }

    @Override
    public String toString() {
        return "巴哈姆特";
    }
}
