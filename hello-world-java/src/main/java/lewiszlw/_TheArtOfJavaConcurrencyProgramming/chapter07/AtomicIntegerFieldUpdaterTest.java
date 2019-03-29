package lewiszlw._TheArtOfJavaConcurrencyProgramming.chapter07;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/10/31
 * Time:16:42
 */
public class AtomicIntegerFieldUpdaterTest {

    public static AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater
            .newUpdater(User.class, "old");

    public static void main(String[] args) {
        User user = new User("lewis", 22);
        a.incrementAndGet(user);
        System.out.println(a.get(user));
    }

    @Data
    @AllArgsConstructor
    public static class User {
        private String name;
        /**
         * 不能为private，否则IllegalAccessException
         * 必须加volatile，否则ExceptionInInitializerError
         */
        public volatile int old;
    }

}
