package data.treasure.weapon;

import data.map.Abyss;
import data.treasure.Treasure;

import java.util.List;
import java.util.Random;

public abstract class Weapon implements Treasure {

    private int weight;
    private int attack;

    public static String getInitialWeaponInfo(){
        Weapon[] initialWeapons = {new Sword(), new Ax(), new Staff()};
        StringBuilder sb = new StringBuilder();
        int i=1;
        sb.append("-----------------------\n");
        for(Weapon weapon:initialWeapons){
            sb.append(i++ + ". ");
            sb.append(weapon);
            sb.append("\n");
        }
        sb.append("-----------------------");
        return sb.toString();
    }

    public Weapon(int weight, int attack) {
        this.weight = weight;
        this.attack = attack;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + " Weight: "+ weight + ", Attack:" + attack;
    }
}

