

# String Calculator Recruitment project by Incubyte

This is a simple implementation of a **String Calculator** that performs addition of numbers provided as a string input. The calculator supports various delimiters, handles negative numbers, and allows multiple delimiters of different lengths. The project follows **Test-Driven Development (TDD)** principles, with detailed unit tests for each feature.

## Features

- **Basic Addition**: Adds 0, 1, or 2 numbers separated by commas and/or new lines.
- **Multiple Numbers**: Supports an unknown number of numbers in the input.
- **Custom Delimiters**: You can specify custom delimiters using a special format.
- **Negative Number Detection**: Throws an exception if negative numbers are provided, with all negative numbers listed in the exception message.
- **Number Limitation**: Ignores numbers greater than 1000.
- **Event Handling**: Fires an event after every addition operation.
- **Multiple Delimiters**: Supports multiple delimiters of varying lengths.

## Requirements

- Java 8 or higher
- Maven

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/pushpenderkadian/string-calculator.git
   ```

2. Navigate to the project directory:

   ```bash
   cd string-calculator
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

## Usage

### Add Method

The core method of this project is `add(String numbers)`, which performs the addition of numbers. It can handle inputs in different formats.

**Example 1**: 
```java
StringCalculator calculator = new StringCalculator();
int result = calculator.add(""); // returns 0
```

**Example 2**: 
```java
StringCalculator calculator = new StringCalculator();
int result = calculator.add("1"); // returns 1
```

**Example 3**:
```java
StringCalculator calculator = new StringCalculator();
int result = calculator.add("1,2"); // returns 3
```

**Example 4** (with new lines between numbers):
```java
StringCalculator calculator = new StringCalculator();
int result = calculator.add("1\n2,3"); // returns 6
```

**Example 5** (with custom delimiters):
```java
StringCalculator calculator = new StringCalculator();
int result = calculator.add("//;\n1;2"); // returns 3
```

### Negative Number Detection

If a negative number is passed, an exception will be thrown:

```java
StringCalculator calculator = new StringCalculator();
try {
    int result = calculator.add("-1,2");
} catch (NegativeNumberException e) {
    System.out.println(e.getMessage()); // "Negatives not allowed: -1"
}
```

### Event Handling

You can add an event listener to track the addition operations:

```java
StringCalculator calculator = new StringCalculator();
calculator.addEventListener((input, result) -> {
    System.out.println("Input: " + input + ", Result: " + result);
});
int result = calculator.add("1,2"); // will trigger the event
```

## Running Tests

This project follows TDD, so all functionality is covered by unit tests. To run the tests:

```bash
mvn test
```

## Project Structure

- `src/main/java/com/calculator/StringCalculator.java`: Contains the implementation of the StringCalculator class.
- `src/test/java/com/calculator/StringCalculatorTest.java`: Contains the unit tests for the StringCalculator class.


## Acknowledgements

- This project follows the principles of **Test-Driven Development** (TDD).
- The String Calculator Kata is inspired by the work of **Roy Osherove** and **Robert Martin (Uncle Bob)**.


