package classLoaders.inClass.proxyClassLoader;

public class EuropeanHuman implements Human {

    @Override
    public void eat(String name, int count) {
        System.out.println("I  have'n eaten " + count + " " + name);
    }

    @Override
    public void sleep(int time) {
        System.out.println("I wont sleep for "+ time);
    }

    @Override
    public String say() {
        return "hello";
    }
}