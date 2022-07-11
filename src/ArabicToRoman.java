import java.util.ArrayList;
import java.util.Map;

public class ArabicToRoman {
    public static final Map<Integer, Character> dictionary = Map.ofEntries(
        Map.entry(1, 'I'),
        Map.entry(5, 'V'),
        Map.entry(10, 'X'),
        Map.entry(50, 'L'),
        Map.entry(100, 'C'),
        Map.entry(500, 'D'),
        Map.entry(1000, 'M')
    );

    private ArabicToRoman() {}

    public static String validate(String input) {
        var inputAsInt = Integer.parseInt(input);
        
        if (inputAsInt >= 1 && inputAsInt <= 1000) {
            return input;
        }
        
        return "failed";
    }

    public static String convert(String input) {
        // if whole number is already a key
        if (dictionary.containsKey(Integer.parseInt(input))) {
            return dictionary.get(Integer.parseInt(input)).toString();
        }

        // split the numbers and add zeros to them
        var splittedNums = new ArrayList<Integer>();

        for (int i = 0; i < input.length(); i++) {
            var num = Integer.parseInt(input.charAt(i) + "0".repeat(input.length() - i - 1));

            // don't add zeros
            if (num != 0) {
                splittedNums.add(num);
            }
        }

        var convertedNums = new ArrayList<String>();

        for (Integer num : splittedNums) {
            // if number isn't a key
            if (!dictionary.containsKey(num)) {
                convertedNums.addAll(convertCurrentIndex(num));
            } else {
                convertedNums.add(String.valueOf(dictionary.get(num)));
            }
        }

        StringBuilder output = new StringBuilder();

        for (String convertedNum : convertedNums) {
            output.append(convertedNum);
        }

        return output.toString();
    }

    private static ArrayList<String> convertCurrentIndex(Integer num) {
        var convertedNums = new ArrayList<String>();

        var firstNumDigit = Integer.parseInt(String.valueOf(num.toString().charAt(0)));

        // get num type range
        var numTypeRange = getNumTypeRange(firstNumDigit);

        // get num range
        var numRange = getNumRange(num, firstNumDigit);
        var firstNumRangeDigit = Integer.parseInt(String.valueOf(numRange.toString().charAt(0)));

        // handle lower ranges
        if (numTypeRange.equals("lower")) {
            var timesToBeRepeated = firstNumDigit - firstNumRangeDigit;

            // get next num range from current num range if num is 3X or 2X
            if (firstNumDigit != 3 && firstNumDigit != 2) {
                var subNumRange = getNumRange(numRange, firstNumRangeDigit);

                var subNumRangeAsRoman = dictionary.get(subNumRange);

                // repeat num n times
                var subNumRangeAsRomanRepeated = subNumRangeAsRoman.toString().repeat(timesToBeRepeated);

                convertedNums.add(dictionary.get(numRange).toString());

                convertedNums.add(subNumRangeAsRomanRepeated);
            } else {
                var numRangeAsRoman = dictionary.get(numRange);

                // repeat num n times
                var numRangeAsRomanRepeated = numRangeAsRoman.toString().repeat(firstNumDigit);

                convertedNums.add(numRangeAsRomanRepeated);
            }
        }

        // handle upper ranges
        else {
            // convert upper range
            var numRangeAsRoman = dictionary.get(numRange);

            var subtract = numRange - num;

            var subtractAsRoman = dictionary.get(subtract);

            var numAsRoman = subtractAsRoman.toString() + numRangeAsRoman.toString();

            convertedNums.add(numAsRoman);
        }

        return convertedNums;
    }


    private static Integer getNumRange(int num, int firstNumDigit) {
        // upper range
        if (firstNumDigit == 4 || firstNumDigit == 9) {
            if (num >= 500) return 1000;
            if (num >= 100) return 500;
            if (num >= 50) return 100;
            if (num >= 10) return 50;
            if (num >= 5) return 10;
            if (num >= 1) return 5;
            return 1;
        }

        // lower range
        if (num <= 5) return 1;
        if (num <= 10) return 5;
        if (num <= 50) return 10;
        if (num <= 100) return 50;
        if (num <= 500) return 100;
        if (num <= 1000) return 500;
        return 1000;
    }

    /**
     * todo - do doc-block comments everywhere
     * Returns the range where parsed num belongs to.
     * Number has "upper" range if it's first digit is 4 or 9. These two are generated by subtracting.
     * Other ones aren't generated by subtracting, so they belong to "lower" range.
     */
    private static String getNumTypeRange(int firstNumDigit) {
        if (firstNumDigit == 4 || firstNumDigit == 9) {
            return "upper";
        }

        return "lower";
    }
}
