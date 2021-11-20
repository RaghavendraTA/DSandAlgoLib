package dynamicprogramming.subsequence;

/*
 * created by raghavendra.ta on 27-Jun-2021
 */

public class LongestCommonSubsequence {

    public static int lcsUsingRecursion(int i, int j, String original, String str) {

        if (i >= original.length() || j >= str.length()) {
            return 0;
        }
        else if (original.charAt(i) == str.charAt(j)) {
            return 1 + lcsUsingRecursion(i + 1, j + 1, original, str);
        }
        else {
            return Math.max(lcsUsingRecursion(i + 1, j, original, str), lcsUsingRecursion(i, j + 1, original, str));
        }
    }

    public static int lcsUsingDP(String original, String str) {

        int m = original.length(), n = str.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
                else if (original.charAt(i - 1) == str.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        printLCS(dp, original, str);

        return dp[m][n];
    }

    public static void printLCS(int[][] dp, String original, String str) {

        int m = original.length(), n = str.length();
        int i = m, j = n;
        int index = dp[m][n];

        StringBuilder lcs = new StringBuilder(original.substring(0, index));

        while (i > 0 && j > 0) {
            if (original.charAt(i - 1) == str.charAt(j - 1)) {
                lcs.setCharAt(index - 1, original.charAt(i - 1));
                i--;
                j--;
                index--;
            }
            else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            }
            else {
                j--;
            }
        }

        System.out.println(lcs);
    }

    public static void main(String[] args) {

        System.out.println(lcsUsingRecursion(0, 0, "ABCBDABEFGH", "BDCABAFH"));
        System.out.println(lcsUsingDP("ABCBDABEFGH", "BDCABAFH"));

        System.out.println(lcsUsingDP("ABCDEF", "ACEF"));
    }
}
