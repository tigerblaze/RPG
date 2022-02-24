package operate;

import java.util.Random;

import data.map.*;
import data.creature.*;

import data.treasure.Treasure;
import data.treasure.weapon.Bow;
import data.treasure.armor.LeatherArmor;
import data.treasure.prop.HealingLotion;
import data.treasure.prop.StrengthEnhanceLotion;


public class EventService {
    private static final Random RANDOM = new Random();
    private static final int FOREST_MAP = 0;
    private static final int ABYSS_MAP = 1;

    private Player player;
    private RpgMap rpgMap;
    /**
     * 兩個地圖都clear才通關
     */
    private RpgMap[] gamePassMaps;

    public enum Event {
        NOTHING,
        MEET_PASSIVE_ENEMY,
        MEET_ACTIVE_ENEMY,
        BRANCH,
        GET_TREASURE
    }

    public EventService(Player player) {
        this.player = player;
        gamePassMaps = new RpgMap[2];
        gamePassMaps[FOREST_MAP] = new Forest();
        gamePassMaps[ABYSS_MAP] = new Abyss();
        this.rpgMap = (RANDOM.nextInt(2) == 0) ? gamePassMaps[FOREST_MAP] : gamePassMaps[ABYSS_MAP];
    }

    public Event exeRandomEvent() {
        Event event = Event.NOTHING;
        int eventNum ;
        if(this.rpgMap.getSteps()>=5){
            eventNum = 3;
        }else {
            eventNum = RANDOM.nextInt(5) + 1;
        }
        while (eventNum == 2) { //目前不會遇到被動怪
            eventNum = RANDOM.nextInt(5) + 1;
        }
        switch (eventNum) {
            case 1:
                System.out.println("沒事發生");
                event = Event.NOTHING;
                break;
            case 2:
                event = Event.MEET_PASSIVE_ENEMY;
                meetPassiveEnemy();
                break;
            case 3:
                event = Event.MEET_ACTIVE_ENEMY;
                chooseActiveEnemy();
                break;
            case 4:
                event = Event.BRANCH;
                branch();
                break;
            case 5:
                event = Event.GET_TREASURE;
                getTreasure();
                break;
        }
        this.rpgMap.go();
        return event;
    }

    public void battle(Enemy enemy) {
        if (enemy == null) {
            return;
        }
        System.out.println("遇到" + enemy + "了");
        player.setBattling(true);

        while (player.getHp() > 0 && enemy.getHp() > 0) {
            System.out.println("1.戰鬥 2.使用道具 3.逃走");
            int select = Input.filterSelection(1, 3);
            if (select == 1) {
                if (player.getAgile() >= enemy.getAgile()) {
                    attack(player, enemy);
                } else {
                    attack(enemy, player);
                }
            } else if (select == 2) {
                System.out.println("選擇要使用的道具(1~5道具 6.返回)：");
                player.showItems();
                int choose = Input.filterSelection(1, 6);
                if(choose==6){
                    continue;
                }
                player.useProp(player.chooseItem(choose-1));

            } else if (select == 3) {
                if (isEscape(player, enemy)) {
                    System.out.println("跑了！跑了！");
                    break;
                } else {
                    System.out.println("逃不掉....");
                }
            }
        }
        player.setBattling(false);
    }

    /**
     * @param first  先攻
     * @param second 後攻
     */
    private void attack(Creature first, Creature second) {
        //戰鬥開始
        while (first.getHp() > 0 && second.getHp() > 0) {
            attackAction(first,second);
            //先攻方攻擊
            Input.pauseAndContinue();
            System.out.println();

            if(first.getHp() <= 0 || second.getHp() <= 0){
                break;
            }
            //後攻方攻擊
            attackAction(second,first);
            player.buffArrayRun();

            Input.pauseAndContinue();
        }
    }

    /**
     * 使出攻擊，傷害判定，打贏怪物的獎勵，及死亡顯示
     * @param attack 攻擊方
     * @param defence 防守方
     */
    public void attackAction(Creature attack, Creature defence){
        int attackAtk = 0;
        int defenceAtk = 0;

        if (attack instanceof Player) {
            attackAtk = attack.getStrength() + ((Player) attack).getWeapon().getAttack();
            defenceAtk = defence.getStrength();
        } else if (defence instanceof Player) {
            defenceAtk = defence.getStrength() + ((Player) defence).getWeapon().getAttack();
            attackAtk = attack.getStrength();
        }

        if(isHit(attack,defence)){
            System.out.println(attack + " 使出攻擊");
            int hurt = attackAtk-defence.getDefense();
            System.out.println(attack + "造成" + hurt + "傷害");
            //傷害計算
            hurt = Math.max(0,hurt); //傷害為負的不扣血
            defence.setHp(defence.getHp()-hurt);//更新守方血量
            System.out.println(defence + "的hp剩下" + defence.getHp());
            System.out.println(attack + "的hp剩下" + attack.getHp());

            //若打敗守方
            if(defence.getHp()<=0){
                System.out.println(defence + "死了");
                //若被打死的是怪，獲得獎勵
                if(defence instanceof Enemy){
                    Enemy enemy = (Enemy) defence;
                    //玩家獲得經驗值
                    System.out.println("獲得經驗值" + enemy.getExp());
                    if(this.player.isReadyToLevelUp(enemy.getExp())){
                        System.out.println("升級！");
                    }
                    this.player.addExp(enemy.getExp());
                    //獲得寶藏
                    Treasure prop = enemy.getDrops()[RANDOM.nextInt(2)];
                    player.getTreasure(prop);
                    System.out.println("獲得" + prop);
                    //打贏boss去下一張圖
                    if(enemy.getType()==EnemyType.ANIMAL_BOSS || enemy.getType()==EnemyType.MONSTER_BOSS){
                        clearChangeMap();
                        System.out.println("你打贏了boss來到了" + this.rpgMap);
                    }
                }
            }
        }else{
            System.out.println(attack + "使出攻擊但沒打到");
        }
    }

