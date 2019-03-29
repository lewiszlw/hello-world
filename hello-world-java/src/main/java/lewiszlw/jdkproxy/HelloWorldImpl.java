package lewiszlw.jdkproxy;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/25
 * Time:16:21
 */
public class HelloWorldImpl implements IHelloWorld {
    @Override public void sayHello() {
        System.out.println("hello world");
    }
}
