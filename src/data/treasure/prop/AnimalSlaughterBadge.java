package data.treasure.prop;
public class AnimalSlaughterBadge extends Badge{/*動物屠殺者徽章*/
    public AnimalSlaughterBadge(){
        setPrice(4);
        setHp(0);
        setDefense(0);
        setAttack(1);
        setStrength(0);
        setHit(1);
        setUsage("與動物戰鬥時，+1攻擊力、+1命中");
        setTimes(-1);
        setIsBuff(false);
        setBuffRounds(0);
    }
}