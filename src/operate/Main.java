package operate;

import data.creature.*;
import data.map.Forest;
import data.treasure.armor.*;
import data.treasure.prop.Prop;
import data.treasure.weapon.*;

/**
 * 主介面控制+輸出文字
 */
public class Main {
    /**
     * 操作輸入
     */
    private static final String GO = "go";
    private static final String STATUS = "status";
    private static final String SUPPLY = "supply";

    /**
     * 道具操作輸入
     */
    public static final int ITEM_STATUS = 1;
    public static final int ITEM_USE = 2;
    public static final int ITEM_EXIT = 3;

    /**
     * 武器選擇
     */
    public static final int SWORD = 1;
    public static final int AX = 2;
    public static final int STAFF = 3;


    /**
     * 防具選擇
     */
    public static final int WOODEN_ARMOR = 1;
    public static final int CHAIN_ARMOR = 2;
    public static final int PLATE_ARMOR = 3;

    /**
     * 取得使用者輸入道具索引
     */
    private static final int[] ITEM_INDEX_ARRAY = new int[1];
    private static final int ITEM_INDEX = 0;


    public static void main(String[] args) {
        System.out.println("你莫名其妙就被選為勇者了\n村莊附近的森林中，有一隻大象在迫害村民。\n而且離村子不遠的深淵中，" +
                "魔王巴哈姆特隨時會毀滅這個村莊。\n請勇者拯救我們吧！請問你的名字是？");
        String name = Input.filterBlankString();
        Player player = new Player(name);
        System.out.printf("%s你好\n", player.getName());
        /**
         * 死亡復活後要回到死亡前的hp
         */
        int preHp;//血量紀錄
        while (player.getWeapon() == null || player.getArmor() == null) {
            System.out.println("你的負重上限：" +  player.getMaxWeight());
            System.out.println("請選擇武器");
            System.out.println(Weapon.getInitialWeaponInfo());
            switch (Input.filterSelection(SWORD, STAFF)) {
                case SWORD: {
                    player.setWeapon(new Sword());
                    break;
                }
                case AX: {
                    player.setWeapon(new Ax());
                    break;
                }
                case STAFF: {
                    player.setWeapon(new Staff());
                    break;
                }
                default:{
                    break;
                }
            }
            System.out.println("請選擇防具");
            System.out.println(Armor.getInitialArmorInfo());
            switch (Input.filterSelection(WOODEN_ARMOR, PLATE_ARMOR)) {
                case WOODEN_ARMOR: {
                    player.setArmor(new WoodenArmor());
                    break;
                }
                case CHAIN_ARMOR: {
                    player.setArmor(new ChainArmor());
                    break;
                }
                case PLATE_ARMOR: {
                    player.setArmor(new PlateArmor());
                    break;
                }
                default: {
                    break;
                }
            }
        }


        System.out.printf("我會將%s傳送到森林，加油啊勇者！別死囉！\n", player.getName());
        Input.pauseAndContinue();

        //新建對象(操作,環境)，控制觸發事件
        EventService eventService = new EventService(player);
        System.out.println("你來到了" + eventService.getRpgMap());
        if (eventService.getRpgMap() instanceof Forest) {
            System.out.println("打敗大象後，記得去打巴哈姆特喔，加油");
        } else {
            System.out.println("你到深淵去了嗎！？那就先打巴哈姆特，再打大象吧");
        }
        Input.pauseAndContinue();

        boolean isGameOver = false;
        //基礎操作
        while (!isGameOver) {
            preHp = player.getHp(); //死亡要回到這個血量
            System.out.println("""
                    要做什麼呢？
                    go -> 往前走一步
                    status -> 查看人物狀態
                    supply -> 顯示物品欄
                    --------------------------------""");
            String operateChoice = Input.getOperateInput();
            switch (operateChoice) {
                case GO: {
                    //隨機事件1~5
                    eventService.exeRandomEvent();
                    break;
                }
                case STATUS: {
                    //顯示狀態
                    player.showDetail();
                    break;
                }
                case SUPPLY: {
                    boolean isItemOpen = true;
                    while (isItemOpen) {
                        player.showItems(); //印出道具
                        System.out.println("""
                                請輸入指令: 顯示相關的物品說明
                                "1~5 status":查看該物品
                                "1~5 use":使用該物品
                                "exit": 退出物品欄""");
                        int itemAction = Input.getItemActionInput(ITEM_INDEX_ARRAY);//選擇顯示說明 使用 離開
                        Prop prop = player.chooseItem(ITEM_INDEX_ARRAY[ITEM_INDEX]);
                        int itemIndex = ITEM_INDEX_ARRAY[ITEM_INDEX];
                        switch (itemAction) {
                            case ITEM_STATUS: {  //調用物品方法(陣列序號)
                                if (prop == null) {
                                    System.out.println("無道具");
                                    break;
                                }
                                System.out.println(prop.getUsage());
                                break;
                            }
                            case ITEM_USE: { //調用使用物品方法
                                if (prop == null) {
                                    System.out.println("無道具");
                                    break;
                                }
                                player.useProp(prop);
                                //getTime為0，代表是一次性道具，使用就移除
                                player.getItems()[itemIndex] = null;
                                break;
                            }
                            case ITEM_EXIT: {
                                isItemOpen = false;
                                break;
                            }
                            default: {
                                System.out.println("輸入錯誤");
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            if (player.getHp() <= 0) {
                //死了整個世界和世界都重整
                eventService = new EventService(player);
                //回到原來的血量
                player.setHp(preHp);
                System.out.println("你重生了回到" + eventService.getRpgMap());
                continue;
            }
            if (eventService.isGamePass()) {
                System.out.printf("*****\\\\%s打敗了大象和巴哈姆特，村民很感謝你，謝謝勇者，再見了勇者//*****\n", player.getName());
                isGameOver = true;
            }
            System.out.println("===============================================================================");
        }

    }
}
