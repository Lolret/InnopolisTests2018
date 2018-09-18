package lesson7.reflection.inClass.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static void printClass(Class clazz) {
        System.out.println("FIELDS:");
        printFields(clazz);
        System.out.println();
        System.out.println("Declared fields: ".toUpperCase());
        printDeclaredFields(clazz);
        System.out.println("METHODS");
        printMethods(clazz);
        Class parentClass = clazz.getSuperclass();
        if(parentClass != null) {
            printClass(parentClass);
        }
    }

    private static void printFields(Class clazz) {
        fieldIterator(clazz.getFields());
    }

    private static void printDeclaredFields(Class clazz) {
        fieldIterator(clazz.getDeclaredFields());
    }

    private static void fieldIterator(Field[] fields) {
        for (Field field: fields) {
            System.out.println("name = " + field.getName());
            System.out.println("type = " + field.getType());
            System.out.println("modifier = " + field.getModifiers());
        }
    }

    private static void printMethods(Class clazz) {
        methodIterator(clazz.getMethods());
    }

    private static void methodIterator(Method[] methods) {
        for (Method method: methods) {
            System.out.println("Method name: " + method.getName());
            System.out.println("Return type: " + method.getReturnType().getName());
            System.out.println("Parameters");
            for (Class parameterClass : method.getParameterTypes()) {
                System.out.println(parameterClass.getName());
            }
        }
    }
}