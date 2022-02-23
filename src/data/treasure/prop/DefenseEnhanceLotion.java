package data.treasure.prop;
public class DefenseEnhanceLotion extends Prop{/*防禦增強藥水*/
    public DefenseEnhanceLotion(){
        setPrice(2);
        setHp(0);
        setDefense(2);
        setAttack(0);
        setStrength(0);
        setHit(0);
        setUsage("+2防禦 持續兩次攻擊，戰鬥結束後消失");
        setTimes(2);
        setIsBuff(true);
        setBuffRounds(2);
    }
}