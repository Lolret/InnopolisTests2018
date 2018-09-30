package io.inClass.objectInput;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(3, 3);
        Line line1 = new Line(p1, p2);
        Line line2 = new Line(p2, p3);
        String filename = "d://file";


        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(line1);
            objectOutputStream.writeObject(line2);
            line2.setP1(new Point(7, 8));
            objectOutputStream.reset();
            objectOutputStream.writeObject(line2);


        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Line> lines = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            for (int i = 0; i<3; i++) lines.add((Line) objectInputStream.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(lines);

    }
}
