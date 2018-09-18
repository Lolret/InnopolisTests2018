package lesson8.classLoaders.home.Frankenshtain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ShtainMethodMaker implements InvocationHandler {

    Shtain shtain;

    static String methodCode;

    public static void makeMethod() {
        collectCode();
    }


    private static void collectCode() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            sb.append(s);
            if (s.equals("")) break;
        }
        methodCode = sb.toString();

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("doWork")) System.out.println("who's late?");

        //метод вызваный у оригинального обьекта
        return method.invoke(shtain);

    }
}
