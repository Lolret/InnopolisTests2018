package lesson7.reflection.inClass.proxy.kata;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CounterIH implements InvocationHandler {

    Counter counter;

    public CounterIH(Counter counter) {
        this.counter = counter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args /*аргументы переданные методу*/) throws Throwable {
        Class clazz = counter.getClass();
        if (clazz.getMethod(method.getName()).getAnnotation(Logged.class)
                /*getAnnotation(Logged.class)*/ != null) {
            long start = System.currentTimeMillis();
            System.out.println("Method " + method.getName() + " called");
            Object result = method.invoke(counter, args);
            System.out.println("Operating time " + (System.currentTimeMillis() - start));
            return result;
        } else return method.invoke(counter, args);
    }
}