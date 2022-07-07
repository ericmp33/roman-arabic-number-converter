import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var line = "-------------------------------------------------------------";

        System.out.println(line);
        System.out.println("Roman to Arabic / Arabic to Roman number converter");
        System.out.println(line);
        System.out.println("Input numbers between 1 and 1000 (Roman or Arabic format)");

        var sc = new Scanner(System.in);

        while (true) {
            System.out.print("> ");

            // retrieve user input
            var input = sc.nextLine().trim().toUpperCase();

            if (input.equalsIgnoreCase("0") || input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("-1")) {
                System.out.println(line);
                return;
            }

            // if user inputted a valid positive int
            if (isValidPositiveInt(input)) {
                // convert from Arabic to Roman
                var validatedInput = ArabicToRoman.validate(input);

                if (! validatedInput.equals("failed")) {
                    System.out.println(ArabicToRoman.convert(validatedInput));
                } else {
                    System.out.println("Invalid input");
                }
            } else {
                // convert from Roman to Arabic
                var validatedInput = RomanToArabic.validate(input);

                if (! validatedInput.equals("failed")) {
                    System.out.println(RomanToArabic.convert(validatedInput));
                } else {
                    System.out.println("Invalid input");
                }
            }

            System.out.println();
        }
    }

    // returns true if parsed var is an integer and positive
    private static boolean isValidPositiveInt(String s) {
        try {
            return Integer.parseInt(s) > 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
