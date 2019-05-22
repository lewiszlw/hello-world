package lewiszlw.javase.exception;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-15
 */
public class TryCatchDemo {

    public static void main(String[] args) {
        try {
            int a = 1 / 0;
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException");
            throw e;
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
