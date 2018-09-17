package lesson7.reflection.inClass.proxy.kata;

@Logged
public class MyCounter implements Counter {

        @Override
        public void count() {
            System.out.println("1-2-3-4");
        }
}
