package lewiszlw.javase.generictype;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019/1/18
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("============泛型通配符==========");
        System.out.println(GenericWildcard.queryStr(Integer.class));
        System.out.println(GenericWildcard.queryStr(AtomicInteger.class));
        // GenericWildcard.queryStr(String.class);  编译错误


        System.out.println("============泛型方法==========");
        GenericMethod.method1(1);
        new GenericMethod().method2("a", 2);

        System.out.println("============泛型类==========");
        // 参数化类型 的 类型参数 为 Long
        GenericClass<Long> longGenericClass = new GenericClass<>();
        longGenericClass.setT(2L);
        longGenericClass.printTClassName();

        // 参数化类型 的 类型参数 移除，参数化类型转变成原始类型
        GenericClass genericClass = new GenericClass();
        // genericClass.printTClassName();  运行错误，NPE
        genericClass.printSth();
    }
}
