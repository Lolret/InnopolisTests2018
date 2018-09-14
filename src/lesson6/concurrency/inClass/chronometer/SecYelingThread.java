package lesson6.concurrency.inClass.chronometer;

public class SecYelingThread implements Runnable {

    private final Chronomer chronomer;
    private int sec;

    SecYelingThread(Chronomer chronomer, int sec) {
        this.chronomer = chronomer;
        this.sec = sec;
    }

    @Override
    public void run() {
        while (true) {
            if (chronomer.getTime() % sec == 0
                    && chronomer.getTime() != 0) {
                System.out.println(sec + " sec!");
            }
            synchronized(chronomer) {
                try {
                    chronomer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}