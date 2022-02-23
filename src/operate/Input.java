package operate;

import java.util.Locale;
import java.util.Scanner;

public class Input {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * 腳色操作的輸入限制
     */
    public static String getOperateInput() {
        while (true) {
            try {
                String input = SCANNER.nextLine();
                input = input.replace(" ","");
                input = input.toLowerCase();
                if ("go".equals(input) || "status".equals(input) || "supply".equals(input)) {
                    String clear = SCANNER.nextLine();
                    return input;
                }
            } catch (Exception e) {
                System.out.println("輸入錯誤請重新輸入 \"go\",\"status\",\"supply\"");
                String clear = SCANNER.nextLine();//清除無用字符
            }
        }
    }

    /**
     * 道具操作的輸入限制
     */
    public static String getItemActionInput() {
        while (true) {
            try {
                String input = SCANNER.nextLine();
                input = input.replace(" ","");
                input = input.toLowerCase(Locale.ROOT);
                if ("status".equals(input) || "use".equals(input) || "exit".equals(input)) {
                    String clear = SCANNER.nextLine();
                    return input;
                }
            } catch (Exception e) {
                System.out.println("輸入錯誤請重新輸入 \"status\",\"use\",\"exit\"");
                String clear = SCANNER.nextLine();//清除無用字符
            }
        }
    }

    /**
     * 檢查是否為有效選項，是的話回傳輸入選項
     *
     * @param choiceStart 選項起始
     * @param choiceEnd   選項結束
     * @return
     */
    public static int filterSelection(int choiceStart, int choiceEnd) {
        do {
            try {
                int input = SCANNER.nextInt();
                while (input < choiceStart || input > choiceEnd) {
                    System.out.println("請輸入有效選項：");
                    input = SCANNER.nextInt();
                }
                String clear = SCANNER.nextLine();
                return input;
            } catch (Exception e) {
                System.out.println("請輸入數字！");
                String clear = SCANNER.nextLine();
            }
        } while (true);
    }

    /**
     * 確保不輸入空白
     */
    public static String filterBlankString() {
        do {

            String input = SCANNER.nextLine();
            while (input.isEmpty()) {
                System.out.println("請輸入有效選項：");
                input = SCANNER.nextLine();
            }
            String clear = SCANNER.nextLine();
            return input;

        } while (true);
    }

}
