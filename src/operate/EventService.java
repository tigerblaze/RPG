package operate;

import java.util.Random;

import data.map.*;
import data.creature.*;

import data.treasure.Treasure;
import data.treasure.prop.Prop;
import data.treasure.weapon.Bow;
import data.treasure.armor.LeatherArmor;
import data.treasure.prop.HealingLotion;
import data.treasure.prop.StrengthEnhanceLotion;


public class EventService {
    private Player player;
    private Map map;
    private OperateService operateService;
    private static final Random RANDOM = new Random();

    public enum Event {
        NOTHING("沒事發生"),
        MEET_PASSIVE_ENEMY("遇到被動怪物"),
        MEET_ACTIVE_ENEMY("遇到主動怪物"),
        BRANCH("遇到叉路"),
        GET_TREASURE("有寶箱！");

        private final String description;

        Event(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public EventService(Player player, OperateService operateService) {
        this.player = player;
        this.map = (RANDOM.nextInt(2) == 0) ? new Forest() : new Abyss();
        this.operateService = operateService;
    }


    public Event exeRandomEvent() {
        Event event = Event.NOTHING;
        int eventNum = RANDOM.nextInt(5) + 1;
        while(eventNum==2){ //目前不會遇到被動怪
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
        return event;
    }


    public void battle(Enemy enemy) {
        if (enemy == null) return;
        System.out.println("遇到" + enemy + "了");

        while (player.getHp() > 0 && enemy.getHp() > 0) {
            System.out.println("1.戰鬥 2.使用道具 3.逃走");
            int select = Input.filterSelection(1, 2);
            if (select == 1) {
                if (player.getAgile() >= enemy.getAgile()) {
                    attack(player, enemy);
                } else {
                    attack(enemy, player);
                }
            } else if (select == 2) {
                //調用operate
                System.out.println("選擇要使用的道具：");
                Prop[] items = player.getItems();
                for (int i = 0; i < items.length; i++) {
                    System.out.println((i + 1) + "." + items[i]);
                }
                int index = Input.filterSelection(1, items.length) - 1;
                //道具使用
            } else if (select == 3) {
                if (isEscape(player, enemy)) {
                    System.out.println("跑了！跑了！");
                    break;
                } else {
                    System.out.println("逃不掉....");
                }
            }
        }
    }

    /**
     * @param first  先攻
     * @param second 後攻
     */
    private void attack(Creature first, Creature second) {
        if (first.getAgile() >= second.getAgile()) {
            attack(first, second);
        }
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
        int newHp = 0;
        while (keepBattle && firstHp != 0 && secondHp != 0) {
            if (isHit(first, second)) {
                System.out.println(first + "使出攻擊");
                hurt = firstAtt - second.getDefense();
                System.out.println(first + "造成" + hurt + "傷害");
                hurt = Math.max(hurt, 0);
                newHp = Math.min(secondHp - hurt, 0);
                second.setHp(newHp);
                secondHp = newHp;
                System.out.println(second + "的hp剩下" + secondHp);
                System.out.println(first + "的hp剩下" + firstHp);
                if (secondHp == 0) {
                    if (second instanceof Enemy) {
                        Enemy enemy = (Enemy) second;
                        this.player.setExp(this.player.getExp() + enemy.getExp());
                        System.out.println("獲得經驗值+" + enemy.getExp());
                        Treasure prop = enemy.getDrops()[RANDOM.nextInt(2)];
                        player.getTreasure(prop);
                    }
                    System.out.println(second + "死了");
                    break;
                }
            } else {
                System.out.println(first + "使出攻擊但沒打到");
            }

            if (isHit(second, first)) {
                System.out.println(second + "使出攻擊");
                hurt = secondAtt - first.getDefense();
                System.out.println(second + "造成" + hurt + "傷害");
                hurt = Math.max(hurt, 0);
                newHp = Math.min(secondHp - hurt, 0);
                first.setHp(newHp);
                firstHp = newHp;
                System.out.println(first + "的hp剩下" + firstHp);
                System.out.println(second + "的hp剩下" + secondHp);
                if (firstHp == 0) {
                    if (first instanceof Enemy) {
                        Enemy enemy = (Enemy) first;
                        this.player.setExp(this.player.getExp() + enemy.getExp());
                        System.out.println("獲得經驗值+" + enemy.getExp());
                        Treasure prop = enemy.getDrops()[RANDOM.nextInt(2)];
                        player.getTreasure(prop);
                    } else {
                        System.out.println(first + "死了");
                    }
                    break;
                }
            } else {
                System.out.println(second + "使出攻擊但沒打到");
            }
        }
    }

    /**
     * 將死亡怪物的經驗值給玩家
     * @param enemy 被擊殺的怪物
     */
    public void killEnemyGetExp(Enemy enemy) {
        int exp = player.getExp();
        player.setExp(exp + enemy.getExp());
        System.out.println("獲得經驗值+" + enemy.getExp());
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
        String currentMap = this.map.getClass().getSimpleName();
        Enemy nextEnemy = null;
        switch (currentMap) {
            case "Forest" -> {
                if (this.map.getSteps() == 5) {
                    nextEnemy = new Elephant();
                } else {
                    Forest.Animal nextAnimal = Forest.Animal.randomAnimal();
                    switch (nextAnimal) {
                        case WOLF -> {
                            nextEnemy = new Wolf();
                        }
                        case LION -> {
                            nextEnemy = new Lion();
                        }
                        case BOAR -> {
                            nextEnemy = new Boar();
                        }
                    }
                }
            }
            case "Abyss" -> {
                if (this.map.getSteps() == 5) {
                    nextEnemy = new Bahamut();
                } else {
                    Abyss.Monster nextMonster = Abyss.Monster.randomMonster();
                    switch (nextMonster) {
                        case MAGICWOLF -> {
                            nextEnemy = new Wolf();
                        }
                        case WEASEL -> {
                            nextEnemy = new Lion();
                        }
                        case GHOST -> {
                            nextEnemy = new Boar();
                        }
                    }
                }
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
        int input = Input.filterSelection(1,3);
        if(input!=3) {
            operateService.go();
        }else{
            System.out.println("回家吧！掰掰！");
            System.exit(0);//好玩用的
        }
    }

    public void getTreasure() {
        String currentMap = this.map.getClass().getSimpleName();
        Treasure treasure = null;
        switch (currentMap) {
            case "Forest" -> {
                Forest.Treasure forestTreasure = Forest.Treasure.randomTreasure();
                switch (forestTreasure) {
                    case HEALINGLOTION -> {
                        treasure = new HealingLotion();
                    }
                    case STRENGTHENHANCELOTION -> {
                        treasure = new StrengthEnhanceLotion();
                    }
                    case ARROW -> {
                        treasure = new Bow();
                    }
                }
            }
            case "Abyss" -> {
                Abyss.Treasure abyssTreasure = Abyss.Treasure.randomTreasure();
                switch (abyssTreasure) {
                    case lEATHERARMOR -> {
                        treasure = new LeatherArmor();
                    }
                    case STRENGTHENHANCELOTION -> {
                        treasure = new StrengthEnhanceLotion();
                    }
                    case HEALINGLOTION -> {
                        treasure = new HealingLotion();
                    }
                }
            }
        }
        if (treasure == null) {
            System.out.println("Error in getTreasure()! treasure is null!");
        } else {
            this.player.getTreasure(treasure);
        }
    }


}
