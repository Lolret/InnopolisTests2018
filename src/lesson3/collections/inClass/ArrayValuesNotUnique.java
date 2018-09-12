package lesson3.collections.inClass;

public class ArrayValuesNotUnique extends Exception {

    ArrayValuesNotUnique() {
    }

    ArrayValuesNotUnique(String msg) {
        super("values of array is not unique");
    }
}
