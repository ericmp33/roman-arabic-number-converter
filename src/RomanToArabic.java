import java.util.ArrayList;
import java.util.Map;

public class RomanToArabic {
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

    public static String validate(String input) {
        if (! isComposedByRomanNumbers(input)) {
            return "failed";
        }

        return input;
    }

    public static String convert(String input) {
        // convert each Roman number to integer and store it in an array (but reversed)
        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = input.length() - 1; i >= 0; i--) {
            integers.add(dictionary.get(input.charAt(i)));
        }

        // build the output
        int output = integers.get(0);

        for (int i = 0; i < integers.size() - 1; i++) {
            int current = integers.get(i);
            int next = integers.get(i + 1);

            // if current number is greater than next one, subtract, otherwise, sum
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

    // returns true if parsed String is only composed by roman numbers
    private static boolean isComposedByRomanNumbers(String str) {
        if (str.isEmpty()) return false;

        for (int i = 0; i < str.length(); i++) {
            if (! dictionary.containsKey(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
