package data.treasure.armor;

/**
 * 防具-板甲
 */
public class PlateArmor extends Armor {
    public PlateArmor() {
        this.setDefense(4);
        this.setWeight(20);
        this.setName("板甲");
        this.setIsInitialWeapon(true);
    }
}