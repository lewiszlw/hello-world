package lewiszlw.problems.producerconsumer;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-14
 */

import java.util.Queue;

/**
 * 厨师线程
 */
public class Cook extends Thread {

    /**
     * 放菜窗口
     */
    private Queue<Food> queue;

    /**
     * 厨师名称
     */
    private String name;

    public Cook(Queue<Food> queue, String name) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            // 模拟做菜
            Utils.randomSleep();
            Food food = new Food();

            synchronized (queue) {
                while (Utils.isFull(queue)) {
                    try {
                        // 如果放菜窗口已满，则该厨师等待
                        System.out.println("放菜窗口已满, " + getName() + "等待服务员将菜拿走");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 厨师放菜到窗口
                queue.add(food);
                System.out.println(getName() + "做完一道菜: " + food.toString() + " 已放到窗口");

                // 叫服务员来端菜
                queue.notifyAll();
            }
        }
    }
}
