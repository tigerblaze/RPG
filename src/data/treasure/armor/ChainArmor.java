package data.treasure.armor;

/**
 * 防具-鎖子甲
 */
public class ChainArmor extends Armor {
    public ChainArmor() {
        this.setDefense(1);
        this.setWeight(15);
        this.setName("鎖子甲");
        this.setIsInitialWeapon(true);
    }
}