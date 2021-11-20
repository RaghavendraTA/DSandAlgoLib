package string;

/*
 * created by raghavendra.ta on 12-Jul-2021
 */

public class RemoveAdjacentCharacter {

    static String removeRepeatingAdjacentPairs(StringBuilder str) {
        int len = str.length();
        int j = 0;
        for (int i = 1; i <= len; i++) {
            while (str.charAt(i) == str.charAt(j) && j >= 0) {
                i++;
                j--;
            }
            j++;
            str.setCharAt(j, str.charAt(i));
        }
        return str.toString();
    }
}
