package lewiszlw.javase.jitproblem;

/**
 * 解释：
 * 加了volatile是肯定保证内存的可见性和禁止指令重排的，不加是不一定保证但不代表一定不可见。
 * 此问题跟JIT优化有关，如果关闭JIT则该问题不会出现，两个线程均可退出。
 *
 * 当没有A行，JIT直接把这个循环编译成了while(true)，对变量c在主线程的改变它是永远也看不到的，虽
 * 然这么做和正常执行代码的结果有出入，但是并不违反没有volatile下不保证可见性的内存模型。
 * JIT这么做每次循环都能减少一次读取变量的开销。因此，最终的结果是JIT简化了代码，提高了运行效率，即使它是一个死循环。
 *
 * 当有了A行，循环内部有一个volatile的变量，JIT编译器认为不能使用前面那种激进的优化策略，于是while中判断run的逻辑被保留了下来。
 *
 * 参考
 * https://www.zhihu.com/question/348513270
 * https://stackoverflow.com/questions/67233073/does-reading-a-volatile-variable-affects-the-value-of-other-no-volatile-variable
 */

public class Vol {

    boolean run = true;
    volatile int s = 1;

    public static void main(String[] args) throws InterruptedException {

        Vol v = new Vol();

        //thread 1
        new Thread(() ->{
            while (v.run) {

                int a = v.s;    // 记做A行。如果没有这行，线程1无法中止
            }
            System.out.println("thread1 ended");
        }).start();

        //thread 2
        new Thread(() ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            v.run = false;
            System.out.println("set run false");
        }).start();
    }
}
