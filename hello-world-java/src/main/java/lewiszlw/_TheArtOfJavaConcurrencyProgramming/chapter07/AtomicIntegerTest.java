package lewiszlw._TheArtOfJavaConcurrencyProgramming.chapter07;

import java.util.concurrent.atomic.*;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/10/31
 * Time:15:47
 */
public class AtomicIntegerTest {

    static AtomicInteger ai = new AtomicInteger();

    public static void main(String[] args) {
        ai.set(1);
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.incrementAndGet());
    }
}
