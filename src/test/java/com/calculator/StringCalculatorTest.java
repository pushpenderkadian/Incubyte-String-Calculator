package com.calculator;


import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    void testAddEmptyString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(""));
    }

    @Test
    void testAddSingleNumber() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(1, calculator.add("1"));
    }

    @Test
    void testAddTwoNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    void testAddNewlineDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void testAddCustomDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    void testAddNegativeNumbers() {
        StringCalculator calculator = new StringCalculator();
        Exception exception = assertThrows(NegativeNumberException.class, () -> calculator.add("1,-2,-3"));
        assertEquals("Negatives not allowed: [-2, -3]", exception.getMessage());
    }

    @Test
    void testAddNumbersGreaterThan1000() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(2, calculator.add("2,1001"));
    }

    @Test
    void testAddWithMultipleDelimiters() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    void testAddWithMultipleLongDelimiters() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }

    @Test
    void testAddEventListener() {
        StringCalculator calculator = new StringCalculator();
        AtomicReference<String> input = new AtomicReference<>();
        AtomicReference<Integer> result = new AtomicReference<>();

        calculator.addEventListener((in, res) -> {
            input.set(in);
            result.set(res);
        });

        calculator.add("1,2");

        assertEquals("1,2", input.get());
        assertEquals(3, result.get());
    }
}
