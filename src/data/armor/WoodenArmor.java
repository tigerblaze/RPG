package data.armor;

public class WoodenArmor extends Armor {
    public WoodenArmor() {
        this( 10, 1);
    }

    public WoodenArmor(int weight, int defense) {
        super(weight, defense);
    }
    @Override
    public String toString(){
        return "木甲";
    }
}

