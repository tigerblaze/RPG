package data.treasure.weapon;

import data.treasure.Treasure;

public abstract class Weapon implements Treasure {
    private int weight;
    private int attack;
    private String name;
    public boolean isInitialWeapon;

    public Weapon() {
    }

    public static String getInitialWeaponInfo() {
        Weapon[] initialWeapons = new Weapon[]{new Sword(), new Ax(), new Staff()};
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("-----------------------\n");
        Weapon[] var3 = initialWeapons;
        int var4 = initialWeapons.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Weapon weapon = var3[var5];
            int var10001 = i++;
            sb.append(var10001 + ". ");
            sb.append(weapon);
            sb.append("：" + weapon.getDetail());
            sb.append("\n");
        }

        sb.append("-----------------------");
        return sb.toString();
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsInitialWeapon(boolean isInitialWeapon) {
    }

    public int getWeight() {
        return this.weight;
    }

    public int getAttack() {
        return this.attack;
    }

    public String getName() {
        return this.name;
    }

    public boolean isInitialWeapon() {
        return this.isInitialWeapon;
    }

    public String toString() {
        return this.name;
    }

    public String getDetail() {
        return "重量: " + this.weight + ", 攻擊力:" + this.attack;
    }
}