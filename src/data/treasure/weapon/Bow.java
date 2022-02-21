package data.treasure.weapon;

public class Bow extends Weapon {
    public Bow() {
        this( 9, 3);
    }

    public Bow(int weight, int attack) {
        super(weight, attack);
    }

    @Override
    public String toString() {
        return "弓箭";
    }
}

