package data.treasure.armor;

public class ChainArmor extends Armor {
    public ChainArmor() {
        this( 15, 2);
    }

    public ChainArmor(int weight, int defense) {
        super(weight, defense);
    }

    @Override
    public String toString() {
        return "鎖子甲";
    }
}

