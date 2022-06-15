import java.util.ArrayList;
import java.util.Map;

import static java.util.Map.entry;

public class ArabicToRoman {
    public static final Map<Integer, Character> dictionary = Map.ofEntries(
        entry(1, 'I'),
        entry(5, 'V'),
        entry(10, 'X'),
        entry(50, 'L'),
        entry(100, 'C'),
        entry(500, 'D'),
        entry(1000, 'M')
    );

    public static void main(String[] args) {
        var input = "268";
        System.out.println("Input: " + input);
        System.out.println();

        // todo - check it's a valid integer and between 1 and 1000

        // check if whole number is already a key
        if (dictionary.containsKey(Integer.parseInt(input))) {
            System.out.println(dictionary.get(Integer.parseInt(input)));
            return;
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
                var firstNumDigit = Integer.parseInt(String.valueOf(num.toString().charAt(0)));

                // get num type range
                var numTypeRange = getNumTypeRange(firstNumDigit);

                // get num range
                var numRange = getNumRange(num, firstNumDigit);
                var firstNumRangeDigit = Integer.parseInt(String.valueOf(numRange.toString().charAt(0)));

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

                    // put it together

                } else {

                }
            } else {
                convertedNums.add(String.valueOf(dictionary.get(num)));
            }
        }

        System.out.println(convertedNums);

        convertedNums.forEach(System.out::print);
        System.out.println();
    }

    /**
     * Gets the range where parsed num belongs to.
     * Number has "lower" range if is not 4x or 9x,
     * those two are generated by subtracting, and they belong to "upper" range.
     */
    private static Integer getNumRange(int num, int firstNumDigit) {
        if (firstNumDigit == 4 || firstNumDigit == 9) {
            // upper range
            if (num >= 500) {
                return 1000;
            }

            if (num >= 100) {
                return 500;
            }

            if (num >= 50) {
                return 100;
            }

            if (num >= 10) {
                return 50;
            }

            if (num >= 5) {
                return 10;
            }

            if (num >= 1) {
                return 5;
            }

            return 1;
        }

        // lower range
        if (num <= 5) {
            return 1;
        }

        if (num <= 10) {
            return 5;
        }

        if (num <= 50) {
            return 10;
        }

        if (num <= 100) {
            return 50;
        }

        if (num <= 500) {
            return 100;
        }

        if (num <= 1000) {
            return 500;
        }

        return 1000;
    }

    private static String getNumTypeRange(int firstNumDigit) {
        if (firstNumDigit == 4 || firstNumDigit == 9) {
            return "upper";
        }

        return "lower";
    }
}
