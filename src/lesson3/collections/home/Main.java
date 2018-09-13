package lesson3.collections.home;

import lesson5.io.inClass.objectInput.Line;
import lesson5.io.inClass.objectInput.Point;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Integer[] setArray = {2, 5, 7, 8, 3, 0};

        MathBox mb = new MathBox(setArray);
        System.out.println(mb);
        System.out.println("Summ = " +mb.summator());
        System.out.println(mb.splitter(2));
        System.out.println(mb);
        mb.delete(8);
        System.out.println(mb.getSortedSet());
        System.out.println("Summ = " + mb.summator());

        serializeMathBox(mb);


        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d://file"))) {
            MathBox mb1 = (MathBox) objectInputStream.readObject();
            System.out.println(mb1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    static void serializeMathBox(MathBox mb) {
        String filename = "d://file";

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(mb);
            objectOutputStream.reset();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}