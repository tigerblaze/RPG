package data.map;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/18 下午 08:33
 */
public class Forest extends Map{
    public enum Animal{
        WOLF, LION, BOAR;//, ELEPHANT;
        private static final List<Animal> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static Animal randomAnimal()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    public enum Treasure{
        HEALINGLOTION, STRENGTHENHANCELOTION, ARROW;
        private static final List<Treasure> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static Treasure randomTreasure()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
}
