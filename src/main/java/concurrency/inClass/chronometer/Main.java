package concurrency.inClass.chronometer;

public class Main {
    public static void main(String[] args) {
        Chronomer chronomer = new Chronomer();
        Thread incThread =new Thread(new IncrementingThread(chronomer), "incThread");
        Thread thread3 =new Thread(new SecYelingThread(chronomer, 3), "thread3");
        Thread thread5 =new Thread(new SecYelingThread(chronomer, 5), "thread5");

      incThread.start();
        thread3.start();
        thread5.start();
    }
}