package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recursion.MyCounter;

public class Knapsack {
    MyCounter counter = new MyCounter(0);
    Integer [] [] cache;
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        cache = new Integer [profits.length] [capacity+1];
        return solveKnapsackRecusive(profits, weights, capacity, 0);
    }

    public int solveKnapsackRecusive(int[] profits, int[] weights, int capacity, int currentIndex) {

        if (capacity <=0 || currentIndex >= profits.length) {
            return 0;
        }

        if (cache[currentIndex][capacity] != null) {
            return cache[currentIndex][capacity];
        }

        int profit1 = 0;
        if (weights[currentIndex] <= capacity) {
            profit1 = profits[currentIndex] + solveKnapsackRecusive(profits, weights, capacity - weights[currentIndex], currentIndex + 1);
        }
        int profit2 = solveKnapsackRecusive(profits, weights, capacity, currentIndex + 1);
        counter.increment();
        return cache[currentIndex][capacity] = Math.max(profit1, profit2);
    }

    private int knapsackRecursive(int[] profits, int[] weights, int capacity,
                                  int currentIndex) {
        // base checks
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't
        // process this

        int profit1 = 0;
        if( weights[currentIndex] <= capacity )
            profit1 = profits[currentIndex] + knapsackRecursive(profits, weights,
                    capacity - weights[currentIndex], currentIndex + 1);

        // recursive call after excluding the element at the currentIndex
        int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

        return Math.max(profit1, profit2);
    }

    @Test
    void test1() {
        int[] profits = new int[]{4, 5, 3, 7};
        int[] weights = new int[]{2, 3, 1, 4};
        Knapsack knapsack = new Knapsack();
        Assertions.assertEquals(10, knapsack.solveKnapsack(profits, weights, 5));
        System.out.println("Total recursive calls: " + knapsack.counter.getCounter());
    }
}
