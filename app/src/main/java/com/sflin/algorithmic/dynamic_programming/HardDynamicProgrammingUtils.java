package com.sflin.algorithmic.dynamic_programming;

import java.util.Arrays;

/**
 * Created by MagicFrost
 *
 * 困难动态规划算法
 */
public class HardDynamicProgrammingUtils {

    /**
     * 鸡蛋掉落
     *
     * @param K
     * @param N
     * @return
     *
     * 示例
     * 输入：K = 2, N = 6
     * 输出：3
     */
    public static int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        for (int m = 1; m <= N; m++) {
            for (int k = 1; k <= K; k++) {
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
                if (dp[k][m] >= N) {
                    return m;
                }
            }
        }
        return N;
    }

    /**
     * 最长连续序列
     *
     * @param nums
     * @return
     * 示例
     * 输入: [100, 4, 200, 1, 3, 2]
     * 输出: 4
     * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length <= 1) return nums.length;
        Arrays.sort(nums);
        int length = 0,tmp = 0;
        for (int i=1;i<nums.length;i++){
            int diff = nums[i]-nums[i-1];
            if(diff == 1){
                tmp++;
            }else if (diff>1){
                length = Math.max(length,tmp+1);
                tmp = 0;
            }
        }
        length = Math.max(length,tmp+1);
        return length;
    }

    /**
     * 矩阵中的最长递增路径
     *
     * @param matrix
     * @return
     *
     * 示例
     * 输入: nums =
     * [
     *   [9,9,4],
     *   [6,6,8],
     *   [2,1,1]
     * ]
     * 输出: 4
     * 解释: 最长递增路径为 [1, 2, 6, 9]。
     */
    int[][] visited;
    int m, n;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int res = 1;
        visited = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (visited[i][j] != 0) continue;
                res = Math.max(res, dfs(matrix, i, j, Integer.MIN_VALUE));
            }
        }
        return res;
    }
    private int dfs(int[][] matrix, int i, int j, int oldVal){
        // 检查当前坐标是否合法，是否递增
        if (i < 0 || i >= m || j < 0 || j >= n || matrix[i][j] <= oldVal) return 0;
        // 检查当前坐标是否已被访问
        if (visited[i][j] == 0){
            // 对四个方向递归调用dfs
            int curVal = matrix[i][j];
            int up = dfs(matrix, i + 1, j, curVal);
            int down = dfs(matrix, i - 1, j, curVal);
            int right = dfs(matrix, i, j + 1, curVal);
            int left = dfs(matrix, i, j - 1, curVal);
            // 设置当前坐标的最长递增路径
            visited[i][j] = 1 + Math.max(Math.max(up, down), Math.max(right, left));
        }

        return visited[i][j];
    }

    /**
     * 76. 最小覆盖子串
     *
     * @param s
     * @param t
     * @return
     *
     * 示例
     * 输入: S = "ADOBECODEBANC", T = "ABC"
     * 输出: "BANC"
     */
    public static String minWindow(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {
            return "";
        }
        //用来统计t中每个字符出现次数
        int[] needs = new int[128];
        //用来统计滑动窗口中每个字符出现次数
        int[] window = new int[128];

        for (int i = 0; i < t.length(); i++) {
            needs[t.charAt(i)]++;
        }

        int left = 0;
        int right = 0;

        String res = "";

        //目前有多少个字符
        int count = 0;

        //用来记录最短需要多少个字符。
        int minLength = s.length() + 1;

        while (right < s.length()) {
            char ch = s.charAt(right);
            window[ch]++;
            if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                count++;
            }

            //移动到不满足条件为止
            while (count == t.length()) {
                ch = s.charAt(left);
                if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                    count--;
                }
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    res = s.substring(left, right + 1);

                }
                window[ch]--;
                left++;

            }
            right++;

        }
        return res;
    }

    public int longestValidParentheses(String s) {
        if (s.length() < 2) return 0;

        boolean[][] dp = new boolean[s.length()][s.length()];


        return 0;
    }
}
