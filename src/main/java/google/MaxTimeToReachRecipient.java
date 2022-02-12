package google;

/*
 * created by raghavendra.ta on 21-Jan-2022
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaxTimeToReachRecipient {

    static final class People {
        int timeTaken;
        List<People> children;
        public People(int timeTaken) {
            this.timeTaken = timeTaken;
            this.children = new ArrayList<>();
        }
    }

    public static final People root = new People(0);

    public static final Random random = new Random();

    public static void addRandomChildren(People[] managers, int j, int n) {
        People p = managers[j];
        for(int i = 0; i < n; i++) {
            p.children.add(new People(random.nextInt(n)));
        }
    }

    public static void insert() {
        People[] managers = new People[3];
        for(int i = 0; i < 3; i++) {
            managers[i] = new People(i + 1);
            addRandomChildren(managers, i, 100);
            root.children.add(managers[i]);
        }
    }

    public static void travers(People node) {

    }

    public static void main(String[] args) {
        insert();
    }
}
