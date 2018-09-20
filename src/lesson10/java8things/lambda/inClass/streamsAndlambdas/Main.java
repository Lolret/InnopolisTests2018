package lesson10.java8things.lambda.inClass.streamsAndlambdas;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Integer> preparedArrayList = Arrays.asList(1, 2, 3, 4,5,6,7,8,9);

        BinaryOperator<Integer> sum = (a, b) -> a + b;

        Integer summary = sum.apply(10, 20);
        System.out.println("Sum of 10 and 20 === " +summary);

        Integer max = max(preparedArrayList);
        System.out.println("Max of list" + preparedArrayList + " is: " +max);


        Double average = average(preparedArrayList);
        System.out.println("Average of list" + preparedArrayList + " is: " +average);

        System.out.println("factorial of 10 is: " + factorial(10));
    }

    private static Integer max(List<Integer> integerList) {
        return integerList.stream().max(Comparator.naturalOrder()).get();
    }

    private static Double average(List<Integer> integerList) {
        return integerList.stream().collect(Collectors.averagingDouble(p -> p));
    }

    private static Integer factorial(Integer a) {
        return Stream.iterate(1, n -> n + 1).limit(a).reduce((s1, s2) -> s1 * s2).orElse(0);
    }
}