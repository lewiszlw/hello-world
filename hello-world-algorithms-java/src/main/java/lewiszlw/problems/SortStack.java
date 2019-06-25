package lewiszlw.problems;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Desc: 栈排序
 * 将一个栈内的元素进行升序（栈顶最大）排序，要求额外空间只能使用栈
 *
 * @author zhanglinwei02
 * @date 2019-06-25
 */
public class SortStack {

    /**
     * 解法：
     * 利用一个降序排列的辅助栈，如果主栈栈顶元素小于降序栈栈顶元素，则将主栈元素直接压到降序栈中，
     * 否则利用主栈来将主栈栈顶元素插入到降序栈中，保持降序栈的降序排列。直到主栈元素全部压到降序
     * 栈中，最后将降序栈元素依次弹出压入到主栈即可。
     */
    public void sort(Stack<Integer> stack) {
        int size = stack.size();
        // 降序辅助栈
        Stack<Integer> descStack = new Stack<>();
        while (descStack.size() != size) {
            if (descStack.empty() || stack.peek() <= descStack.peek()) {
                descStack.push(stack.pop());
            } else {
                // 主栈栈顶元素保存到临时变量
                Integer tmp = stack.pop();
                // 记录辅栈移动元素到主栈次数
                int count = 0;
                while (!descStack.empty()) {
                    stack.push(descStack.pop());
                    count ++;
                    if (descStack.empty() || tmp <= descStack.peek()) {
                        descStack.push(tmp);
                        // 将主栈保持的临时元素移动回来
                        move(stack, descStack, count);
                        break;
                    }
                }
            }
        }
        // 将降序栈元素依次弹出压入到主栈
        move(descStack, stack, null);
    }

    /**
     * 将 from 栈依次弹出 expectCount 个元素压入到 to 栈中，
     * expectCount 为 null 或者负数代表全体元素移动
     */
    private void move(Stack from, Stack to, Integer expectCount) {
        int realCount = 0;
        while (!from.empty()) {
            to.push(from.pop());
            realCount ++;
            if (expectCount != null && realCount == expectCount) {
                break;
            }
        }
    }


    @Test
    public void test() {
        Stack<Integer> stack = new Stack();
        stack.push(3);
        stack.push(8);
        stack.push(5);
        stack.push(9);
        stack.push(7);
        stack.push(4);
        stack.push(1);
        sort(stack);
        Assert.assertTrue(stack.pop() == 9);
        Assert.assertTrue(stack.pop() == 8);
        Assert.assertTrue(stack.pop() == 7);
        Assert.assertTrue(stack.pop() == 5);
        Assert.assertTrue(stack.pop() == 4);
        Assert.assertTrue(stack.pop() == 3);
        Assert.assertTrue(stack.pop() == 1);
    }
}
