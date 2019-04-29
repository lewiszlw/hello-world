package lewiszlw.javase.generictype;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: 泛型继承
 *
 * @author zhanglinwei02
 * @date 2019/1/23
 */
public class GenericInheritance {

    public static void main(String[] args) {
        List<Number> list1 = new ArrayList<Number>();
        // ArrayList<Number> list2 = new ArrayList<Integer>();   编译错误
        List<? extends Number> list3 = new ArrayList<Integer>();
        // List<? super Number> list4 = new ArrayList<Integer>();  编译错误
        List<? super Number> list5 = new ArrayList<Object>();
        List<? super Integer> list6 = list5;
    }
}
