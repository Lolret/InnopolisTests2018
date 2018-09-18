package lesson8.classLoaders.home;

import java.io.File;

public class Main {
    public static void main(String[] args) {



        String pathFile = System. getProperty("user.dir");

        File root = new File("/java");


        ShtainClassMaker.doAll(pathFile);

//        System.out.println(root.getPath());
    }
}
