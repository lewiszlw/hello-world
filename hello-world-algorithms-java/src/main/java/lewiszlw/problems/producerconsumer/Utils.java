package lewiszlw.problems.producerconsumer;

import java.util.Queue;
import java.util.Random;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-14
 */
public class Utils {

    private static Random random = new Random();

    public static void randomSleep() {
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断放菜窗口是否已满
     */
    public static <E> boolean isFull(Queue<E> queue) {
        return queue.size() >= 4;
    }

    /**
     * 判断放菜窗口是否已空
     */
    public static <E> boolean isEmpty(Queue<E> queue) {
        return queue.isEmpty();
    }
}
