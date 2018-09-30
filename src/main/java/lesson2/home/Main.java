package lesson2.home;

import java.util.Random;


public class Main {
    public static void main(String[] args) {

        Integer ARRAY_LENGTH = 20;
        Integer[] array = new Integer[ARRAY_LENGTH];

        Random random = new Random();
        for (int i = 0; i < ARRAY_LENGTH; i++) array[i] = random.nextInt(100);

        QuickSortUnit.printArray(array);
        QuickSortUnit.quickSort(array);
        QuickSortUnit.printArray(array);
    }
}
