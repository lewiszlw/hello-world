package lewiszlw.doublecompare;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/26
 * Time:11:02
 */
public class DoubleCompare {
    public static void main(String[] args) {
        double a = 1.2;
        double b = 1.2;
        double c = 1.23;
        System.out.println(Double.compare(a, b));
        System.out.println(Double.compare(a, c));
    }
}
