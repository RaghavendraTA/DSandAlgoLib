package recursion;

/*
 * created by raghavendra.ta on 22-Jun-2021
 */

import org.junit.jupiter.api.Test;

public class TowerOfHanoiTest {

    @Test
    public void testTower() {
        TowerOfHanoi.towersOfHanoi(4, 'A', 'C', 'B');
    }
}
