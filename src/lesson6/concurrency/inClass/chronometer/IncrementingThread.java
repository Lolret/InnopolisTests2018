package lesson6.concurrency.inClass.chronometer;

public class IncrementingThread implements Runnable {

    Chronomer chronomer;

    public IncrementingThread(Chronomer chronomer) {
        this.chronomer = chronomer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (chronomer) {
                chronomer.incTime();
                System.out.println(chronomer.getTime());
                chronomer.notifyAll();
            }
        }
    }
}