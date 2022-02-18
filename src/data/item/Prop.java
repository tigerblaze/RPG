package data.item;
public class Prop {
       private int price;
       private int hp;
       private int strength;
       private int defense;
       private int attack;
       private int hit;
       private int agile;
       private int times;

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

       public void setPrice(int price){this.price = price;}
       public void setHp(int hp){this.hp = hp;}
       public void setStrength(int strength){this.strength = strength;}
       public void setDefense(int defense){this.defense = defense;}
       public void setAttack(int attack){this.attack = attack;}
       public void setHit(int defense){this.hit = hit;}
       public void setUsage(String usage){this.usage = usage;}
       public void setAgile(int agile) {
              this.agile = agile;
       }

       public void setTimes(int times){this.usage = usage;}/*BUFF存在回"次數" 0:永久存在 -1:其他判定 */
}
