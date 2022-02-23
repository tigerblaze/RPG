package operate;

import data.creature.*;

public class Main {
    /**
     * 操作輸入
     */
    private static final String GO = "go";
    private static final String STATUS = "status";
    private static final String SUPPLY = "supply";

    private static final String ITEM_STATUS = "status";
    private static final String ITEM_USE = "use";
    private static final String ITEM_EXIT = "exit";


    public static void main(String[] args) {
        System.out.println("請輸入角色名稱");
        String name = Input.filterBlankString();
        Player player = new Player(name);

        //新建對象(操作,環境)，控制觸發事件
        EventService eventService = new EventService(player);

        boolean isGameOver = false;
        //基礎操作
        while (!isGameOver) {
            System.out.println("要做什麼呢？\ngo -> 往前走一步\nstatus -> 查看人物狀態\nsupply -> 顯示物品欄");
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
                        System.out.println("請輸入「1~5 status」 顯示相關的物品說明\t\n " +
                                "1~5 use 使用該物品\t\n " +
                                "exit 離開物品欄\n");
                        int itemIndex = Input.filterSelection(1, 5) - 1;//選擇物品序號(0~4)
                        String itemAction = Input.getItemActionInput();//選擇顯示說明 使用 離開
                        switch (itemAction) {
                            case ITEM_STATUS: {  //調用物品方法(陣列序號)
                                System.out.println(player.chooseItem(itemIndex));
                                break;
                            }
                            case ITEM_USE: { //調用使用物品方法
                                player.useProp(player.chooseItem(itemIndex));
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
        }

    }
}
