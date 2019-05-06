package lewiszlw.java8.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-06
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("completableFuture1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
