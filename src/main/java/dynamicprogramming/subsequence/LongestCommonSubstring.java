package dynamicprogramming.subsequence;

/*
 * created by raghavendra.ta on 15-Jul-2021
 */

public class LongestCommonSubstring {

    public static int longestCommonSubstring(String original, String str) {

        int m = original.length(), n = str.length();
        int[][] dp = new int[m][n];
        int result = 0;

        for(int i = 0; i < m; i++) {
            if (original.charAt(i) == str.charAt(0))
                dp[i][0] = 1;
        }

        for(int j = 0; j < n; j++) {
            if (original.charAt(0) == str.charAt(j))
                dp[0][j] = 1;
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if (original.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String X = "raghuchecking";
        String Y = "raghuhekdance";

        System.out.println(longestCommonSubstring(X, Y));
    }

}
