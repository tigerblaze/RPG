package data.treasure.prop;
import data.treasure.Treasure;

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

       String name,usage;
       public String getName(){return this.name;}
       public String getUsage(){return this.usage;}

       public int getPrice(){return this.price;}
       public int getHp(){return this.hp;}
       public int getStrength(){return this.strength;}
       public int getDefense(){return this.defense;}
       public int getAttack(){return this.attack;}
       public int getHit(){return this.hit;}
       public int getAgile() {
              return agile;
       }
       public int getTimes() {
              return times;
       }
       public boolean isBuff(){
              return this.isBuff;
       }

       public void setPrice(int price){this.price = price;}
       public void setHp(int hp){this.hp = hp;}
       public void setStrength(int strength){this.strength = strength;}
       public void setDefense(int defense){this.defense = defense;}
       public void setAttack(int attack){this.attack = attack;}
       public void setHit(int defense){this.hit = defense;}
       public void setUsage(String usage){this.usage = usage;}
       public void setAgile(int agile) {
              this.agile = agile;
       }
       public void setTimes(int times){this.times = times;}/*BUFF存在回"次數" 0:一次性 -1:其他判定 */
       public void setIsBuff(boolean isBuff){
              this.isBuff = true;
       }
       public void setBuffRounds(int buffRounds){
              this.buffRounds = buffRounds;
       }


       public Prop(){
       }


       /**
        * 建立反向屬性道具
        * @param buffCancel
        */
       public Prop(Prop buffCancel){
              buffCancel.setHp( -buffCancel.getHp());
              buffCancel.setStrength( -buffCancel.getStrength());
              buffCancel.setDefense( -buffCancel.getDefense());
              buffCancel.setAttack( -buffCancel.getAttack());
              buffCancel.setHit( -buffCancel.getHit());
              buffCancel.setAgile( -buffCancel.getAgile());
              buffCancel.setTimes(buffCancel.getTimes()-1);
       }

       /**
        * 建立反向屬性道具
        * @param buffCancel
        * @param times
        */
       public Prop(Prop buffCancel, int times){
              buffCancel.setHp( -buffCancel.getHp());
              buffCancel.setStrength( -buffCancel.getStrength());
              buffCancel.setDefense( -buffCancel.getDefense());
              buffCancel.setAttack( -buffCancel.getAttack());
              buffCancel.setHit( -buffCancel.getHit());
              buffCancel.setAgile( -buffCancel.getAgile());
              buffCancel.setTimes(times);
       }
}
