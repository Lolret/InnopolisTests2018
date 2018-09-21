package lesson8.classLoaders.home;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        String pathFile = System. getProperty("user.dir");

        ShtainClassMaker.doAll(pathFile);
    }
}