package lesson7.reflection.inClass.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TrainingCenter implements InvocationHandler{

    private Trainer trainer;

    public TrainingCenter(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args /*аргументы переданные методу*/) throws Throwable {
        System.out.println("I take your money");
        System.out.println("I give money to trainer");
        System.out.println("Lets go work! We are the team!");

        if (method.getName().equals("teach")) System.out.println("who's late?");

        //метод вызваный у оригинального обьекта
        return method.invoke(trainer, args);
    }
}
