package reflection.inClass.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Trainer trainer = new JavaTrainer();

        InvocationHandler center = new TrainingCenter(trainer);

        Trainer stc = (Trainer) Proxy.newProxyInstance(TrainingCenter.class.getClassLoader(),
                new Class[]{Trainer.class}, center);

        System.out.println("Without proxy:");

        trainer.sleep();
        trainer.eat();
        trainer.teach();

        System.out.println("With proxy:");

        stc.sleep();
        stc.eat();
        stc.teach();
    }
}