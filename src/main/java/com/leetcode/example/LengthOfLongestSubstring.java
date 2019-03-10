package com.leetcode.example;

/**
 * Description:
 *
 * @author:
 * @date: 2019/03/09
 */
public class LengthOfLongestSubstring {

    /**
     * 无重复字符的最长子串
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     */
    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() == 0) {

            return 0;
        }

        char[] chars = s.toCharArray();
        int[] charIndexRecord = new int[128];
        int left = 0;
        int result = 0;
        for (int cursor = 0; cursor < chars.length; cursor++) {
            //字符未出现过
            if (charIndexRecord[chars[cursor]] == 0 || charIndexRecord[chars[cursor]] < left) {
                //计算新的长度
                result = Math.max(cursor - left + 1, result);
            } else {
                //字符出现过,重新计算窗口左边缘
                left = charIndexRecord[chars[cursor]];
            }
            //记录当前字符的出现
            charIndexRecord[chars[cursor]] = cursor + 1;
        }

        return result;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring solution = new LengthOfLongestSubstring();
        int length = solution.lengthOfLongestSubstring("abcabcbbcjpasjporocnwlmrocoiuvueklzxlpvre");
        System.out.println(length);
    }
}
