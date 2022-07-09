import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var line = "-------------------------------------------------------------";

        System.out.println(line);
        System.out.println("Roman to Arabic / Arabic to Roman number converter");
        System.out.println(line);
        System.out.println("Input numbers between 1 and 1000 (Roman or Arabic format)");
        System.out.println("Type \"-1\" to exit, \"test\" to test all 1000 numbers");

        var sc = new Scanner(System.in);

        while (true) {
            System.out.print("> ");

            // retrieve user input
            var input = sc.nextLine().trim().toUpperCase();

            // exit condition
            if (input.equalsIgnoreCase("-1")) {
                System.out.println("bye");
                return;
            }

            // if user wants to test all 1000 numbers
            if (input.equalsIgnoreCase("test")) {
                testAllNumbersAreConvertedSuccessfully();
            }

            // if user inputted a valid positive int
            else if (isValidPositiveInt(input)) {
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

    // returns true if parsed String is a valid positive integer number
    private static boolean isValidPositiveInt(String s) {
        try {
            return Integer.parseInt(s) > 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    // checks if all numbers (from 1 to 1000) are converted successfully, from arabic to roman, and then from roman to arabic
    public static void testAllNumbersAreConvertedSuccessfully() {
        // convert all the numbers (from 1 to 1000), and check conversions
        var error = false;

        for (int i = 1; i < 1001; i++) {
            var convertedRomanNumber = ArabicToRoman.convert(String.valueOf(i));
            var convertedArabicNumber = RomanToArabic.convert(convertedRomanNumber);

            System.out.println("> " + i);
            System.out.println("> " + convertedRomanNumber);
            System.out.println("> " + convertedArabicNumber);
            System.out.println("-----");

            if (i != Integer.parseInt(convertedArabicNumber)) {
                error = true;
                System.out.println("\nThe converter failed converting the number " + i + ".");
                break;
            }
        }

        if (! error) {
            System.out.println("\nThe converter converted numbers from 1 to 1000 successfully.");
        }
    }
}
