package data.treasure.armor;

/**
 * 防具-皮甲
 */
public class LeatherArmor extends Armor {
    public LeatherArmor() {
        this.setDefense(2);
        this.setWeight(9);
        this.setName("皮甲");
        this.setIsInitialWeapon(false);
    }
}