package lewiszlw.problems.producerconsumer;

import java.util.Queue;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-14
 */

/**
 * 服务员线程
 */
public class Waiter extends Thread {

    /**
     * 放菜窗口
     */
    private Queue<Food> queue;

    /**
     * 服务员名称
     */
    private String name;

    public Waiter(Queue<Food> queue, String name) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (Utils.isEmpty(queue)) {
                    try {
                        // 如果放菜窗口为空，则该服务员等待
                        System.out.println("放菜窗口已空, " + getName() + "等待厨师做菜");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 端一道菜
                Food food = queue.remove();
                System.out.println(getName() + "端走了一份菜: " + food.toString());

                // 通知厨师放菜
                queue.notifyAll();
            }

            // 模拟服务员端菜时间
            Utils.randomSleep();
        }
    }
}
