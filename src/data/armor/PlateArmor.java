package data.armor;

public class PlateArmor extends Armor {
    public PlateArmor() {
        this( 20, 4);
    }

    public PlateArmor(int weight, int defense) {
        super(weight, defense);
    }

    @Override
    public String toString() {
        return "板甲";
    }
}
