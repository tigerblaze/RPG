package data.weapon;

public class Ax extends Weapon {
    public Ax() {
        this( 15, 4);
    }

    public Ax(int weight, int attack) {
        super(weight, attack);
    }

    @Override
    public String toString() {
        return "斧頭";
    }
}
