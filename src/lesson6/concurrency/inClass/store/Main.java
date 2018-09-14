package lesson6.concurrency.inClass.store;

public class Main {
    public static void main(String[] args) {
        Store store = new Store(4);

        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();

    }

}
