package concurrency.inClass.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(8);

        SlaveThread slaveThread = new SlaveThread();
        long start = System.currentTimeMillis();

        List<Future<Double>> futures = new ArrayList<>();

        for (int i = 0; i < 400; i++) {
            final int j = 0;
            futures.add(CompletableFuture.supplyAsync(slaveThread::count, service));
        }
        double value = 0;
        for (Future<Double> future : futures) {
            try {
                value += future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Exec time: " + (System.currentTimeMillis() - start));
    }
}