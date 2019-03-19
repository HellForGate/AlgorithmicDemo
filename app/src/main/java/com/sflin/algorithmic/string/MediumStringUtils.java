package com.sflin.algorithmic.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created MagicFrost.
 *
 * 中等字符串算法
 */
public class MediumStringUtils {

    /**
     * 分割回文串
     *
     * @param s
     * @return
     *
     * 示例
     *
     * 输入: "aab"
     * 输出:
     * [
     *   ["aa","b"],
     *   ["a","a","b"]
     * ]
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> lists = new ArrayList<>();
        partition(lists,s,0,new ArrayList<String>());
        return lists;
    }

    private static void partition(List<List<String>> lists,String s,int index,List<String> list) {
        if (index == s.length()){
            lists.add(new ArrayList<String>(list));
        }
        for (int i=index;i<s.length();i++){
            String str = s.substring(index,i+1);
            if (isPalindrome(str)){
                list.add(str);
                partition(lists,s,i+1,list);
                list.remove(list.size()-1);
            }
        }
    }
    private static boolean isPalindrome(String s) {
        for (int i = 0;i < s.length()/2;i ++){
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 单词拆分
     *
     * @param s
     * @param wordDict
     * @return
     *
     * 示例
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     */
//    public static boolean wordBreak(String s, List<String> wordDict) {
//        int len = s.length();
//        boolean[] dp = new boolean[len + 1];
//        dp[0] = true;
//        for (int i = 1; i <= len; i++)
//            for (int j = 0; j < i; j++) {
//                String tmp = s.substring(j, i);
//                if (dp[j] && wordDict.contains(tmp)) {
//                    dp[i] = true;
//                    break;
//                }
//            }
//        return dp[len];
//    }
    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        int max_length=0;
        for(String temp:wordDict){
            max_length = temp.length()>max_length? temp.length():max_length;
        }
        boolean[] memo = new boolean[n + 1];

        memo[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i-1; j >=0 && max_length>=i-j; j--) {
                if (memo[j] && wordDict.contains(s.substring(j, i))) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[n];
    }
}
