package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a staircase with ‘n’ steps and an array of ‘n’ numbers representing the fee that you have to pay if you take
 * the step. Implement a method to calculate the minimum fee required to reach the top of the staircase
 * (beyond the top-most step). At every step, you have an option to take either 1 step, 2 steps, or 3 steps. You should
 * assume that you are standing at the first step.
 *
 * Example 1:
 * Number of stairs (n) : 6
 * Fee: {1,2,5,2,1,2}
 * Output: 3
 * Explanation: Starting from index '0', we can reach the top through: 0->3->top
 * The total fee we have to pay will be (1+2).
 *
 * Example 2:
 * Number of stairs (n): 4
 * Fee: {2,3,4,5}
 * Output: 5
 * Explanation: Starting from index '0', we can reach the top through: 0->1->top
 * The total fee we have to pay will be (2+3).
 */
public class MinimumJumpWithFee {
    public static int calculateMinimumFee(int[] fees){
        return calculateRecursive(fees,0);
    }

    private static int calculateRecursive(int[] fees, int currentStair) {
        if (fees == null || fees.length < 1) {
            // no stairs to climb
            return 0;
        }
        if (currentStair > fees.length-1) {
            // reached to the top
            return 0;
        }

        // if we take 1 step
        int costToClimbOneStair = fees[currentStair] + calculateRecursive(fees, currentStair + 1);

        // if we take 2 step
        int costToClimbTwoStair = fees[currentStair] + calculateRecursive(fees, currentStair + 2);

        // if we take 3 step
        int costToClimbThreeStair = fees[currentStair] + calculateRecursive(fees, currentStair + 3);

        return Math.min(costToClimbOneStair, Math.min(costToClimbTwoStair, costToClimbThreeStair));
    }

    private static int calculateRecursiveDown(int[] fees, int currentStair) {
        
        if (fees == null || fees.length < 1) {
            // no stairs to climb
            return 0;
        }

        if (currentStair < 0) {
            // reached to the bottom since we are allowed to take 1 or 2 or 3 steps
            return 0;
        }

        // if we take 1 step
        int costToClimbOneStair = currentStair - 1 < 0 ? 0 : fees[currentStair - 1] + calculateRecursiveDown(fees, currentStair - 1);

        // if we take 2 step
        int costToClimbTwoStair = currentStair - 2 < 0 ? 0 : fees[currentStair - 2] + calculateRecursiveDown(fees, currentStair - 2);

        // if we take 3 step
        int costToClimbThreeStair = currentStair - 3 < 0 ? 0 : fees[currentStair - 3] + calculateRecursiveDown(fees, currentStair - 3);

        return Math.min(costToClimbOneStair, Math.min(costToClimbTwoStair, costToClimbThreeStair));
    }

      public static int calculateMinimumFeeBottomUp(int[] fees) {

        int[] minCost = new int[fees.length+1];
        minCost[0] = 0;           // no fee for 0th steps.
        minCost[1] = fees[0];    // only one step, so we have to pay its fee
        minCost[2] = minCost[3] = fees[0];
        return 0;
      }

    @Test
    void test1() {
        Assertions.assertEquals(3,calculateMinimumFee(new int[]{1,2,5,2,1,2}));
    }

    @Test
    void test2(){
        Assertions.assertEquals(5,calculateMinimumFee(new int[]{2,3,4,5}));
    }
}
