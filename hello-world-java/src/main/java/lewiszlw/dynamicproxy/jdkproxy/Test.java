package lewiszlw.dynamicproxy.jdkproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/25
 * Time:16:26
 */
public class Test {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        // 第一种写法
        Class<?> proxyClass = Proxy.getProxyClass(Test.class.getClassLoader(), IHelloWorld.class);
        final Constructor<?> cons = proxyClass.getConstructor(InvocationHandler.class);
        final InvocationHandler ih = new HelloWorldProxy(new HelloWorldImpl());
        IHelloWorld helloWorld = (IHelloWorld) cons.newInstance(ih);
        helloWorld.sayHello();

        // 第二种写法，内部实现跟第一种写法一样
        IHelloWorld helloWorld1 = (IHelloWorld) Proxy.newProxyInstance(Test.class.getClassLoader(),
                new Class<?>[]{IHelloWorld.class},
                new HelloWorldProxy(new HelloWorldImpl()));
        helloWorld1.sayHello();

    }
}
