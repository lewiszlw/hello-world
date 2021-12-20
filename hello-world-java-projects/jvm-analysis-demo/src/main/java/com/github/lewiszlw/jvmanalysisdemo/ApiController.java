package com.github.lewiszlw.jvmanalysisdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class ApiController {

    public static final int _1MB = 1024 * 1024;


    /**
     * 心跳检测
     */
    @GetMapping("/heartbeat")
    public String heartbeat() {
        return "alive";
    }


    /**
     * 制造堆内存OOM
     */
    @GetMapping("/heapOOM")
    public void heapOOM() {
        for (;;) {
            personList.add(new Person(UUID.randomUUID().toString(), new byte[_1MB]));
        }
    }
    private static List<Person> personList = new ArrayList<>();
    static class Person {
        private String name;
        private byte[] data;
        public Person(String name, byte[] data) { this.name = name; this.data = data; }
    }


    /**
     * 制造堆外内存OOM
     */
    @GetMapping("/directMemoryOOM")
    public void directMemoryOOM() {
        List<ByteBuffer> list = new ArrayList<>();
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1MB);
            list.add(byteBuffer);
        }
    }


    /**
     * 制造栈溢出
     */
    @GetMapping("/stackOverflow")
    public void stackOverflow() {
        recursiveMethod();
    }
    private static void recursiveMethod() {
        recursiveMethod();
    }


    /**
     * 制造元空间OOM
     */
    @GetMapping("/metaspaceOOM")
    public void metaspaceOOM() {
        List<ClassLoader> classLoaders = new ArrayList<>();
        while (true) {
            ClassLoader classLoader = new URLClassLoader(new URL[]{});
            Facade facade = (Facade) Proxy.newProxyInstance(classLoader, new Class<?>[]{Facade.class}, new MetaspaceFacadeInvocationHandler(new FacadeImpl()));
            classLoaders.add(classLoader);
        }
    }
    public interface Facade {}
    public static class FacadeImpl implements Facade {}
    public static class MetaspaceFacadeInvocationHandler implements InvocationHandler {
        private Object impl;
        public MetaspaceFacadeInvocationHandler(Object impl) { this.impl = impl; }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { return method.invoke(impl, args); }
    }

}
