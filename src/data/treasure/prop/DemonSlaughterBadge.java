package data.treasure.prop;

/**
 * 惡魔屠殺者徽章，編號Time：-1 此編號是決定它存在徽章欄位的index計算方式是 times * (-1)-1
 * 所以這個會存在accessories[0];
 *
 */
public class DemonSlaughterBadge extends Badge{
    public DemonSlaughterBadge(){
        setPrice(4);
        setHp(0);
        setDefense(0);
        setAttack(1);
        setStrength(0);
        setHit(1);
        setTimes(-1);
        setUsage("與魔物戰鬥時，+1攻擊力、+1命中");
        setIsBuff(false);
        setBuffRounds(0);
        setName("惡魔屠殺者徽章");
    }
}