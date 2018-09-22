package lesson8.classLoaders.inClass.proxyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Human humanProxy = (Human) Proxy.newProxyInstance(EuropeanHuman.class.getClassLoader(),
                new Class[]{Human.class}, new HumanInvocation());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            humanProxy.eat("bread", 2);
            System.out.println(humanProxy.say());
            humanProxy.sleep(6);
            scanner.nextLine();
        }





        /*Human human = new EuropeanHuman();
        System.serverSocketWriter.println(human.say());
        human.eat("banana", 5);
        ClassLoader parentLoader = EuropeanHuman.class.getClassLoader();
        HumanLoader humanLoader = new HumanLoader(parentLoader);
        Class humanClass = humanLoader.loadClass("ru.innopolis.stx13._7_classLoader.magic.proxy.EuropeanHuman");
        System.serverSocketWriter.println(humanClass.getMethod("say").invoke(humanClass.newInstance(), null));
        */
    }
}