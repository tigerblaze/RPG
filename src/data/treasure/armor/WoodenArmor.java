package data.treasure.armor;

/**
 * 防具-木甲
 */
public class WoodenArmor extends Armor {
    public WoodenArmor() {
        this.setDefense(1);
        this.setWeight(10);
        this.setName("木甲");
        this.setIsInitialWeapon(true);
    }
}