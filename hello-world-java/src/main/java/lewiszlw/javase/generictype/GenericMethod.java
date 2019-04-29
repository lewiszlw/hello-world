package lewiszlw.javase.generictype;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019/1/18
 */
public class GenericMethod {

    public static <T> void method1(T t) {
        System.out.println(t.getClass().getName());
    }

    public <K, V> void method2(K k, V v) {
        System.out.println(k.getClass().getName());
        System.out.println(v.getClass().getName());
    }
}
