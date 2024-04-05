import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String errorMessage = "Invalid input entered. Terminating...";
        System.out.println("List of operations: add subtract multiply divide alphabetize\nEnter an operation:");
        String operation = input.next().toLowerCase();

        if (operation.equals("add") || operation.equals("subtract")) {
            // Get input as str and check if it contains two ints
            System.out.println("Enter two integers:");
            String firstInt = input.next();
            String secondInt = input.next();
            try {
                Integer.parseInt(firstInt);
                Integer.parseInt(secondInt);
                switch (operation) {
                    case "add":
                        int addingResult = Integer.valueOf(firstInt) + Integer.valueOf(secondInt);
                        System.out.println("Answer: " + addingResult);
                        break;
                    case "subtract":
                        int subtractResult = Integer.valueOf(firstInt) - Integer.valueOf(secondInt);
                        System.out.println("Answer: " + subtractResult);
                        break;
                }
            }
            catch (NumberFormatException e) {
                // Error case
                System.out.println(errorMessage);
            }
        }
        else if (operation.equals("multiply") || operation.equals("divide")) {
            // Get input as str and check if it contains two doubles
            System.out.println("Enter two doubles:");
            String firstDouble = input.next();
            String secondDouble = input.next();
            try {
                Double.parseDouble(firstDouble);
                Double.parseDouble(secondDouble);
                switch (operation) {
                    case "multiply":
                        double multiplyResult = Double.valueOf(firstDouble) * Double.valueOf(secondDouble);
                        System.out.printf("Answer: %.2f\n", multiplyResult);
                        break;
                    case "divide":
                    if (secondDouble.equals("0")) {
                        System.out.println(errorMessage);
                    }
                    else {
                        double divisionResult = Double.valueOf(firstDouble) / Double.valueOf(secondDouble);
                        System.out.printf("Answer: %.2f\n", divisionResult);
                        break;
                    }
                }
            }
            catch (NumberFormatException e) {
                // Error case
                System.out.println(errorMessage);
            }
        }
        else if (operation.equals("alphabetize")) {
            System.out.println("Enter two words:");
            String firstWord = input.next();
                String secondWord = input.next();
                int comparison = firstWord.toLowerCase().compareTo(secondWord.toLowerCase());
                if (comparison == 0) {
                    System.out.println("Answer: Chicken or Egg.");
                }
                else {
                    if (comparison > 0) {
                        String middleWord = firstWord;
                        firstWord = secondWord;
                        secondWord = middleWord;
                    }
                    System.out.printf("Answer: %s comes before %s alphabetically.\n", firstWord, secondWord);
                }
        }
        else {
            System.out.println(errorMessage);
        }
        input.close();
    }
}
