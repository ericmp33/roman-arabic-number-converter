import java.util.ArrayList;

public class RomanToArabic {
    public static void mainz(String[] args) {
        var input = "xxxiii";
        var output = "error";

        input = input.toUpperCase();

        switch (input) {
            case "I" -> output = "1";
            case "V" -> output = "5";
            case "X" -> output = "10";
            case "D" -> output = "50";
            case "C" -> output = "100";
            case "L" -> output = "500";
            case "M" -> output = "1000";
        }

        System.out.println(output);

        System.out.println(hasValidSrtucture(input));
    }

    private static boolean hasValidSrtucture(String str) {
        return hasValidRomanNumberCharacters(str) && numbersAreCorrectlyRepeated(str);
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


    public static void main(String[] args) {
        System.out.println(numbersAreCorrectlyRepeated("xiiiixzg"));
    }


    // returns true if parsed String is only composed by roman numbers
    private static boolean hasValidRomanNumberCharacters(String str) {
        str = str.toUpperCase();

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
}
