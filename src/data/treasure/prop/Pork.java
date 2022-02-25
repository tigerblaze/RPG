package data.treasure.prop;

/**
 * 豬肉
 */
public class Pork extends Prop{
    public Pork(){
        setPrice(2);
        setHp(2);
        setDefense(-1);
        setAttack(0);
        setStrength(0);
        setHit(0);
        setUsage("回復2滴血量、-1防禦");
        setIsBuff(false);
        setBuffRounds(0);
        setName("豬肉");
    }
}