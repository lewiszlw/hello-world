package lewiszlw._TheArtOfJavaConcurrencyProgramming.chapter04;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/13
 * Time:19:56
 */
public class DaemonThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                SleepUtils.second(10);
                System.out.println("daemon");
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
