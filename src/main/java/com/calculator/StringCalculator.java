package com.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringCalculator handles the addition of numbers from a string input with various delimiters and rules.
 */
public class StringCalculator {
    private int callCount = 0;
    private List<AddEventListener> listeners = new ArrayList<>();

    public int add(String numbers) {
        callCount++;
        if (numbers == null || numbers.isEmpty()) {
            notifyListeners(numbers, 0);
            return 0;
        }

        String delimiter = ",|\n"; // Default delimiters
        if (numbers.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(\\[.*?])+\\n").matcher(numbers);
            if (matcher.find()) {
                delimiter = extractDelimiters(matcher.group());
                numbers = numbers.substring(matcher.end());
            } else {
                delimiter = Pattern.quote(numbers.substring(2, numbers.indexOf("\n")));
                numbers = numbers.substring(numbers.indexOf("\n") + 1);
            }
        }

        String[] tokens = numbers.split(delimiter);
        List<Integer> negatives = new ArrayList<>();
        int sum = 0;

        for (String token : tokens) {
            if (token.trim().isEmpty()) continue; // Skip empty tokens
            int num = Integer.parseInt(token.trim());
            if (num < 0) negatives.add(num);
            if (num <= 1000) sum += num; // Ignore numbers > 1000
        }

        if (!negatives.isEmpty()) {
            throw new NegativeNumberException("Negatives not allowed: " + negatives);
        }

        notifyListeners(numbers, sum);
        return sum;
    }

    private String extractDelimiters(String delimiterDefinition) {
        Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(delimiterDefinition);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            if (sb.length() > 0) {
                sb.append("|");
            }
            sb.append(Pattern.quote(matcher.group(1))); // Properly quote each delimiter
        }
        return sb.length() > 0 ? sb.toString() : ",|\n"; // Default to comma and newline
    }

    public int getCalledCount() {
        return callCount;
    }

    public void addEventListener(AddEventListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String input, int result) {
        for (AddEventListener listener : listeners) {
            listener.onAdd(input, result);
        }
    }
}
