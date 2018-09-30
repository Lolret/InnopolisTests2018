package classLoaders.inClass.MagickThings;

import java.lang.reflect.InvocationTargetException;

public class Main    {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

//
//        System.serverSocketWriter.println(Magick.class.getName());


        ClassLoader parentClassLoader = Magick.class.getClassLoader();
        Class magicClass = parentClassLoader.loadClass("classLoaders.inClass.MagickThings.Magick");
//        Magick magick1 = (Magick) magicClass.newInstance();
//        magick1.cast();

        KindMagickClassLoader kindMagickClassLoader = new KindMagickClassLoader(parentClassLoader);
        Class kindMagicClass = kindMagickClassLoader.loadClass("lesson8.classLoaders.inClass.MagickThings.Magick");
        kindMagicClass.getMethod("cast").invoke(kindMagicClass.newInstance(), null);

        Magick magick2 = (Magick) kindMagicClass.newInstance();

    }
}
