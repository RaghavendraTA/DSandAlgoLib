package recursion;

/*
 * created by raghavendra.ta on 22-Jun-2021
 */

public class TowerOfHanoi {

    public static void towersOfHanoi(int n, char from, char to, char aux) {

        if (n == 1) {
            System.out.println("Move disk 1 from " + from + " to " + to);
            return;
        }

        towersOfHanoi(n-1, from, aux, to);

        System.out.println("Move disk " + n + " from " + from + " to " + to);

        towersOfHanoi(n-1, aux, to, from);
    }

}
