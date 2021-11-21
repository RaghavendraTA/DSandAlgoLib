package sets.basic;

/*
 * created by raghavendra.ta on 27-Jun-2021
 */

import java.util.HashMap;
import java.util.Map;

public class SetMinus {

    public static Map<Integer, Integer> map = new HashMap<>();

    public static void A_B_and_B_A(int[] A, int[] B) {

        for(int i: A) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for(int j: B) {
            if (map.containsKey(j)) {
                map.put(j, map.getOrDefault(j, 0) - 1);
            } else {
                map.put(j, -1);
            }
        }
    }

    public static void main(String[] args) {

        int[] A = new int[]{1, 2, 3, 4, 4, 7};
        int[] B = new int[]{3, 4, 5, 5, 6, 9, 10};
        A_B_and_B_A(A, B);

        System.out.println("A - B =>");

        for(int i : A) {
            if (map.getOrDefault(i, 0) > 0) {
                for(int k = 0; k < map.get(i); k++)
                    System.out.print(i + " ");
                map.put(i, 0);
            }
        }

        System.out.println("\nB - A =>");

        for(int j : B) {
            if (map.getOrDefault(j, 0) < 0) {
                for(int k = 0; k > map.get(j); k--)
                    System.out.print(j + " ");
                map.put(j, 0);
            }
        }
    }

}
