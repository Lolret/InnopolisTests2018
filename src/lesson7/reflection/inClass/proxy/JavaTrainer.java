package lesson7.reflection.inClass.proxy;

public class JavaTrainer implements  Trainer {

    public String name;

    public JavaTrainer() {
    }

    public JavaTrainer(String name) {

        this.name = name;
    }

    @Override
    public void teach() {
        System.out.println("Java is really compicated");
    }

    @Override
    public void eat() {
        System.out.println("I like to eat bananas");

    }

    @Override
    public void sleep() {
        System.out.println("zZZZZZz");
    }
}