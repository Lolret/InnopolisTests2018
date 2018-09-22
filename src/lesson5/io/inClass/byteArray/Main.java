package lesson5.io.inClass.byteArray;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

public class Main {
    String text = "Hello world!";
    byte[] buffer = text.getBytes();
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);

//    try (BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream)) {
//        int c = 0;
//        while ((c = bufferedInputStream.read()) != -1) {
//            System.serverSocketWriter.print((char) c);
//        }
//    } catch
}
