package data.treasure.prop;

import java.util.List;
import java.util.Random;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/23 下午 03:21
 */
public abstract class Badge extends Prop{
    enum BadgeType{
        ANIMAL_SLAUGHTER_BADGE, MONSTER_SLAUGHTER_BADGE;
        private static final List<Badge.BadgeType> VALUES = List.of(values());
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();
        public static Badge.BadgeType randomMonster()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
}
