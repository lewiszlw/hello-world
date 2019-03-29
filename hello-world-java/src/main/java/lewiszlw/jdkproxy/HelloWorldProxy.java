package lewiszlw.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/25
 * Time:16:22
 */
public class HelloWorldProxy implements InvocationHandler {

    private Object target;

    public HelloWorldProxy(Object target) {
        this.target = target;
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method: " + method.getName() + " is invoked.");
        return method.invoke(target, args);
    }
}
