package data.armor;

public class Armor {
    private int weight;
    private int defense;

    public Armor() {
    }

    public Armor(int weight, int defense) {
        this.weight = weight;
        this.defense = defense;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}

