package org.buildwithraghu.dynamicprogramming;

/**
 * Comprehensive LCS implementation showing:
 * - Recurrence relation explanation
 * - Naive recursion (exponential)
 * - Memoization (top-down)
 * - Bottom-up (iterative) - row by row
 * - Space-optimized (1D array)
 * - Reconstruction of actual subsequence
 */
public class LongestCommonSubsequence {

    // https://leetcode.com/problems/longest-common-subsequence/

    // ==================== 1. Naive Recursion (EXPLICIT - DO NOT USE) ====================
    // Time: O(2^(m+n)), Space: O(m+n) for recursion stack
    public int naiveRecursion(String text1, String text2, int i, int j) {
        if (i == 0 || j == 0)
            return 0;
        if (text1.charAt(i-1) == text2.charAt(j-1))
            return 1 + naiveRecursion(text1, text2, i-1, j-1);
        else
            return Math.max(naiveRecursion(text1, text2, i-1, j),
                           naiveRecursion(text1, text2, i, j-1));
    }

    public int longestCommonSubsequenceNaive(String text1, String text2) {
        return naiveRecursion(text1, text2, text1.length(), text2.length());
    }

    // ==================== 2. Recurrence Relation ====================
    // dp[i][j] = LCS of text1[0..i-1] and text2[0..j-1]
    // If text1[i-1] == text2[j-1]: dp[i][j] = dp[i-1][j-1] + 1
    // Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])

    // ==================== 3. Memoization (Top-Down) ====================
    // Time: O(mn), Space: O(mn)
    private int[][] memo;

    public int longestCommonSubsequenceMemo(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        memo = new int[m+1][n+1];
        for(int i = 0; i <= m; i++)
            for(int j = 0; j <= n; j++)
                memo[i][j] = -1;  // -1 indicates uncomputed

        return solve(text1, text2, text1.length(), text2.length());
    }

    private int solve(String text1, String text2, int i, int j) {
        if (i == 0 || j == 0)
            return 0;
        if (memo[i][j] != -1)
            return memo[i][j];

        if (text1.charAt(i-1) == text2.charAt(j-1))
            memo[i][j] = 1 + solve(text1, text2, i-1, j-1);
        else
            memo[i][j] = Math.max(solve(text1, text2, i-1, j),
                                  solve(text1, text2, i, j-1));

        return memo[i][j];
    }

