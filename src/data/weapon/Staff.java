package data.weapon;

public class Staff extends Weapon{
    public Staff() {
        this( 7, -2);
    }

    public Staff(int weight, int attack) {
        super(weight, attack);
    }

    @Override
    public String toString() {
        return "法杖";
    }
}

