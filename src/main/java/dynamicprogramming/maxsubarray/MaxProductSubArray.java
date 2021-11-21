package dynamicprogramming.maxsubarray;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

/** calculating temp_max => MaxOfBelowThree
 *                         curr
 *  max_so_far * curr               min_so_far * cur
 *
 *  calculating max_so_far => temp_max
 *
 *  calculating min_so_far =>
 *                         curr
 *  max_so_far * curr               min_so_far * cur
 */
public class MaxProductSubArray {

    public int maxProduct(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int max_so_far = nums[0];
        int min_so_far = nums[0];
        int result = max_so_far;

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int temp_max = Math.max(curr, Math.max(max_so_far * curr, min_so_far * curr));
            min_so_far = Math.min(curr, Math.min(max_so_far * curr, min_so_far * curr));

            max_so_far = temp_max;

            result = Math.max(max_so_far, result);
        }

        return result;
    }
}
