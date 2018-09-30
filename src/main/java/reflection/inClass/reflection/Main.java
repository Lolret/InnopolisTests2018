package reflection.inClass.reflection;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Capucin mrJenkins = new Capucin(
                10,
                100,
                3);

        Field field = mrJenkins.getClass().getDeclaredField("footsize");
        Field field2 = Capucin.class.getDeclaredField("footsize");

        field.setAccessible(true);
        field2.setAccessible(true);

        System.out.println(field.get(mrJenkins));
        field.set(mrJenkins, 354);
        System.out.println(field.get(mrJenkins));

        Field tailLengthFIeld = mrJenkins.getClass().getDeclaredField("talLength");
        tailLengthFIeld.setAccessible(true);
        System.out.println(tailLengthFIeld.get(mrJenkins));
        tailLengthFIeld.set(mrJenkins, 45);
        System.out.println(tailLengthFIeld.get(mrJenkins));
//        new ReflectionUtils();

        Capucin abou = new Capucin(50);
        Class<Capucin> clazz = (Class<Capucin>) abou.getClass();
        ReflectionUtils.printClass(clazz);
    }
}