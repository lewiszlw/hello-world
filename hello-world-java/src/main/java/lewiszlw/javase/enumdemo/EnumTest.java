package lewiszlw.javase.enumdemo;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/7/18
 * Time:12:10
 */
public class EnumTest {
    public static void main(String[] args) {
        Color color = Color.RED;
        System.out.println(color.toString());
        System.out.println(color.getCode());
        System.out.println(color.getValue());
        System.out.println(color.getComments());
//        for (Color c: Color.values()) {
//            System.out.println(c.ordinal());
//            System.out.println(c.toString());
//        }
        System.out.println(Color.RED.compareTo(Color.BULE));
        System.out.println(Color.RED.equals(Color.BULE));
    }
}
