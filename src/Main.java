import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                -------------------------------------------------------------
                Roman to Arabic / Arabic to Roman number converter
                -------------------------------------------------------------
                Input numbers between 1 and 1000 (Roman or Arabic format)
                Type "-1" to exit, "test" to test all 1000 numbers
                """);

        var sc = new Scanner(System.in);

        while (true) {
            System.out.print("> ");

            // retrieve user input
            var input = sc.nextLine().trim().toUpperCase();

            // exit condition
            if (input.equalsIgnoreCase("-1")) return;

            // if user wants to test all 1000 numbers
            if (input.equalsIgnoreCase("test")) testAllNumbersAreConvertedSuccessfully();

            // if user inputted a valid positive int
            else if (isValidPositiveInt(input)) {
                // validate input and convert from Arabic to Roman
                var validatedInput = ArabicToRoman.validate(input);

                if (! validatedInput.equals("failed")) {
                    System.out.println(ArabicToRoman.convert(validatedInput));
                } else {
                    System.out.println("Invalid input");
                }
            } else {
                // validate input and convert from Roman to Arabic
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

    /**
     * Checks if parsed String can be converted to a positive integer number.
     *
     * @param s Input String to be checked
     * @return true if parsed String is a positive integer, false otherwise
     */
    private static boolean isValidPositiveInt(String s) {
        try {
            return Integer.parseInt(s) > 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Checks if all numbers (from 1 to 1000) are converted from Arabic to Roman and from Roman to Arabic successfully.
     */
    public static void testAllNumbersAreConvertedSuccessfully() {
        var error = false;

        // convert all numbers
        for (int i = 1; i < 1001; i++) {
            var convertedRomanNumber = ArabicToRoman.convert(String.valueOf(i));
            var convertedArabicNumber = RomanToArabic.convert(convertedRomanNumber);

            System.out.println("> " + i);
            System.out.println("> " + convertedRomanNumber);
            System.out.println("> " + convertedArabicNumber);
            System.out.println("-----");

            // check conversion
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
