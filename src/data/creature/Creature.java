package data.creature;

/**
 * @author Lillian
 * @Description 生物類，生物的基本屬性
 * @date 2022/2/14 下午 12:21
 */
public class Creature {
    protected int hp;
    protected int agile;
    protected int strength;
    protected int hit;
    protected int defense;
    protected int level;
    protected int exp;

    public Creature(int hp,int agile,int strength,int hit,int defense,
                    int level, int exp){
        this.hp = hp;
        this.agile = agile;
        this.strength = strength;
        this.hit = hit;
        this.defense = defense;
        this.level = level;
        this.exp = exp;
    }

    public int getHp() {
        return hp;
    }

    /**
     * 若hp小於0，為0
     * @param newHp 新hp
     */
    public void setHp(int newHp) {
        if(newHp<0){
            this.hp = 0;
        }else {
            this.hp = newHp;
        }
    }

    /**
     * 回傳敏捷
     * @return
     */
    public int getAgile() {
        return agile;
    }

    /**
     * 設置敏捷
     * @param agile
     */
    public void setAgile(int agile) {
        this.agile = agile;
    }

    /**
     *回傳力量
     * * @return
     */
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
