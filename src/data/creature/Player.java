package data.creature;
import data.treasure.Treasure;
import data.treasure.weapon.*;
import data.treasure.armor.*;
import data.treasure.prop.*;

public class Player extends Creature{
    private String name;
    private Weapon weapon = null;
    private Armor armor = null;
    private Prop[] items;
    private int numOfItems;
    private int weight;
    private int upgradeExp;
    private boolean isBattling;

    public Player(String name){
        super(5,5,5,5,5,1,0);
        this.name = name;
        this.upgradeExp = 10;
        items = new Prop[5];
        this.weight = strength*6;
    }

    public boolean isReadyToLevelUp(){
        return this.exp>this.upgradeExp;
    }

    public void addExp(int exp){
        this.exp += exp;
        if(isReadyToLevelUp()){
            levelUp();
        }
    }

    public void levelUp(){
        upgradeExp*=2;
        hp+=2;
        agile+=2;
        strength+=2;
        hit+=2;
        defense+=2;
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

    public int getUpgradeExp(){
        return upgradeExp;
    }

    public boolean isBattling() {
        return isBattling;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeapon(Weapon weapon) {
        if(this.weapon!=null){
            if(this.weight+this.weapon.getWeight()<weapon.getWeight()){
                System.out.println("無法更換武器! 若換至新武器，您的負重將會超出上限!");
                return;
            }
            else{
                this.weight += this.weapon.getWeight();
            }
        }
        this.weight -= weapon.getWeight();
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        if(this.armor!=null){
            if(this.weight+this.armor.getWeight()<armor.getWeight()){
                System.out.println("無法更換防具! 若換至新防具，您的負重將會超出上限!");
                return;
            }
            else{
                this.weight += this.armor.getWeight();
            }
        }
        this.weight -= armor.getWeight();
        this.armor = armor;
    }

    public void setItems(Prop[] items) {
        this.items = items;
    }

    public boolean isItemsFull(){
        return (this.items.length==this.numOfItems);
    }

    public void addItem(Prop prop){
        if(isItemsFull()){
            System.out.println("無法撿取道具!已達道具上限!");
        }
        else{
            this.items[this.numOfItems++] = prop;
        }
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setUpgradeExp(int upgradeExp){
        this.upgradeExp = upgradeExp;
    }

    public void setBattling(boolean battling) {
        isBattling = battling;
    }

    public void getTreasure(Treasure treasure){
        String treasureType = treasure.getClass().getSimpleName();
        Weapon weapon = null;
        Armor armor = null;
        Prop prop = null;
        if(treasure instanceof Weapon){
            weapon = (Weapon) treasure;
            setWeapon(weapon);
        }
        else if(treasure instanceof Armor){
            armor = (Armor) treasure;
            setArmor(armor);
        }
        else if(treasure instanceof Prop){
            prop = (Prop) treasure;
        }
        else{
            System.out.println("Error in Player.getTreasure()! treasure is neither weapon, armor nor prop!");
        }
    }
}
