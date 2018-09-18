package lesson7.reflection.home;

import lesson7.reflection.inClass.proxy.JavaTrainer;
import lesson7.reflection.inClass.proxy.Trainer;

import java.util.Objects;

public class Iam {

    public int age = 29;
    public JavaTrainer innerTrainer = new JavaTrainer("Me");

    public Iam() {
    }

    public Iam(int age) {

        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Iam)) return false;
        Iam iam = (Iam) o;
        return age == iam.age &&
                Objects.equals(innerTrainer, iam.innerTrainer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(age, innerTrainer);
    }

    @Override
    public String toString() {
        return "Iam{" +
                "age=" + age +
                ", innerTrainer=" + innerTrainer +
                '}';
    }
}
