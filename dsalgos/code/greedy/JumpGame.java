package greedy;

/*
 * created by raghavendra.ta on 12-Jul-2021
 */

public class JumpGame {

    public static boolean canJump(int[] arr) {
        int targetIdx = arr.length - 1;
        for(int i = arr.length - 2; i >= 0; i--) {
            if (i + arr[i] >= targetIdx)
                targetIdx = i;
        }
        if (targetIdx == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(canJump(nums));

        nums = new int[]{3,2,1,0,4};
        System.out.println(canJump(nums));
    }
}
