package lewiszlw.javase.varargs;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: 可变参数
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/27
 * Time:17:34
 */
public class VarArgsDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(isAllIn(list, 1));
        System.out.println(isAllIn(list, 1, 2));
        System.out.println(isAllIn(list, 1, 2, 4));
    }

    private static boolean isAllIn(List<Integer> list, int... args) {
        for(int i: args) {
            if(!list.contains(i)) {
                return false;
            }
        }
        return true;
    }
}
