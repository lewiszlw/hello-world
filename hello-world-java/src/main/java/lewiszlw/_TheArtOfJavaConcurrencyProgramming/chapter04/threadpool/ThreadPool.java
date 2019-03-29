package lewiszlw._TheArtOfJavaConcurrencyProgramming.chapter04.threadpool;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/10/30
 * Time:15:31
 */
public interface ThreadPool<Job extends  Runnable> {
    void execute(Job job);

    void shutdown();

    void addWorkers(int num);

    void removeWorker(int num);

    int getJobSize();
}
