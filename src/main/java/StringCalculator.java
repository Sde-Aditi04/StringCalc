import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = ",";

    public static void main(String[] args) {
        String numbers = "//;\n1;2;3";
        int sum = Add(numbers);
        System.out.println("Sum: " + sum);
    }

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
}
