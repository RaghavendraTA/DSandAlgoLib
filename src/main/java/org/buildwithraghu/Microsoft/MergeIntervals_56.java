import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
	
	public int[][] merge(int[][] intervals) {
		if (intervals.length == 0) return new int[][]{};
		
		Arrays.sort(intervals, (a,b) -> (a[0]-b[0]));
        List<int[]> ans = new LinkedList<>();
		ans.add(intervals[0]);
		
		for(int u = 1; u < intervals.length; u++) {
			if (ans.getLast()[1] >= intervals[u][0])
				ans.getLast()[1] = Math.max(ans.getLast()[1], intervals[u][1]);
			else
				ans.add(intervals[u]);
		}
		return ans.toArray(new int[ans.size()][]);
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Testing");
	}
}