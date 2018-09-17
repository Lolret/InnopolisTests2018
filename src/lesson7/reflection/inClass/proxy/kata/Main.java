package lesson7.reflection.inClass.proxy.kata;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Counter realCounter = new MyCounter();
        Counter counterProxy = (Counter) Proxy.newProxyInstance(
                CounterIH.class.getClassLoader(),
                new Class[]{Counter.class},
                new CounterIH(realCounter)
        );
        System.out.println("Real counter:");
        realCounter.count();
        System.out.println("Proxy counter: ");
        counterProxy.count();
    }
}