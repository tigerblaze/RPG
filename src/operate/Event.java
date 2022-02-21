package operate;

import data.map.Abyss;

import java.util.List;
import java.util.Random;

public enum Event {
    NOTHING, MEET_PASSIVE_ENEMY, MEET_ACTIVE_ENEMY, BRANCH, GET_TREASURE;
    private static final List<Event> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    public static Event randomEvent()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
