package data.treasure.armor;

import data.treasure.Treasure;
import data.treasure.weapon.Weapon;

import java.util.List;
import java.util.Random;

public class Armor implements Treasure {
    public enum initialArmors{
        WOODENARMOR, CHAINARMOR, PLATEARMOR;
        private static final List<initialArmors> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static initialArmors randomTreasure()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
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

    public String getArmorDetail(){
        return "Weight: "+ weight + ", Defense:" + defense;
    }
}

