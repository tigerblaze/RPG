package data.weapon;

public class Sword extends Weapon{
    public Sword() {
        this( 10, 2);
    }

    public Sword(int weight, int attack) {
        super(weight, attack);
    }

    @Override
    public String toString() {
        return "劍";
    }
}

