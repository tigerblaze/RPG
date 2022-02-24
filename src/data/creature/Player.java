package data.creature;

import data.treasure.Treasure;
import data.treasure.weapon.*;
import data.treasure.armor.*;
import data.treasure.prop.*;
import operate.Input;

import java.util.ArrayList;

public class Player extends Creature {
    private String name;
    private Weapon weapon = null;
    private Armor armor = null;
    private Prop[] items;
    private Prop[] accessories;
    private ArrayList<Prop> buffs;
    private int numOfItems;
    private int weight;
    private int upgradeExp;
    private boolean isBattling;

    /**
     * 遊戲中徽章的種類數量
     */
    private static final int DEFAULT_ACCESSORIES_NUM = 2;

    public Player(String name) {
        super(5, 5, 5, 5, 5, 1, 0);
        this.name = name;
        this.upgradeExp = 10;
        this.items = new Prop[5];
        this.weight = strength * 6;
        buffs = new ArrayList<>();
        accessories = new Prop[DEFAULT_ACCESSORIES_NUM];
    }

    /**
     * 增加exp，經驗值到就升級
     *
     * @param exp 獲得的經驗值
     */
    public void addExp(int exp) {
        if (isReadyToLevelUp(exp)) {
            this.exp += exp;
            levelUp();
        } else {
            this.exp += exp;
        }
    }

    /**
     * 是否升級
     *
     * @param exp 得到的經驗值
     * @return 是否升級
     */
    public boolean isReadyToLevelUp(int exp) {
        return this.exp + exp >= this.upgradeExp;
    }

    /**
     * 升級level+1
     */
    public void levelUp() {
        level++;
        upgradeExp *= 2;
        hp += 2;
        agile += 2;
        strength += 2;
        hit += 2;
        defense += 2;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public Prop[] getItems() {
        return items;
    }

    public int getWeight() {
        return weight;
    }

    public int getUpgradeExp() {
        return upgradeExp;
    }

    public boolean isBattling() {
        return isBattling;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeapon(Weapon weapon) {
        int armorWeight = (this.armor == null) ? 0 : this.armor.getWeight();
        if (this.weapon != null) {
            if (this.weight - armorWeight - weapon.getWeight() < 0) {
                System.out.println("無法更換武器! 若換至新武器，您的負重將會超出上限!");
                return;
            } else {
                this.weight += this.weapon.getWeight();
            }
        }
//        this.weight -= weapon.getWeight();
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        int weaponWeight = (this.weapon == null) ? 0 : this.weapon.getWeight();
        if (this.armor != null) {
            if (this.weight - weaponWeight - armor.getWeight() < 0) {
                System.out.println("無法更換防具! 若換至新防具，您的負重將會超出上限!");
                return;
            } else {
                this.weight += this.armor.getWeight();
            }
        }
//        this.weight -= armor.getWeight();
        this.armor = armor;
    }

    public void setItems(Prop[] items) {
        this.items = items;
    }

    public boolean isItemsFull() {
        return (this.items.length == this.numOfItems);
    }

    public void addItem(Prop prop) {
        if (isItemsFull()) {
            System.out.println("無法撿取道具!已達道具上限!");
        } else {
            this.items[this.numOfItems++] = prop;
        }
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setUpgradeExp(int upgradeExp) {
        this.upgradeExp = upgradeExp;
    }

    public void setBattling(boolean battling) {
        isBattling = battling;
    }

    /**
     *
     * @param treasure
     */
    public void getTreasure(Treasure treasure) {
        String treasureType = treasure.getClass().getSimpleName();
        Weapon weapon = null;
        Armor armor = null;
        Prop prop = null;
        if (treasure instanceof Weapon) {
            weapon = (Weapon) treasure;
            setWeapon(weapon);
        } else if (treasure instanceof Armor) {
            armor = (Armor) treasure;
            setArmor(armor);
        } else if (treasure instanceof Prop) {
            prop = (Prop) treasure;
            for(int i = 0; i < 5; i++) {
                if (this.items[i] == null){
                    this.items[i] = prop;
                    break;
                }
            }
        } else {
            System.out.println("Error in Player.getTreasure()! treasure is neither weapon, armor nor prop!");
        }
    }


    /**
     * buff說明：
     * 1.將藥水屬性直接加到角色身上 假如有持續回合 製造一個反向的道具紀錄在buffs裡面
     * 2.每次攻擊後判定 假如反向藥水回合記數歸零 則使用這瓶反向藥水(加到角色身上);
     */

    /**
     * 使用道具
     *
     * @param prop 使用哪個道具
     */
    public void useProp(Prop prop) {
        if (prop == null) {
            return;
        }
        this.hp += prop.getHp();
        this.strength += prop.getStrength();
        this.defense += prop.getDefense();
        this.hit += prop.getHit();
        this.agile += prop.getAgile();
        //有作用回合限制
        if (prop.getTimes() > 0) {
            buffs.add(new Prop(prop));
        }
        //飾品類的反向道具
        if (prop.getTimes() == -1) {
            buffs.add(new Prop(prop, 0));
        }
    }

    /**
     * @param prop 反向道具，將原buff狀態改回來
     * @return 反向道具的剩餘次數
     */
    public int buffRun(Prop prop) {
        int times = prop.getTimes();
        if (times > 0) {
            //buff繼續，效果還沒消失
            prop.setTimes(times - 1);
        } else if (prop.getTimes() == 0) { //buff效果結束
            useProp(prop); //使用反向道具
        }
        return prop.getTimes();
    }

    /**
     * 每回合跑一次buff陣列來減少存在回合或是結束
     */
    public void buffArrayRun() {
        for (int i = 0; i < buffs.size(); i++) {
            if (this.buffRun(buffs.get(i)) == 0) {
                buffs.remove(i); //buff效果結束，從buff欄移除
                i--;
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * 顯示玩家詳情
     */
    public void showDetail() {
        System.out.println(name
                + "\n等級：" + level
                + "\n經驗值：" + exp + " / " + upgradeExp
                + "\n血量：" + hp
                + "\n敏捷：" + agile
                + "\n力量：" + strength
                + "\n命中：" + hit
                + "\n防禦：" + defense
                + "\n負重：" + (weapon.getWeight() + armor.getWeight()) + " / " + this.weight
                + "\n武器：" + (weapon==null? "無":weapon)
                + "\n防具：" + (armor==null? "無":armor)
        );
    }

    /**
     * 顯示道具欄
     */
    public void showItems() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println((i + 1) + "." + items[i]);
            } else {
                System.out.println((i + 1) + ".");
            }
        }
    }


    /**
     * 選擇道具
     *
     * @param index 道具欄索引
     * @return 選到的道具
     */
    public Prop chooseItem(int index) {
        Prop result = items[index];
        this.getItems()[index] = null;
        return result;
    }


}
