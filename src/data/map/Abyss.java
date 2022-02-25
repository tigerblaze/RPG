package data.map;

import java.util.List;
import java.util.Random;

/**
 * @author Lillian
 * @Description 地圖-深淵
 * @date 2022/2/18 下午 08:34
 */
public class Abyss extends RpgMap {
    /**
     * 會生成的敵人種類
     */
    public enum Monster{
        MAGIC_WOLF, WEASEL, GHOST, BAHAMUT;
        private static final List<Abyss.Monster> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static Abyss.Monster randomMonster()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    /**
     * 會掉落的調落物種類
     */
    public enum Treasure{
        LEATHER_ARMOR, STRENGTH_ENHANCE_LOTION, HEALING_LOTION;
        private static final List<Treasure> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static Treasure randomTreasure()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    @Override
    public String toString() {
        return "深淵";
    }
}
