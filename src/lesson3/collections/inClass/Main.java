package lesson3.collections.inClass;

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
    }
}