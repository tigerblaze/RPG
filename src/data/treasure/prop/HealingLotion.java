package data.treasure.prop;

/**
 * 治療藥水
 */
public class HealingLotion extends Prop{
    public HealingLotion(){
        setPrice(3);
        setHp(3);
        setDefense(0);
        setAttack(0);
        setStrength(0);
        setHit(0);
        setUsage("回復3滴血量");
        setTimes(0);
        setIsBuff(false);
        setBuffRounds(0);
        setName("治療藥水");
    }
}