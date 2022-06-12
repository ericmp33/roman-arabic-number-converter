import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RomanToArabic {
    public static void main(String[] args) {
        Map<Character, Integer> dictionary = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};

        // System.out.println(dictionary.get('C'));
        // System.out.println(dictionary.containsKey('C'));
        // System.out.println(dictionary.containsValue(10));

        var input = "ix".toUpperCase();

        if (!hasValidStructure(input)) {
            System.out.println("Invalid structure");
            return;
        }

        String output = "unhandled case";

        if (input.length() == 1) {
            var character = input.charAt(0);

            if (dictionary.containsKey(character)) {
                output = dictionary.get(character).toString();
            }
        } else if (input.length() == 3 && allCharactersAreTheSame(input)) {
            output = String.valueOf(dictionary.get(input.charAt(0)) * 3);
        } else {
            // if any of chars is greater than last one, means there is a subtract
            //if (thereIsASubtract(input, dictionary)) {
              //  System.out.println("subtract");
                //return;
            //}

            /**
             * read numbers:
             *
             * example:
             *
             * 1000
             * 1000
             * 100
             * 1000
             * 10
             * 1
             * 5
             *
             * is 2914
             */

//            example:
//1000
//1000
//100
//1000
//10
//1
//5



            var numbers = new ArrayList<Integer>();

            int finalNum = 0;

            for (int i = 0; i < input.length(); i++) {
                numbers.add(dictionary.get(input.charAt(i)));
                finalNum += numbers.get(i);
            }

            output = String.valueOf(finalNum);
        }

        System.out.println(output);
    }

    private static boolean allCharactersAreTheSame(String input) {
        return input.charAt(0) == input.charAt(1) && input.charAt(0) == input.charAt(2);
    }

    private static boolean hasValidStructure(String str) {
        return hasValidRomanNumberCharacters(str) && numbersAreCorrectlyRepeated(str);
    }

    // returns true if parsed String is only composed by roman numbers
    private static boolean hasValidRomanNumberCharacters(String str) {
        char[] validChars = { 'I', 'V', 'X', 'D', 'C', 'L', 'M' };

        for (int i = 0; i < str.length(); i++) {
            var count = 0;

            for (int j = 0; j < validChars.length; j++) {
                if (str.charAt(i) == validChars[j]) {
                    count++;
                    break;
                }
            }

            if (count == 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean numbersAreCorrectlyRepeated(String str) {
        var checkedChars = new ArrayList<Character>();

        for (int i = 0; i < str.length(); i++) {
            var count = 0;

            if (checkedChars.contains(str.charAt(i))) {
                break;
            } else {
                checkedChars.add(str.charAt(i));
            }

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
