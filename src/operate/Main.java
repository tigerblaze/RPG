package operate;

import data.creature.*;
import data.map.*;
import data.treasure.*;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EventService eventService;//控制觸發事件
        OperateService operateService;  //腳色操作

        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入角色名稱");
        String name = Input.filterBlankString();
        Player player =new Player(name);

        //新建對象(操作,環境)
        OperateService = new OperateService (player);
        eventService  = new EventService(player,operateService);

        //基礎操作
        while (true) {
            String operate = Input.getOperate();
            System.out.println("要做什麼呢？\ngo -> 往前走一步\nstatus -> 查看人物狀態\nsupply -> 顯示物品欄");
            switch (operate) {
                case "go":
//                if(map.getSteps<=5){
//                    //隨機事件1~5
//
//                    map.setSteps(getSteps+1);
//
//                }else{
//                    //Boss 戰
//                    exeEvent(MEET_ACTIVE_ENEMY);
//                    map.setSteps(getSteps+1);
//                }
                    break;
                case "status":

                    break;
                case "supply":
                    boolean isItem = false;
                    while (!isItem) {
                        System.out.println("請輸入1~5 status 顯示相關的物品說明\t\n " +
                                "1~5 use 使用該物品\t\n " +
                                "exit 離開物品欄\n\t");
                        int itemIndex = itemIndex();//選擇物品序號(0~4)
                        String item = item();//選擇顯示說明 使用 離開

                        switch (item) {
                            case "status":
//                        item.tostring(itemIndex);//調用物品方法(陣列序號)
                                break;
                            case "use":
//                        item.use(itemIndex);//調用使用物品方法(陣列序號)
                                break;
                            case "exit":
                                isItem = true;
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    break;
            }
//            //判斷BOSS alive or player hp<0 or change map
//            if (!map.BossAlive) {
//                if (in Forest map) {
//                    eventService.setMap(map);//換地圖
//                } else if (in Abyss map) {
//                    eventService.setMap(map);//換地圖
//                } else if(!map.BossAlive && !mapBossAlive){
//                    System.out.println("game pass!!");
//                }
//
//            } else if (player.gethp() < 0) {
//                TextEvent(MEET_ACTIVE_ENEMY);
//            }

        }
    }


    /**
     * 選擇物品 限制輸入
     * @return
     */
    private static int itemIndex() {
        while (true) {
            try {
                int input = sc.nextInt();
                while ((input < 0) || (input > 5)) {
                    System.out.println("請輸入有效選項:");
                    input = sc.nextInt();
                }
                String clear = sc.nextLine();
                return input;
            } catch (Exception e) {
                System.out.println("輸入錯誤請重新輸入 \"go\",\"status\",\"supply\"");
                String clear = sc.nextLine();//清除無用字符
            }
        }
    }

    //操作的輸入限制
    private static String item() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.nextLine();
                if ("status".equals(input) || "use".equals(input) || "exit".equals(input)) {
                    String clear = sc.nextLine();
                    return input;
                }
            } catch (Exception e) {
                System.out.println("輸入錯誤請重新輸入 \"status\",\"use\",\"exit\"");
                String clear = sc.nextLine();//清除無用字符
            }
        }
    }
}
