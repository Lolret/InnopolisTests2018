package lesson7.reflection.home;

import lesson7.reflection.inClass.proxy.JavaTrainer;

public class Main
{
    public static void main(String[] args) throws IllegalAccessException {
        XMLUtil xmlUtil = new XMLUtil();
        String XML = xmlUtil.createXML("a", new JavaTrainer("Misha"));

        System.out.println(XML);

    }
}
