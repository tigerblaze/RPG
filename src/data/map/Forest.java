package data.map;

import java.util.List;
import java.util.Random;

/**
 * @author Lillian
 * @Description 地圖-森林
 * @date 2022/2/18 下午 08:33
 */
public class Forest extends RpgMap {
    /**
     * 會生成的敵人種類
     */
    public enum Animal{
        WOLF, LION, BOAR, ELEPHANT;
        private static final List<Animal> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static Animal randomAnimal()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    /**
     * 會掉落的調落物種類
     */
    public enum Treasure{
        HEALING_LOTION, STRENGTH_ENHANCE_LOTION, ARROW;
        private static final List<Treasure> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static Treasure randomTreasure()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    @Override
    public String toString() {
        return "森林";
    }
}
