package dynamicprogramming.subsequence;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

/**
 *
 * L(i, j) = 1 if A[i]...A[j] is palindrome else 0
 *
 * L(i, i) = 1
 * L(i, j) = L[i, i+1], if A[i] == A[i+1], for 1 <= i <= j <= n-1
 * L(i, j) = L[i+1, j-1] if A[i] == A[j]
 */
public class LongestPalindromeSubstring {

    // dp solution, to get length O(n^2)
    public static int lps(String str) {
        int max = 1;
        int n = str.length();
        int[][] dp = new int[n][n];

        for(int i = 1; i < n; i++) {
            dp[i][i] = 1;
            if (str.charAt(i) == str.charAt(i+1)) {
                dp[i][i+1] = 1;
                max = 2;
            }
            else {
                dp[i][i+1] = 0;
            }
        }

        for(int k = 3; k <= n; k++) {
            for(int i = 1; i <= n-k+1; i++) {
                int j = i + k - 1;
                if (str.charAt(i) == str.charAt(j) && dp[i+1][j-1] == 1) {
                    dp[i][j] = 1;
                    max = k;
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }

        return max;
    }

    // dp solution to get string O(n^2)
    public static String lps_string(String str) {
        return "";
    }

    public static String lps_using_binary_search(String str) {
        if (str == null || str.length() == 0 || str.length() == 1)
            return str;
        if (str.length() == 2)
            return str.charAt(0) == str.charAt(1) ? str : str.charAt(0) + "";

        return str;
    }

    // Mancher's Algorithm O(n)
    public static String manchers_lps(String str) {
        return "";
    }

    public static void main(String[] args) {
        String name = "bb";
        System.out.println(lps_using_binary_search(name));
    }
}
