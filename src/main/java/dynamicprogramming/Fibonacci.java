package dynamicprogramming;

/**
 *  Fibonacci numbers are a series of numbers in which each number is the sum of the two preceding numbers.
 * 0, 1, 1, 2, 3, 5,8,13
 */
public class Fibonacci {

    public static int calculateFibonacci(int n) {
        int[] memorize = new int[n +1];
        return calculateFibonacciRecursive(memorize, n);
    }

    public static int calculateFibonacciRecursive(int[] memorize, int n) {
        if (n < 2) {
            return n;
        }

        if (memorize[n] != 0) {
            return memorize[n];
        }
        memorize[n] = calculateFibonacciRecursive(memorize, n - 1) + calculateFibonacciRecursive(memorize, n - 2);
        return memorize[n];
    }

    public static void main(String[] args) {
        System.out.println(calculateFibonacci(7));
    }
}
