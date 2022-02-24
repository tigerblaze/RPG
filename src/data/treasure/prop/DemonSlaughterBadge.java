package data.treasure.prop;

/**
 * 惡魔屠殺者徽章
 */
public class DemonSlaughterBadge extends Badge{
    public DemonSlaughterBadge(){
        setPrice(4);
        setHp(0);
        setDefense(0);
        setAttack(1);
        setStrength(0);
        setHit(1);
        setUsage("與魔物戰鬥時，+1攻擊力、+1命中");
        setIsBuff(false);
        setBuffRounds(0);
        setName("惡魔屠殺者徽章");
    }
}