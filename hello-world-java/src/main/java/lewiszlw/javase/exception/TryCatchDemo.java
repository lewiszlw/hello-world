package lewiszlw.javase.exception;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-15
 */
public class TryCatchDemo {

    public static void main(String[] args) {
        m1();
        System.out.println(m2());
        System.out.println(m3());
    }

    private static void m1() {
        try {
            int a = 1 / 0;
        } catch (ArithmeticException e) {
            // 这里catch住
            System.out.println("ArithmeticException");
            throw e;
        } catch (Exception e) {
            // 这里不会再catch住
            System.out.println("Exception");
        }
    }

    private static int m2() {
        int x = 1;
        try {
            x ++;
            return x;
        } finally {
            // 返回值在执行finally前已保存，所以这里++x没用
            ++x;
        }
    }

    private static int m3() {
        int x = 1;
        try {
            x ++;
            // 退出虚拟机，finally内代码不会执行
            System.exit(0);
            return x;
        } finally {
            System.out.println("finally");
        }
    }
}
