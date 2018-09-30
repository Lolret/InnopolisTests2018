package lambda.inClass.after;

import lambda.inClass.nonFuncTaxCalc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your salary");
        Integer salary = scanner.nextInt();
        System.out.println("Enter your country");
        String country = scanner.nextLine();
        nonFuncTaxCalc taxCalc = null;
        switch (country) {
            case "Russia":
                taxCalc = (sum) -> (sum * 1.13);
                break;
            case "USA":
        }
    }
}
