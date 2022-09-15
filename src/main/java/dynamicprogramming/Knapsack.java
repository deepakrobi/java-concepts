package dynamicprogramming;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recursion.MyCounter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Given the weights and profits of ’N’ items, put these items in a knapsack which has a capacity ‘C’. Your goal: get the maximum profit from the items in the knapsack. Each item can only be selected once.
 *
 * A common example of this optimization problem involves which fruits in the knapsack you’d include to get maximum profit. Here’s the weight and profit of each fruit:
 *
 * Items: { Apple, Orange, Banana, Melon }
 * Weight: { 2, 3, 1, 4 }
 * Profit: { 4, 5, 3, 7 }
 * Knapsack capacity: 5
 */
public class Knapsack {

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        return this.knapsackRecursive(profits, weights, capacity, 0);
    }

    private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
        // base checks
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
        int profit1 = 0;
        if (weights[currentIndex] <= capacity)
            profit1 = profits[currentIndex] + knapsackRecursive(profits, weights,
                    capacity - weights[currentIndex], currentIndex + 1);

        // recursive call after excluding the element at the currentIndex
        int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

        return Math.max(profit1, profit2);
    }

    public int solveKnapsackDP1(int[] profits, int[] weights, int capacity) {
        Integer[][] dp = new Integer[profits.length][capacity + 1];
        return this.knapsackRecursive(dp, profits, weights, capacity, 0);
    }

    private int knapsackRecursive(Integer[][] dp, int[] profits, int[] weights, int capacity,
                                  int currentIndex) {

        // base checks
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;

        // if we have already solved a similar problem, return the result from memory
        if (dp[currentIndex][capacity] != null)
            return dp[currentIndex][capacity];

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
        int profit1 = 0;
        if (weights[currentIndex] <= capacity)
            profit1 = profits[currentIndex] + knapsackRecursive(dp, profits, weights,
                    capacity - weights[currentIndex], currentIndex + 1);

        // recursive call after excluding the element at the currentIndex
        int profit2 = knapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);

        dp[currentIndex][capacity] = Math.max(profit1, profit2);
        return dp[currentIndex][capacity];
    }

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsackDP1(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }

}
