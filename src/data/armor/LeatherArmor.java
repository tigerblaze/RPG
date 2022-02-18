package data.armor;

public class LeatherArmor extends Armor {
    public LeatherArmor() {
        this( 9, 2);
    }

    public LeatherArmor(int weight, int defense) {
        super(weight, defense);
    }

    @Override
    public String toString() {
        return "皮甲";
    }
}

