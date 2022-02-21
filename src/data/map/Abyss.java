package data.map;

import java.util.List;
import java.util.Random;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/18 下午 08:34
 */
public class Abyss extends Map{
    public enum Monster{
        MAGICWOLF, WEASEL, GHOST;//, BAHAMUT;
        private static final List<Abyss.Monster> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static Abyss.Monster randomMonster()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
    public enum Treasure{
        lEATHERARMOR, STRENGTHENHANCELOTION, HEALINGLOTION;
        private static final List<Treasure> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static Treasure randomTreasure()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
}
