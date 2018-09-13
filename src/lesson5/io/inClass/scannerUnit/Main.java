package lesson5.io.inClass.scannerUnit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("D://note.exe");
        List<String> stringList = new ArrayList<>();
        try ( Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                stringList.add(line);
                System.out.print(line);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}