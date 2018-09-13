package lesson5.io.inClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

//    private static String text = "File not found!!!!1";
//    private static String text1 = "Hello Innopolis!";
    private static String filename = "D://note.exe";
    private static String filename2 = "D://note1.exe";

    public static void main(String[] args) {
//        writefile(filename, text1);
//        readFile(filename);
//        writefile(filename, text);
//        readFile(filename);
        copyFile(filename, filename2);
    }

    private static void copyFile(String copyedFile, String destFile) {
        writefile(filename2, readFile(filename));
    }

    private static void writefile(String filename, String text) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)){

            byte[] buffer = text.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String filename) {

        StringBuffer stringBuffer = new StringBuffer();

        try (FileInputStream fileInputStream  = new FileInputStream(filename))
        {
            System.out.println("Размер файла" + fileInputStream.available());
            int i = 0;
            while ((i = fileInputStream.read()) != -1) {
                System.out.print((char) i );
                stringBuffer.append((char)i);
            }
            System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  stringBuffer.toString();
    }
}
