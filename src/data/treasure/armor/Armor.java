package data.treasure.armor;

import data.treasure.Treasure;
import java.util.List;
import java.util.Random;

/**
 * 防具
 */
public abstract class Armor implements Treasure {
    private int weight;
    private int defense;
    private String name;
    /**
     * 是否為初始防具
     */
    private boolean isInitialWeapon;

    /**
     * 回傳初始防具的所有資訊
     * @return 
     */
    public static String getInitialArmorInfo() {
        Armor[] initialArmors = new Armor[]{new WoodenArmor(), new ChainArmor(), new PlateArmor()};
        StringBuilder armorDescription = new StringBuilder();
        int count = 1;
        armorDescription.append("-----------------------\n");
        for(Armor armor:initialArmors) {
            armorDescription.append(count++ + ". ");
            armorDescription.append(armor);
            armorDescription.append(" " + armor.getDetail());
            armorDescription.append("\n");
        }
        armorDescription.append("-----------------------");
        return armorDescription.toString();
    }

    /**
     * 回傳防具重量
     * @return 防具重量
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * 設定防具重量
     * @param weight 防具重量
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * 設置防具名稱
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 將屬性設置成初始防具
     * @param isInitialWeapon 
     */
    public void setIsInitialWeapon(boolean isInitialWeapon) {
        this.isInitialWeapon = isInitialWeapon;
    }

    public String toString() {
        return this.name;
    }

    /**
     * 回傳防具細部資料
     * * @return
     */
    public String getDetail() {
        return "重量: " + this.weight + ", 防禦:" + this.defense;
    }

}
