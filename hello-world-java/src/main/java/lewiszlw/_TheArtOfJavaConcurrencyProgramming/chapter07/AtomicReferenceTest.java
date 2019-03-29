package lewiszlw._TheArtOfJavaConcurrencyProgramming.chapter07;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/10/31
 * Time:16:31
 */
public class AtomicReferenceTest {

    public static AtomicReference<User> atomicUserRef = new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User("lewis", 22);
        atomicUserRef.set(user);
        User updateUser = new User("tom", 15);
        atomicUserRef.compareAndSet(user, updateUser);
        System.out.println(atomicUserRef.get().name);
        System.out.println(atomicUserRef.get().old);
    }

    @Data
    @AllArgsConstructor
    static class User {
        private String name;
        private int old;
    }
}
