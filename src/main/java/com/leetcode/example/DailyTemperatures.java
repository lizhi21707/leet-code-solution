package com.leetcode.example;

import java.util.Arrays;
import java.util.Stack;

/**
 * Description:
 *
 * @author: lizhi1@corp.netease.com
 * @date: 2019/03/10
 */
public class DailyTemperatures {

    /**
     * 739. 每日温度
     * https://leetcode-cn.com/problems/daily-temperatures/
     * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。
     * <p>
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     * <p>
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的都是 [30, 100] 范围内的整数。
     */
    public int[] dailyTemperatures(int[] T) {

        int[] result = new int[T.length];
        Stack<Integer> indexesNotFindLagger = new Stack<>();
        int i = 0;
        while (i < T.length) {
            if (indexesNotFindLagger.empty()) {
                indexesNotFindLagger.push(i);
                i++;
                continue;
            }

            //1. 栈顶元素比当前元素大或等于,说明栈顶元素还没有找到比它大的第一个下标
            //2. 当前元素还没有找到比它大的数,所以当前元素最终也会入栈
            if (T[indexesNotFindLagger.peek()] >= T[i]) {
                indexesNotFindLagger.push(i);
                i++;
                continue;
            }

            //否则,当前元素比栈顶的下标对应的元素要大,说明栈顶要找的第一个比它大的数的下标是栈顶的值

            final Integer peekIndex = indexesNotFindLagger.pop();
            //向后的天数是下标相减
            result[peekIndex] = i - peekIndex;
        }

        while (!indexesNotFindLagger.empty()) {
            T[indexesNotFindLagger.pop()] = -1;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        final DailyTemperatures dailyTemperatures = new DailyTemperatures();
        final int[] ints = dailyTemperatures.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(ints));
    }
}
