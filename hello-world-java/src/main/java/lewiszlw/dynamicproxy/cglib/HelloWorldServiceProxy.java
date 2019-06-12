package lewiszlw.dynamicproxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-12
 */
public class HelloWorldServiceProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before: " + method.getName());

        //CGLIB反射调用真实对象方法
        Object result = methodProxy.invokeSuper(o, objects);

        System.out.println("after: " + method.getName());

        return result;
    }
}
