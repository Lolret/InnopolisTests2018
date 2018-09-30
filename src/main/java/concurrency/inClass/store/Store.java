package concurrency.inClass.store;

public class Store {
    private int products = 0;

    public Store(int products) {
        this.products = products;
    }

    public synchronized void get() {
        while (products < 1) {
            System.out.println("waiting to put");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products--;
        System.out.println("Купили 1 продукт");
        notify();
    }

    public synchronized void put() {
        while (products >=5) {
            System.out.println("wait for get");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products++;
        System.out.println("Произведен 1 товар");
        System.out.println("товаров на складе: " + products);
        notify();
    }
}