    /**
     * @param attack  攻擊方
     * @param defence 防禦方
     * @return 是否命中
     */
    public boolean isHit(Creature attack, Creature defence) {
        double rate = (defence.getAgile() - attack.getHit()) * 0.2;
        boolean hit = false;
        if (rate < 0) {
            hit = true;
        } else if (rate > 1) {
            hit = false;
        } else {
            double num = Math.random();
            if (num < rate) {
                hit = true;
            } else if (num >= rate) {
                hit = false;
            }
        }
        return hit;
    }

    /**
     * @param player
     * @param enemy
     * @return 是否逃跑成功
     */
    public boolean isEscape(Player player, Enemy enemy) {
        if (enemy.isActive()) {
            return false;
        }
        double rate = (player.getAgile() - enemy.getAgile() * 0.4);
        boolean escape = false;
        if (rate < 0) {
            escape = true;
        } else if (rate > 1) {
            escape = false;
        } else {
            double num = Math.random();
            if (num < rate) {
                escape = true;
            } else if (num >= rate) {
                escape = false;
            }
        }
        return escape;
    }

    public void meetPassiveEnemy() {
        //just for now cause Stan said that we will not meet any passive enemy
        return;
    }

    public void chooseActiveEnemy() {
        String currentMap = this.rpgMap.getClass().getSimpleName();
        Enemy nextEnemy = null;
        switch (currentMap) {
            case "Forest" : {
                if (this.rpgMap.getSteps() >= 5) {
                    nextEnemy = new Elephant();
                } else {
                    Forest.Animal nextAnimal = Forest.Animal.randomAnimal();
                    switch (nextAnimal) {
                        case WOLF : {
                            nextEnemy = new Wolf();
                            break;
                        }
                        case LION : {
                            nextEnemy = new Lion();
                            break;
                        }
                        case BOAR : {
                            nextEnemy = new Boar();
                            break;
                        }
                        case ELEPHANT:{
                            nextEnemy = new Elephant();
                            break;
                        }
                    }
                }
                break;
            }
            case "Abyss" : {
                if (this.rpgMap.getSteps() >= 5) {
                    nextEnemy = new Bahamut();
                } else {
                    Abyss.Monster nextMonster = Abyss.Monster.randomMonster();
                    switch (nextMonster) {
                        case MAGIC_WOLF: {
                            nextEnemy = new MagicWolf();
                            break;
                        }
                        case WEASEL: {
                            nextEnemy = new Weasel();
                            break;
                        }
                        case GHOST : {
                            nextEnemy = new Ghost();
                            break;
                        }
                        case BAHAMUT:{
                            nextEnemy = new Bahamut();
                            break;
                        }
                    }
                }
                break;
            }
        }
        if (nextEnemy == null) {
            System.out.println("Error in meetActiveEnemy! nextEnemy is mull !");
        } else {
            battle(nextEnemy);
        }
    }

    public void branch() {
        System.out.println("遇到岔路，你要往哪邊走呢？\n1.左邊 2.右邊 3.不走了回家");
        int input = Input.filterSelection(1, 3);
        this.rpgMap.go();
        this.exeRandomEvent(); //再執行一次
        if (input==3) {
            System.out.println("回家吧！掰掰！");
            System.exit(0);//好玩用的
        }
    }

    public void getTreasure() {
        String currentMap = this.rpgMap.getClass().getSimpleName();
        Treasure treasure = null;
        switch (currentMap) {
            case "Forest": {
                Forest.Treasure forestTreasure = Forest.Treasure.randomTreasure();
                switch (forestTreasure) {
                    case HEALING_LOTION: {
                        treasure = new HealingLotion();
                        break;
                    }
                    case STRENGTH_ENHANCE_LOTION: {
                        treasure = new StrengthEnhanceLotion();
                        break;
                    }
                    case ARROW: {
                        treasure = new Bow();
                        break;
                    }
                }
                break;
            }
            case "Abyss": {
                Abyss.Treasure abyssTreasure = Abyss.Treasure.randomTreasure();
                switch (abyssTreasure) {
                    case LEATHER_ARMOR: {
                        treasure = new LeatherArmor();
                        break;
                    }
                    case STRENGTH_ENHANCE_LOTION: {
                        treasure = new StrengthEnhanceLotion();
                        break;
                    }
                    case HEALING_LOTION: {
                        treasure = new HealingLotion();
                        break;
                    }
                }
                break;
            }
        }
        if (treasure == null) {
            System.out.println("Error in getTreasure()! treasure is null!");
        } else {
            this.player.getTreasure(treasure);
            System.out.println("有寶箱獲得" + treasure);
        }
    }

    /**
     * 遊戲是否通關
     * @return 遊戲是否通關
     */
    public boolean isGamePass(){
        return (!gamePassMaps[0].isBossAlive() && !gamePassMaps[1].isBossAlive());
    }

    /**
     * 打贏BOSS換地圖
     */
    private void clearChangeMap(){
        if(rpgMap instanceof Forest){
            rpgMap = gamePassMaps[ABYSS_MAP];
        }else if(rpgMap instanceof Abyss){
            rpgMap = gamePassMaps[FOREST_MAP];
        }
    }

    public RpgMap getRpgMap() {
        return rpgMap;
    }
}
