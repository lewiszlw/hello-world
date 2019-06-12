package lewiszlw.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-12
 */
public class Test {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 继承被代理类
        enhancer.setSuperclass(HelloWorldService.class);
        // 设置回调，回调类需实现MethodInterceptor接口
        enhancer.setCallback(new HelloWorldServiceProxy());
        // 生成代理对象
        HelloWorldService helloWorldService = (HelloWorldService) enhancer.create();

        // 使用代理对象调用相关方法
        helloWorldService.sayHello("hello");
    }
}
