import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var line = "-----------------------------------------------------------------";

        // print welcome text
        System.out.println(line);
        System.out.println("Roman to Arabic / Arabic to Roman number converter");
        System.out.println(line);
        System.out.println("Input numbers between 1 and 1000 (Roman or Arabic format)");

        // ask user input
        var sc = new Scanner(System.in);

        while (true) {
            System.out.print("> ");

            // retrieve user input
            var input = sc.nextLine().trim().toUpperCase();

            if (input.equals("0")) {
                System.out.println(line);
                return;
            }

            // check if user wants to do roman to arabic or inverse
            if (getConversionMode(input).equals("arabicToRoman")) {
                var validatedInput = ArabicToRoman.validate(input);

                if (! validatedInput.equals("failed")) {
                    System.out.println(ArabicToRoman.convert(validatedInput));
                } else {
                    System.out.println("Invalid input");
                }
            } else {
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

    private static String getConversionMode(String input) {
        // if input is a valid and positive Integer
        if (isValidPositiveInt(input)) {
            return "arabicToRoman";
        }

        return "romanToArabic";
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
