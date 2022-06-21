import java.util.ArrayList;
import java.util.Map;

/**
 * algorithm:
 *
 * i first started from right to left, just adding the numbers and checking if next number was negative
 *
 * actually was the other way:
 *
 * steps:
 *
 * 1- convert character to integer
 * 2- store integers in array
 * 3- reverse the array
 * 4- sum if current number is greater than next one, otherwise, subtract
 *
 * example:
 *
 * MMCXLII
 *
 * 1 + (1 (current number) is greater than 1 (next number)? false => sum)
 * 1 + (1 (current number) is greater than 50 (next number)? false => sum)
 * 50 + (50 (current number) is equal or greater than 10 (next number)? false => subtract)
 * 10 - (10 (current number) is equal or greater than 100 (next number)? false => subtract)
 * 100 +
 * 1000 +
 * 1000
 *
 * without comments:
 * MMCXLII
 *
 * 1 +
 * 1 +
 * 50 +
 * 10 -
 * 100 +
 * 1000 +
 * 1000
 *
 * another example:
 * MMCCXIV
 *
 * 5 -
 * 1 +
 * 10 +
 * 100 +
 * 100 +
 * 1000 +
 * 1000
 *
 * another example
 *
 * mdlxxix
 *
 * 10 -
 * 1 +
 * 10 +
 * 10 +
 * 50 +
 * 500 +
 * 1000
 *
 * 5- finally, build it up and print it
 */

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
        /*
        if (! input.equals("") && isValidRomanNumber(input)) {
            return input;
        }

        // todo - still cases to control - iix, XXC, xixiix, check validation logic

        // todo - in this cases says invalid input, lol? - LXXXIX, CDLXXXIX

        return "failed";*/
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

        // lifehack-trick to do reverse validation looool !?
// todo: make a test to try to get all numbers translated, from 1 to 1000, and from roman to arabic and from arabic to roman
        var test = ArabicToRoman.convert(String.valueOf(output));

        if (! test.equals(input)) {
            return "Invalid input";
        }

        //System.out.println(outputTest);
//        System.out.println(output);

        return String.valueOf(output);
    }

    // returns true if parsed String is a valid Roman number
    private static boolean isValidRomanNumber(String str) {
        return charactersAreRepeatedLessThanFourTimes(str) && isComposedByRomanNumbers(str);
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

    // returns true if parsed String's characters are repeated less than 4 times in the String itself
    private static boolean charactersAreRepeatedLessThanFourTimes(String str) {
        for (int i = 0; i < str.length(); i++) {
            var count = 0;

            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    count++;
                }
            }

            if (count > 3) {
                return false;
            }
        }

        return true;
    }
}
