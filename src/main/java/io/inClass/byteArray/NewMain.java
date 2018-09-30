package io.inClass.byteArray;

import java.io.ByteArrayInputStream;

public class NewMain {
    public static void main(String[] args) {
        byte[] array1 = new byte[]{1,3,5,6};
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array1);

        int b;
        while ((b = byteArrayInputStream.read()) != -1) {
            System.out.print(b);
        }

        String text = "Hello Inno!";
        byte[] array2 = text.getBytes();
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(array2);
        b=0;
        while ((b=byteArrayInputStream2.read()) != -1) {
            System.out.println((char)b);
        }
    }
}