    // ==================== 4. Bottom-Up (Iterative) - Row by Row ====================
    // Time: O(mn), Space: O(mn)
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m+1][n+1];

        // dp[i][j] stores LCS of text1[0..i-1] and text2[0..j-1]
        // Base case: dp[0][j] = dp[i][0] = 0 (empty string has LCS = 0)

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;  // Match: extend previous LCS
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);  // Skip one char
                }
            }
        }
        return dp[m][n];
    }

    // ==================== 5. Space-Optimized (1D Array) ====================
    // Time: O(mn), Space: O(min(m,n))
    // Only keep one row since we only need previous row values
    public int longestCommonSubsequenceOptimized(String text1, String text2) {
        int m = text1.length(), n = text2.length();

        // Ensure text1 is smaller to minimize space
        if (m > n) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
            int t = m;
            m = n;
            n = t;
        }

        int[] dp = new int[n+1];

        for (int i = 1; i <= m; i++) {
            int prev = 0;  // Stores dp[i-1][j-1]
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];  // Save current value (was dp[i-1][j])
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
                prev = temp;  // Update for next iteration
            }
        }
        return dp[n];
    }

    // ==================== 6. Reconstruct Actual Subsequence ====================
    public String reconstructLCS(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m+1][n+1];

        // Fill DP table
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // Backtrack to find the actual subsequence
        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (text1.charAt(i-1) == text2.charAt(j-1)) {
                sb.append(text1.charAt(i-1));  // This char is part of LCS
                i--; j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                i--;  // Move up
            } else {
                j--;  // Move left
            }
        }
        return sb.reverse().toString();
    }

    // ==================== 7. Find All LCS Strings ====================
    // Returns all unique LCS strings (useful for small inputs)
    public java.util.List<String> findAllLCS(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m+1][n+1];

        // Fill DP table
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        java.util.Set<String> result = new java.util.HashSet<>();
        backtrackAllLCS(text1, text2, m, n, dp, "", result);

        return new java.util.ArrayList<>(result);
    }

    private void backtrackAllLCS(String text1, String text2, int i, int j,
                                  int[][] dp, String current,
                                  java.util.Set<String> result) {
        if (i == 0 || j == 0) {
            result.add(current);
            return;
        }

        if (text1.charAt(i-1) == text2.charAt(j-1)) {
            backtrackAllLCS(text1, text2, i-1, j-1, dp, current + text1.charAt(i-1), result);
        } else {
            if (dp[i-1][j] >= dp[i][j-1]) {
                backtrackAllLCS(text1, text2, i-1, j, dp, current, result);
            }
            if (dp[i][j-1] >= dp[i-1][j]) {
                backtrackAllLCS(text1, text2, i, j-1, dp, current, result);
            }
        }
    }

    // ==================== Main Method with Tests ====================
    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();

        System.out.println("=== LCS Dynamic Programming - Comprehensive Tests ===\n");

        // Test 1: Basic case
        String text1 = "ABCBDAB";
        String text2 = "BDCABA";
        System.out.println("Test 1: " + text1 + " and " + text2);
        System.out.println("  LCS length: " + lcs.longestCommonSubsequence(text1, text2));
        System.out.println("  LCS (one example): " + lcs.reconstructLCS(text1, text2));
        System.out.println("  All LCS strings: " + lcs.findAllLCS(text1, text2));

        System.out.println("\nTest 2: " + "programming" + " and " + "gaming");
        System.out.println("  LCS length: " + lcs.longestCommonSubsequence("programming", "gaming"));
        System.out.println("  LCS: " + lcs.reconstructLCS("programming", "gaming"));

        System.out.println("\nTest 3: " + "AGGTAB" + " and " + "GXTXAYB");
        System.out.println("  LCS length: " + lcs.longestCommonSubsequence("AGGTAB", "GXTXAYB"));
        System.out.println("  LCS: " + lcs.reconstructLCS("AGGTAB", "GXTXAYB"));

        System.out.println("\nTest 4: No common characters");
        System.out.println("  LCS length: " + lcs.longestCommonSubsequence("abc", "xyz"));

        System.out.println("\nTest 5: One string is substring of another");
        System.out.println("  LCS length: " + lcs.longestCommonSubsequence("abc", "abc"));
        System.out.println("  LCS: " + lcs.reconstructLCS("abc", "abc"));

        System.out.println("\nTest 6: Empty strings");
        System.out.println("  LCS length: " + lcs.longestCommonSubsequence("", "abc"));
        System.out.println("  LCS length: " + lcs.longestCommonSubsequence("abc", ""));

        // Test 7: Compare different implementations
        System.out.println("\n=== Comparing Different Implementations ===");
        String s1 = "ABCDGH";
        String s2 = "AEDFHR";
        System.out.println("Texts: " + s1 + " and " + s2);
        System.out.println("  Memoization: " + lcs.longestCommonSubsequenceMemo(s1, s2));
        System.out.println("  Bottom-up: " + lcs.longestCommonSubsequence(s1, s2));
        System.out.println("  Space-optimized: " + lcs.longestCommonSubsequenceOptimized(s1, s2));
        System.out.println("  Reconstructed LCS: " + lcs.reconstructLCS(s1, s2));

        System.out.println("\n=== Demo: Space Optimization Trade-off ===");
        System.out.println("Original strings (shorter): " + s1 + " (" + s1.length() + ")");
        System.out.println("Original strings (longer): " + s2 + " (" + s2.length() + ")");
        System.out.println("Space used (2D): O(" + s1.length() + " x " + s2.length() + ") = " + (s1.length() * s2.length()) + " integers");
        System.out.println("Space used (1D): O(min(" + s1.length() + "," + s2.length() + ")) = " + Math.min(s1.length(), s2.length()) + " integers");
        System.out.println("Space savings: ~" + String.format("%.1f", (1.0 - (Math.min(s1.length(), s2.length()) / (float)(s1.length() * s2.length()))) * 100) + "%");

        System.out.println("\n=== Understanding the DP Table ===");
        System.out.println("Processing order for " + s1 + " and " + s2 + ":");
        int m = s1.length(), n = s2.length();
        int[][] demoDp = new int[m+1][n+1];
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    demoDp[i][j] = demoDp[i-1][j-1] + 1;
                } else {
                    demoDp[i][j] = Math.max(demoDp[i-1][j], demoDp[i][j-1]);
                }
            }
        }
        System.out.print("       ");
        System.out.print("  ");
        for(int j = 0; j <= n; j++) {
            System.out.print((j < n ? s2.charAt(j) : " ") + "   ");
        }
        System.out.println();
        for(int i = 0; i <= m; i++) {
            System.out.print((i < m ? s1.charAt(i) : " ") + "  ");
            for(int j = 0; j <= n; j++) {
                System.out.print(demoDp[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println("       ^");
        System.out.println("       Final answer: " + demoDp[m][n]);

        System.out.println("\n=== Key Insights ===");
        System.out.println("1. Overlapping subproblems: LCS(m,n) calls LCS(m-1,n), LCS(m,n-1), LCS(m-1,n-1)");
        System.out.println("2. Optimal substructure: LCS solution builds on smaller LCS solutions");
        System.out.println("3. Without memoization: O(2^(m+n)) - exponential time");
        System.out.println("4. With memoization: O(mn) - each cell computed once");
        System.out.println("5. Bottom-up fills table in order: (1,1), (1,2), ..., (m,n)");
        System.out.println("6. Backtracking goes backwards: (m,n) -> ... -> (0,0)");
    }
}
