package com.leetcode.example;

/**
 * Description:
 *
 * @author:
 * @date: 2019/03/09
 */
public class LongestCommonPrefix {

    /**
     * 14. 最长公共前缀
     * https://leetcode-cn.com/problems/longest-common-prefix/
     */
    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        //>=2
        String str = strs[0];
        int result = 0;
        outFor:
        for (int i = 0; i < str.length(); i++) {
            //考虑字符串长度不一致的情况
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() < i + 1) {
                    break outFor;
                }
                if (strs[j].charAt(i) != str.charAt(i)) {
                    break outFor;
                }
            }
            result += 1;
        }

        if (result == 0) {
            return "";
        }

        return str.substring(0, result);
    }
    /*
    方法二:分治
        对数组进行分组求LCP,再合并求

    方法三:二分查找
        先找到所有字符串的最短长度;
        再对所有字符串在这个长度上进行二分对比,一旦发现不符合的就立即返回

    方法四:
        对n-1的字符串建立前缀树,最后一个进行匹配
     */

    public static void main(String[] args) {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();

        String[] strs = new String[]{"flower", "flow", "flight"};
        String commonPrefix = longestCommonPrefix.longestCommonPrefix(strs);
        System.out.println(commonPrefix);

        strs = new String[]{"dog", "racecar", "car", ""};
        commonPrefix = longestCommonPrefix.longestCommonPrefix(strs);
        System.out.println(commonPrefix);
    }
}
