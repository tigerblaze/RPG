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
        boolean keepBattle = true;
        int firstHp = first.getHp();
        int secondHp = second.getHp();
        int firstAtt = 0;
        int secondAtt = 0;

        if (first instanceof Player) {
            keepBattle = ((Player) first).isBattling();
            firstAtt = first.getStrength() + ((Player) first).getWeapon().getAttack();
            secondAtt = second.getStrength();
        } else if (second instanceof Player) {
            keepBattle = ((Player) second).isBattling();
            secondAtt = second.getStrength() + ((Player) second).getWeapon().getAttack();
            firstAtt = first.getStrength();
        }

        //戰鬥開始
        int hurt = 0;
        while (keepBattle && firstHp > 0 && secondHp > 0) {
            if (isHit(first, second)) {
                System.out.println(first + "使出攻擊");
                hurt = firstAtt - second.getDefense();
                System.out.println(first + "造成" + hurt + "傷害");
                //傷害
                hurt = Math.max(hurt, 0);
                second.setHp(secondHp - hurt);
                secondHp -= hurt;
                System.out.println(second + "的hp剩下" + second.getHp());
                System.out.println(first + "的hp剩下" + first.getHp());

                if (secondHp <= 0) {
                    if (second instanceof Enemy) {
                        Enemy enemy = (Enemy) second;
                        //得到經驗值
                        if(this.player.isReadyToLevelUp(enemy.getExp())){
                            System.out.println("升級！");
                        }
                        this.player.addExp(enemy.getExp());
                        System.out.println("獲得經驗值+" + enemy.getExp());
                        //獲得寶藏
                        Treasure prop = enemy.getDrops()[RANDOM.nextInt(2)];
                        player.getTreasure(prop);
                        System.out.println("獲得" + prop);
                        if(enemy.getType()==EnemyType.ANIMAL_BOSS || enemy.getType()==EnemyType.MONSTER_BOSS){
                            clearChangeMap();
                            System.out.println("你打贏了boss來到了" + this.rpgMap);
                        }
                    }
                    System.out.println(second + "死了");
                    player.buffArrayRun();
                    break;
                }
            } else {
                System.out.println(first + "使出攻擊但沒打到");
            }

            Input.pauseAndContinue();
            System.out.println();

            if (isHit(second, first)) {
                System.out.println(second + "使出攻擊");
                hurt = secondAtt - first.getDefense();
                System.out.println(second + "造成" + hurt + "傷害");
                //傷害
                hurt = Math.max(hurt, 0);
                first.setHp(firstHp - hurt);
                firstHp -=hurt ;
                System.out.println(first + "的hp剩下" + first.getHp());
                System.out.println(second + "的hp剩下" + second.getHp());

                if (firstHp <= 0) {
                    if (first instanceof Enemy) {
                        Enemy enemy = (Enemy) first;
                        //得到經驗值
                        if(this.player.isReadyToLevelUp(enemy.getExp())){
                            System.out.println("升級！");
                        }
                        this.player.addExp(enemy.getExp());
                        System.out.println("獲得經驗值+" + enemy.getExp());
                        //獲得寶藏
                        Treasure prop = enemy.getDrops()[RANDOM.nextInt(2)];
                        player.getTreasure(prop);
                        System.out.println("獲得" + prop);
                        if(enemy.getType()==EnemyType.ANIMAL_BOSS || enemy.getType()==EnemyType.MONSTER_BOSS){
                            clearChangeMap();
                            System.out.println("你打贏了boss來到了" + this.rpgMap);
                        }
                    } else {
                        System.out.println(first + "死了");
                    }
                    player.buffArrayRun();
                    break;
                }
            } else {
                System.out.println(second + "使出攻擊但沒打到");
            }
            player.buffArrayRun();

            Input.pauseAndContinue();
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
