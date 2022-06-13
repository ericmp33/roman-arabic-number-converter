import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class ArabicToRoman {
    public static final Map<Integer, Character> dictionary = Map.of(
        1, 'I',
        5, 'V',
        10, 'X',
        50, 'L',
        100, 'C',
        500, 'D',
        1000, 'M'
    );

    public static final Map<Character, Integer> romanToArabic = Map.of(
        'I', 1,
        'V', 5,
        'X', 10,
        'L', 50,
        'C', 100,
        'D', 500,
        'M', 1000
    );

    public static void main(String[] args) {
        String input = "150".trim();

        // abans de fer res, provar de buscar directament?, per exemple si es 100, doncs ja esta, C
        if (dictionary.containsKey(Integer.parseInt(input))) {
            System.out.println(dictionary.get(Integer.parseInt(input)));
            return;
        }

        /*
         inputs a provar:
          427 777 444 1515 5151 150
         */

        // 1- split

        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            if (Integer.parseInt(input.charAt(i) + "0".repeat(input.length() - i - 1)) != 0) {
                integers.add(Integer.parseInt(input.charAt(i) + "0".repeat(input.length() - i - 1)));
            }
        }

        // check if up or down

        for (int i = 0; i < integers.size(); i++) {
            // know if between 1x and 5x or 5x and 1x
            var number = integers.get(i);
            var firstCharAsInt = Integer.parseInt(String.valueOf(number.toString().charAt(0)));
            var upperRomanNumber = getUpperRomanNumber(number);
            var lowerRomanNumber = getLowerRomanNumber(number);

            if (firstCharAsInt == 4 || firstCharAsInt == 9) {
                System.out.println(number);

                System.out.println(
                    (dictionary.get(romanToArabic.get(upperRomanNumber) - number)).toString()
                    +
                    upperRomanNumber.toString()
                );
            } else {
                System.out.println(number);
                System.out.println("b");

                System.out.println(
                        lowerRomanNumber
                        +
                        dictionary.get(romanToArabic.get(getLowerRomanNumber(romanToArabic.get(lowerRomanNumber)))).toString().repeat(
                    firstCharAsInt
                            -
                            Integer.parseInt(String.valueOf(romanToArabic.get(lowerRomanNumber).toString().charAt(0)))

                        )
                );
            }
            System.out.println();

        }
        System.out.println();
    }

    private static Character getUpperRomanNumber(int num) {
        if (num >= 500) {
            return dictionary.get(1000);
        }

        if (num >= 100) {
            return dictionary.get(500);
        }

        if (num >= 50) {
            return dictionary.get(100);
        }

        if (num >= 10) {
            return dictionary.get(50);
        }

        if (num >= 5) {
            return dictionary.get(10);
        }

        if (num >= 1) {
            return dictionary.get(5);
        }

        return dictionary.get(1);
    }

    private static Character getLowerRomanNumber(int num) {
        if (num <= 5) {
            return dictionary.get(1);
        }

        if (num <= 10) {
            return dictionary.get(5);
        }

        if (num <= 50) {
            return dictionary.get(10);
        }

        if (num <= 100) {
            return dictionary.get(50);
        }

        if (num <= 500) {
            return dictionary.get(100);
        }

        if (num <= 1000) {
            return dictionary.get(500);
        }

        return dictionary.get(1000);
    }
}


























