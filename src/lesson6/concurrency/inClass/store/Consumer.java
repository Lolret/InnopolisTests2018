package lesson6.concurrency.inClass.store;

public class Consumer implements  Runnable{

    public Consumer(Store store) {
        this.store = store;
    }

    private Store store;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            store.get();
        }
    }
}
