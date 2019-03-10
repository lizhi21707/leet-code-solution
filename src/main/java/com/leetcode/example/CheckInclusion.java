package com.leetcode.example;

/**
 * Description:
 *
 * @author:
 * @date: 2019/03/09
 */
public class CheckInclusion {
    /**
     * 567.字符串的排列
     * https://leetcode-cn.com/problems/permutation-in-string/
     * 第一个字符串的排列之一是第二个字符串的子串
     */
    public boolean checkInclusion(String s1, String s2) {

        if (s1 == null || s2 == null) {
            return false;
        }

        if (s1.length() == 0 && s2.length() == 0) {
            return false;
        }

        if (s1.length() > s2.length()) {
            return false;
        }

        int[] character = new int[26];
        int[] characterOrigin = new int[26];

        //构建长度登录s1的窗口,每次平移,判断集合是否相等
        for (int i = 0; i < s1.length(); i++) {
            character[s1.charAt(i) - 'a'] = character[s1.charAt(i) - 'a'] + 1;
            characterOrigin[s2.charAt(i) - 'a'] = characterOrigin[s2.charAt(i) - 'a'] + 1;
        }

        if (characterEquals(character, characterOrigin)) {
            return true;
        }

        int idx = s1.length();
        for (int i = idx; i < s2.length(); i++) {
            final int leftIndex = i - s1.length();
            final int leftIndexFrequency = s2.charAt(leftIndex) - 'a';
            if (characterOrigin[leftIndexFrequency] > 0) {
                characterOrigin[leftIndexFrequency] = characterOrigin[leftIndexFrequency] - 1;
            }
            final int currentIndexFrequency = s2.charAt(i) - 'a';
            characterOrigin[currentIndexFrequency] = characterOrigin[currentIndexFrequency] + 1;
            if (characterEquals(character, characterOrigin)) {
                return true;
            }
        }

        return false;
    }

    private boolean characterEquals(int[] character, int[] characterOrigin) {
        for (int i = 0; i < character.length; i++) {
            if (character[i] != characterOrigin[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "hello", s2 = "ooolleoooleh";
        final CheckInclusion soluiton = new CheckInclusion();
        final boolean result = soluiton.checkInclusion(s1, s2);
        System.out.println(result);
    }
}
