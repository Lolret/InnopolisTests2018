package lesson7.reflection.inClass.proxy;

import java.util.Objects;

public class JavaTrainer implements  Trainer {
    public String name;

    public int age = 25;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JavaTrainer)) return false;
        JavaTrainer that = (JavaTrainer) o;
        return age == that.age &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "JavaTrainer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}