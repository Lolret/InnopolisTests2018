package io.inClass.objects;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Tom", 23, 1.65, true);
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("data.bin"))){
            dataOutputStream.writeUTF(person.getName());
            dataOutputStream.writeInt(person.getAge());
            dataOutputStream.writeDouble(person.getHeight());
            dataOutputStream.writeBoolean(person.getMarried());
            System.out.println("Файл записан");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
