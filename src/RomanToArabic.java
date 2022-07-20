import java.util.ArrayList;
import java.util.Map;

/**
 * Handles conversions of Roman numbers to Arabic numbers.
 */
public class RomanToArabic {
    /**
     * Table of equivalences between Roman numbers and Arabic numbers
     */
    public static final Map<Character, Integer> dictionary = Map.of(
        'I', 1,
        'V', 5,
        'X', 10,
        'L', 50,
        'C', 100,
        'D', 500,
        'M', 1000
    );

    private RomanToArabic() {}

    /**
     * Validates if parsed input is a valid Roman number to proceed with the conversion to Arabic number.
     *
     * @param input String to be checked
     * @return "failed" if validation fails, otherwise, the validated input
     */
    public static String validate(String input) {
        if (! isComposedByRomanNumbers(input)) {
            return "failed";
        }

        return input;
    }

    // returns true if parsed String is only composed by roman numbers

    /**
     * Helper method to do the validation.
     * Checks if parsed String is only composed by Roman numbers.
     *
     * @param str String to be checked
     * @return True if parsed String is only composed by Roman numbers, otherwise, false
     */
    private static boolean isComposedByRomanNumbers(String str) {
        if (str.isEmpty()) return false;

        for (int i = 0; i < str.length(); i++) {
            if (! dictionary.containsKey(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Main's class method, which converts parsed String (should be a Roman number) to Arabic number.
     *
     * @param input Roman number to be converted to Arabic number
     * @return The converted Roman number as Arabic number, or "Invalid input"
     */
    public static String convert(String input) {
        // convert each Roman number to integer and store it in an array (but reversed)
        ArrayList<Integer> integers = new ArrayList<>();

        // todo - explain the conversion!

        for (int i = input.length() - 1; i >= 0; i--) {
            integers.add(dictionary.get(input.charAt(i)));
        }

        // build the output
        int output = integers.get(0);

        for (int i = 0; i < integers.size() - 1; i++) {
            int current = integers.get(i);
            int next = integers.get(i + 1);

            // if current number is greater than next one, subtract, otherwise, add
            if (current > next) {
                output -= next;
            } else {
                output += next;
            }
        }

        // trick to do reverse validation
        if (! ArabicToRoman.convert(String.valueOf(output)).equals(input)) {
            return "Invalid input";
        }

        return String.valueOf(output);
    }
}
