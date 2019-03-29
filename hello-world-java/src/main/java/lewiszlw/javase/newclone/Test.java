package lewiszlw.javase.newclone;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/21
 * Time:14:43
 */
public class Test {

    public static int COUNT = 10000000;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for(int i = 0; i < COUNT; i++) {
            SimpleObject temp = new SimpleObject(1, "simpleObject");
        }
        System.out.println("new SimpleObject: " + (System.currentTimeMillis() - start));

        SimpleObject simpleObject = new SimpleObject(1, "simpleObject");
        start = System.currentTimeMillis();
        for(int i = 0; i < COUNT; i++) {
            SimpleObject temp = simpleObject.clone();
        }
        System.out.println("clone SimpleObject: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for(int i = 0; i < COUNT; i++) {
            ComplexObject temp = new ComplexObject(1, "complexObject");
        }
        System.out.println("new ComplexObject: " + (System.currentTimeMillis() - start));

        ComplexObject complexObject = new ComplexObject(1, "complexObject");
        start = System.currentTimeMillis();
        for(int i = 0; i < COUNT; i++) {
            ComplexObject temp = complexObject.clone();
        }
        System.out.println("clone ComplexObject: " + (System.currentTimeMillis() - start));
    }
}
