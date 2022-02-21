package data.treasure.weapon;

import data.map.Abyss;
import data.treasure.Treasure;

import java.util.List;
import java.util.Random;

public class Weapon implements Treasure {
    public enum initialWeapons{
        SWORD, AX, STAFF;
        private static final List<initialWeapons> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static initialWeapons randomTreasure()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    private int weight;
    private int attack;

    public Weapon() {
    }

    public Weapon(int weight, int attack) {
        this.weight = weight;
        this.attack = attack;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getWeaponDetail(){
        return "Weight: "+ weight + ", Attack:" + attack;
    }
}

