package data.treasure.armor;

import data.treasure.Treasure;
import data.treasure.weapon.Weapon;

import java.util.List;
import java.util.Random;

public abstract class Armor implements Treasure {
    public enum initialArmors{
        WOODEN_ARMOR, CHAIN_ARMOR, PLATE_ARMOR;
        private static final List<initialArmors> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static initialArmors randomTreasure()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
    private int weight;
    private int defense;

    public static String getInitialArmorInfo(){
        Armor[] initialArmors = {new WoodenArmor(), new ChainArmor(), new PlateArmor()};
        StringBuilder sb = new StringBuilder();
        int i=1;
        sb.append("-----------------------\n");
        for(Armor armor:initialArmors){
            sb.append(i++ + ". ");
            sb.append(armor);
            sb.append("\n");
        }
        sb.append("-----------------------");
        return sb.toString();
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

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + " Weight: "+ weight + ", Defense:" + defense;
    }
}

