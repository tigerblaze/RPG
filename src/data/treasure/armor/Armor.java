package data.treasure.armor;

import data.treasure.Treasure;
import java.util.List;
import java.util.Random;

public class Armor implements Treasure {
    private int weight;
    private int defense;
    private String name;
    private boolean isInitialWeapon;

    public Armor() {
    }

    public static String getInitialArmorInfo() {
        Armor[] initialArmors = new Armor[]{new WoodenArmor(), new ChainArmor(), new PlateArmor()};
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("-----------------------\n");
        Armor[] var3 = initialArmors;
        int var4 = initialArmors.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Armor armor = var3[var5];
            int var10001 = i++;
            sb.append(var10001 + ". ");
            sb.append(armor);
            sb.append("\n");
        }

        sb.append("-----------------------");
        return sb.toString();
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsInitialWeapon(boolean isInitialWeapon) {
        this.isInitialWeapon = isInitialWeapon;
    }

    public String toString() {
        return this.name;
    }

    public String getDetail() {
        String var10000 = this.getClass().getSimpleName();
        return var10000 + " Weight: " + this.weight + ", Defense:" + this.defense;
    }

    public static enum initialArmors {
        WOODEN_ARMOR,
        CHAIN_ARMOR,
        PLATE_ARMOR;

        private static final List<Armor.initialArmors> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        private initialArmors() {
        }

        public static Armor.initialArmors randomTreasure() {
            return (Armor.initialArmors)VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
}
