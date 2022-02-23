package operate;

import data.creature.*;
import data.treasure.armor.*;
import data.treasure.weapon.*;

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
    private static int[] ITEM_INDEX_ARRAY = new int[1];
    private static final int ITEM_INDEX = 0;


    public static void main(String[] args) {
        System.out.println("請輸入角色名稱");
        String name = Input.filterBlankString();
        Player player = new Player(name);

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
        }
        //新建對象(操作,環境)，控制觸發事件
        EventService eventService = new EventService(player);

        boolean isGameOver = false;
        //基礎操作
        while (!isGameOver) {
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
                    player.showDetail();
                    break;
                }
                case SUPPLY: {
                    boolean isItemOpen = true;
                    while (isItemOpen) {
                        player.showItems();
                        System.out.println("""
                                請輸入指令: 顯示相關的物品說明
                                "1~5 status":使用該物品
                                "1~5 use":使用該物品
                                "exit": 退出物品欄""");

                        int itemAction = Input.getItemActionInput(ITEM_INDEX_ARRAY);//選擇顯示說明 使用 離開
                        switch (itemAction) {
                            case ITEM_STATUS: {  //調用物品方法(陣列序號)
                                System.out.println(player.chooseItem(ITEM_INDEX_ARRAY[ITEM_INDEX]));
                                break;
                            }
                            case ITEM_USE: { //調用使用物品方法
                                player.useProp(player.chooseItem(ITEM_INDEX_ARRAY[ITEM_INDEX]));
                                break;
                            }
                            case ITEM_EXIT: {
                                isItemOpen = false;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            if (player.getHp() < 0) {
                eventService = new EventService(player);
                continue;
            }
            if (eventService.isGamePass()) {
                isGameOver = true;
            }
            System.out.println("===============================================================================");
        }

    }
}
