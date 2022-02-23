package data.treasure.prop;
public class StrengthEnhanceLotion extends Prop{/*力量增強藥水*/
    public StrengthEnhanceLotion(){
        setPrice(2);
        setHp(0);
        setDefense(0);
        setAttack(0);
        setStrength(2);
        setHit(0);
        setUsage("+2力量 持續兩次攻擊，戰鬥結束後消失");
        setTimes(2);
        setIsBuff(true);
        setBuffRounds(2);
    }
}