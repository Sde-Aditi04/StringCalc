import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StringCalculatorTest {
    private static final String DEFAULT_DELIMITER = ",";

    public static int Add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = DEFAULT_DELIMITER;
        String[] lines = numbers.split("\n", 2);
        if (lines.length > 1 && lines[0].startsWith("//")) {
            delimiter = lines[0].substring(2);
            numbers = lines[1];
        }

        String[] numberArray = numbers.split("[" + delimiter + "\n]");
        List<Integer> negatives = new ArrayList<>();

        int sum = 0;
        for (String number : numberArray) {
            if (!number.isEmpty()) {
                int num = Integer.parseInt(number);
                if (num < 0) {
                    negatives.add(num);
                }
                sum += num;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        return sum;
    }

    @Test
    public void testEmptyString() {
        int sum = Add("");
        Assertions.assertEquals(0, sum);
    }

    @Test
    public void testSingleNumber() {
        int sum = Add("5");
        Assertions.assertEquals(5, sum);
    }

    @Test
    public void testTwoNumbers() {
        int sum = Add("3,7");
        Assertions.assertEquals(10, sum);
    }

    @Test
    public void testMultipleNumbers() {
        int sum = Add("1,2,3,4,5");
        Assertions.assertEquals(15, sum);
    }

    @Test
    public void testNewLineDelimiter() {
        int sum = Add("1\n2,3");
        Assertions.assertEquals(6, sum);
    }

    @Test
    public void testCustomDelimiter() {
        int sum = Add("//;\n1;2;3");
        Assertions.assertEquals(6, sum);
    }

    @Test
    public void testNegativeNumbers() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Add("1,-2,3,-4"));
    }
}
