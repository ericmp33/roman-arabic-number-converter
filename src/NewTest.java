import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

public class NewTest {
    public static final Map<Character, Integer> dictionary = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public static void main(String[] args) {
        String input = "mdlxxix".toUpperCase();

        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = input.length() - 1; i >= 0; i--) {
            integers.add(dictionary.get(input.charAt(i)));
        }

        System.out.println(Arrays.toString(integers.toArray()));

        var output = integers.get(0);
        var test = String.valueOf(integers.get(0));

        for (int i = 0; i < integers.size() - 1; i++) {
            int current = integers.get(i);
            int next = integers.get(i + 1);

            if (current > next) {
                test += " - " + next;
                output += (- next);
            } else {
                test += " + " +  next;
                output += next;

            }

            System.out.println("current       : " + current);
            System.out.println("next          : " + next);
            System.out.println("current < next: " + (current < next));
            System.out.println("output        : " + output);
            System.out.println();
        }

        System.out.println("output        : " + output);
        System.out.println("test          : " + test);
    }
}
