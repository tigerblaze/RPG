package data.treasure.prop;

import data.treasure.Treasure;

/**
 * 掉落物
 */
public class Prop implements Treasure {
       private int price;
       private int hp;
       private int strength;
       private int defense;
       private int attack;
       private int hit;
       private int agile;
       private int times;
       private boolean isBuff;
       private int buffRounds;
       private String name;
       private String usage;

       public String getName() {
              return this.name;
       }

       public String getUsage() {
              return this.usage;
       }

       public int getPrice() {
              return this.price;
       }

       public int getHp() {
              return this.hp;
       }

       public int getStrength() {
              return this.strength;
       }

       public int getDefense() {
              return this.defense;
       }

       public int getAttack() {
              return this.attack;
       }

       public int getHit() {
              return this.hit;
       }

       public int getAgile() {
              return this.agile;
       }

       public int getTimes() {
              return this.times;
       }

       public boolean isBuff() {
              return this.isBuff;
       }

       public void setName(String name) {
              this.name = name;
       }

       public void setUsage(String usage) {
              this.usage = usage;
       }

       public void setPrice(int price) {
              this.price = price;
       }

       public void setHp(int hp) {
              this.hp = hp;
       }

       public void setStrength(int strength) {
              this.strength = strength;
       }

       public void setDefense(int defense) {
              this.defense = defense;
       }

       public void setAttack(int attack) {
              this.attack = attack;
       }

       public void setHit(int defense) {
              this.hit = defense;
       }

       public void setAgile(int agile) {
              this.agile = agile;
       }

       public void setTimes(int times) {
              this.times = times;
       }

       public void setIsBuff(boolean isBuff) {
              this.isBuff = true;
       }

       public void setBuffRounds(int buffRounds) {
              this.buffRounds = buffRounds;
       }

       public Prop() {
       }

       /**
        * 因為這個道具是buff類型 有限定的條件下才會有效果
        * 創建一個效果反向的道具 回合到了把他當道具吃掉來互相抵銷效果所以要給他一個消失的方法
        * @param buffCancel
        */
       public Prop(Prop buffCancel) {
              buffCancel.setHp(-buffCancel.getHp());
              buffCancel.setStrength(-buffCancel.getStrength());
              buffCancel.setDefense(-buffCancel.getDefense());
              buffCancel.setAttack(-buffCancel.getAttack());
              buffCancel.setHit(-buffCancel.getHit());
              buffCancel.setAgile(-buffCancel.getAgile());
              buffCancel.setTimes(buffCancel.getTimes() - 1);
       }

       /**
        * 一個物件 可以設定times目前只有設計給取消buff用
        * @param buffCancel
        * @param times 幾回合後消失
        */
       public Prop(Prop buffCancel, int times) {
              buffCancel.setHp(-buffCancel.getHp());
              buffCancel.setStrength(-buffCancel.getStrength());
              buffCancel.setDefense(-buffCancel.getDefense());
              buffCancel.setAttack(-buffCancel.getAttack());
              buffCancel.setHit(-buffCancel.getHit());
              buffCancel.setAgile(-buffCancel.getAgile());
              buffCancel.setTimes(times);
       }

       public String toString() {
              return this.name;
       }
}
