import java.util.ArrayList;
import java.util.List;

class Solution {
	
	List<String> ans;
	
	public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
		rec(0, 0, n, "");
		return ans;
    }
	
	private void rec(int o, int c, int n, String str) {
		if (str.length() == n * 2) {
			ans.add(str);
			return;
		}
		if (o < n)
			rec(o + 1, c, n, str + "(");
		if (c < o)
			rec(o, c + 1, n, str + ")");
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.generateParenthesis(3));
	}
}