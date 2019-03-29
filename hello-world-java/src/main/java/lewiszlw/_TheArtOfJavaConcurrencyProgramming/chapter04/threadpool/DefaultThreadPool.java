package lewiszlw._TheArtOfJavaConcurrencyProgramming.chapter04.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/10/30
 * Time:15:33
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{

    private static final int MAX_WORKER_NUMBERS = 10;

    private static final int DEFAULT_WORKER_NUMBERS = 5;

    private static final int MIN_WORKER_NUMBERS = 1;

    private final LinkedList<Job> jobs = new LinkedList<Job>();

    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    /**
     * 工作者线程的数量
     */
    private int workerNum = DEFAULT_WORKER_NUMBERS;

    /**
     * 线程编号
     */
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initializeWorkers(workerNum);
    }

    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                // notify()比notifyAll()开销小，notifyAll()会将等待队列中线程全部移到阻塞队列中
                jobs.notify();
            }
        }
    }

    @Override public void shutdown() {
        for (Worker worker: workers) {
            worker.shutdown();
        }
    }

    @Override public void addWorkers(int num) {
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override public void removeWorker(int num) {
        synchronized (jobs) {
            if (this.workerNum - num < MIN_WORKER_NUMBERS) {
                num = this.workerNum - MIN_WORKER_NUMBERS;
            }
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= num;
        }
    }

    @Override public int getJobSize() {
        return jobs.size();
    }


    class Worker implements Runnable {

        private volatile boolean running = true;

        @Override public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        // worker线程执行一个job
                        job.run();
                    } catch (Exception e) {

                    }
                }
            }

        }

        public void shutdown() {
            running = false;
        }
    }
}
