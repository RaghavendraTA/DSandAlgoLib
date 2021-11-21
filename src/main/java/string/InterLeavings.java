package string;

/*
 * created by raghavendra.ta on 12-Jul-2021
 */

public class InterLeavings {

    static void printInterleavings(String str1, String str2, StringBuilder ans, int m, int n, int i) {

        if (m == 0 && n == 0) {
            System.out.println(ans);
        }

        if (m != 0) {
            ans.setCharAt(i, str1.charAt(0));
            printInterleavings(str1.substring(1), str2, ans, m-1, n, i+1);
        }

        if(n != 0) {
            ans.setCharAt(i, str2.charAt(0));
            printInterleavings(str1, str2.substring(1), ans, m, n-1, i+1);
        }
    }

    static void print(String str1, String str2) {
        StringBuilder ans = new StringBuilder(str1 + str2);
        printInterleavings(str1, str2, ans, str1.length(), str2.length(), 0);
    }

    public static void main(String[] args) {
        print("AB", "CD");
    }
}
