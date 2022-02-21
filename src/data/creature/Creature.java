package data.creature;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/14 下午 12:21
 */
public class Creature {
    private int hp;
    private int agile;
    private int strength;
    private int hit;
    private int defense;
    private int level;
    private int exp;

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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAgile() {
        return agile;
    }

    public void setAgile(int agile) {
        this.agile = agile;
    }

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

    public boolean isDead(){
        return this.hp<=0;
    }

}
