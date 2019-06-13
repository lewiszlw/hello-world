package lewiszlw.problems.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-14
 */
public class Restaurant {

    public static void main(String[] args) {
        // 放菜窗口
        Queue<Food> queue = new LinkedList<>();

        Cook cook1 = new Cook(queue, "厨师王师傅");
        Cook cook2 = new Cook(queue, "厨师李师傅");
        Cook cook3 = new Cook(queue, "厨师杨师傅");

        Waiter waiter1 = new Waiter(queue, "服务员小张");
        Waiter waiter2 = new Waiter(queue, "服务员小美");
        Waiter waiter3 = new Waiter(queue, "服务员小丽");

        // 饭馆开张
        cook1.start();
        cook2.start();
        cook3.start();
        waiter1.start();
        waiter2.start();
        waiter3.start();
    }
}
