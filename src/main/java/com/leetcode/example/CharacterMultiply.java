package com.leetcode.example;

import java.util.Arrays;

/**
 * Description:
 *
 * @author:
 * @date: 2019/03/09
 */
public class CharacterMultiply {
    /**
     * 43.字符串相乘
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

        char[] num1CharArray = num1.toCharArray();
        int[] num1IntArray = reverse(num1CharArray);

        char[] num2CharArray = num2.toCharArray();
        int[] num2IntArray = reverse(num2CharArray);

        for (int i = 0; i < num1IntArray.length; i++) {
            for (int j = 0; j < num2IntArray.length; j++) {
                result[i + j] += num1IntArray[i] * num2IntArray[j];
            }
        }

        System.out.println(Arrays.toString(result));
        for (int i = 0; i < result.length; i++) {
            if (result[i] >9) {
                result[i + 1] += result[i] / 10;
                result[i] %= 10;
            }
            System.out.println(Arrays.toString(result));
        }

        int lastNotZero = resultLength - 1;
        while (lastNotZero >= 0 && result[lastNotZero] == 0) {
            lastNotZero--;
        }

        if (lastNotZero < 0) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (lastNotZero >= 0) {
            stringBuilder.append((char) (result[lastNotZero] + '0'));
            lastNotZero--;
        }

        return stringBuilder.toString();
    }

    private int[] reverse(char[] num1CharArray) {

        if (num1CharArray.length <= 0) {
            return new int[0];
        }

        int from = 0;
        int end = num1CharArray.length - 1;
        int[] result = new int[num1CharArray.length];
        while (from <= end) {
            result[end] = num1CharArray[from] - '0';
            result[from] = num1CharArray[end] - '0';
            from++;
            end--;
        }

        return result;
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

    public String multiply2(String num1, String num2) {

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
        final String multiplyResult = solution.multiply("2", "3");

        System.out.println(multiplyResult);
    }

    // public String multiply(string num1, string num2) {
    //     int x[120] = {0},y[120] = {0},z[250] = {0};
    //     int len1 = num1.size(),len2 = num2.size();
    //     倒序
    //     for(int i = len1-1,k = 0;i>=0;i--)
    //         x[k++] = num1[i]-'0';
    //     for(int i = len2-1,k = 0;i>=0;i--)
    //         y[k++] = num2[i]-'0';
    //     //在这里进行相乘，但没进位
    //     for(int i = 0;i<len1;i++) {
    //         for(int j = 0;j<len2;j++)
    //             z[i+j] += (x[i]*y[j]);
    //     }
    //     //现在进位
    //     for(int i = 0;i<249;i++) {
    //         if(z[i]>9){
    //             z[i+1] += z[i]/10;
    //             z[i]%=10;
    //         }
    //     }
    //     int i;
    //     for(i = 249;i>=0;i--)
    //         if(z[i] != 0)
    //             break;
    //     string res = "";
    //     for(;i>=0;i--)
    //         res+=(z[i]+'0');
    //     if(res == "") return "0";
    //
    //     return res;
    // }
}
