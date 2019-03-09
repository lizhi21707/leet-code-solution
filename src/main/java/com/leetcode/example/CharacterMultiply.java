package com.leetcode.example;

/**
 * Description:
 *
 * @author: lizhi1@corp.netease.com
 * @date: 2019/03/09
 */
public class CharacterMultiply {
    /**
     * 字符串相乘
     * https://leetcode-cn.com/problems/multiply-strings/
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     */
    public String multiply(String num1, String num2) {

        if (num1 == null || num2 == null) {
            return null;
        }

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int resultLength = num1.length() + num2.length();
        int[] result = new int[resultLength];

        //分成两步
        //第一步,按位乘
        //第二步,执行加法

        final char[] num2Chars = num2.toCharArray();
        int minBit = 0;
        for (int i = num2Chars.length - 1; i >= 0; i--) {
            char single = num2Chars[i];
            int[] tempResult = multiply(num1.toCharArray(), single);
            if (!isZero(tempResult)) {
                result = plus(tempResult, minBit, result);
            }
            minBit++;
        }

        return printResult(result);
    }

    private String printResult(int[] result) {
        StringBuilder stringBuilder = new StringBuilder();
        int idx = 0;
        while (idx < result.length && result[idx] == 0) {
        }

        if (idx == result.length) {
            return "";
        }
        for (; idx < result.length; idx++) {
            stringBuilder.append((char) (result[idx] + '0'));
        }

        return stringBuilder.toString();
    }

    private boolean isZero(int[] chars) {
        if (chars == null) {
            return true;
        }
        for (int aChar : chars) {
            if (0 != aChar) {
                return false;
            }
        }
        return true;
    }

    private int[] plus(int[] multiplyResult, int minBit, int[] finalResult) {

        int j = multiplyResult.length - 1;
        int extra = 0;
        for (int i = finalResult.length - minBit - 1; i >= 0; i--) {
            int tmp = finalResult[i];

            if (j >= 0) {
                tmp += multiplyResult[j];
                j--;
            }

            if (extra > 0) {
                tmp += extra;
                extra = 0;
            }

            if (tmp >= 10) {
                extra = tmp / 10;
                tmp = tmp % 10;
            }

            finalResult[i] = tmp;
        }

        return finalResult;
    }

    private int[] multiply(char[] s, char singleCharacter) {

        if (singleCharacter == '0') {
            return new int[]{0};
        }

        int extra = 0;
        int[] result2 = new int[s.length + 1];
        final int singleCharacterNumber = singleCharacter - '0';
        for (int i = s.length - 1; i >= 0; i--) {
            int temp = (s[i] - '0') * singleCharacterNumber + extra;
            if (temp >= 10) {
                extra = temp / 10;
                temp = temp % 10;
            } else {
                extra = 0;
            }
            result2[i + 1] = temp;
        }

        if (extra >= 0) {
            result2[0] = extra;
        }

        return result2;
    }

    public static void main(String[] args) {

        final CharacterMultiply solution = new CharacterMultiply();
        final String multiplyResult = solution.multiply("999221112222", "99333222333");

        System.out.println(multiplyResult);
    }
}
