package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Problem Statement
 *
 * Given an array of positive numbers, where each element represents the max number of jumps that can be made forward
 * from that element, write a program to find the minimum number of jumps needed to reach the end of the array
 * (starting from the first element). If an element is 0, then we cannot move through that element.
 *
 * Example 1:
 * Input = {2,1,1,1,4}
 * Output = 3
 * Explanation: Starting from index '0', we can reach the last index through: 0->2->3->4
 *
 * Example 2:
 * Input = {1,1,3,6,9,3,0,1,3}
 * Output = 4
 * Explanation: Starting from index '0', we can reach the last index through: 0->1->2->3->8
 */
public class MinimumJump {

    public static int countMinJump(int[] jumps) {
        int totalJump = 0;
        for (int i = 0; i < jumps.length; i++){
            int count = countMinJumpRecursive(jumps,i,jumps[i]);
            if(count!= Integer.MAX_VALUE){
                totalJump+=count;
            }
        }
        return totalJump;
    }

    private static int countMinJumpRecursive(int[] jumps, int start, int end) {
        if(start >= jumps.length){
            return Integer.MAX_VALUE;
        }

        if(start == jumps.length -1){
            return 0;
        }
        int count = Integer.MAX_VALUE;

        while(start <=end ){
           int minJump =  countMinJumpRecursive(jumps,start+1,end);
           start++;
           if(minJump != Integer.MAX_VALUE){
               count = minJump+1;
           }
        }
        return count;
    }

    @Test
    void test1(){
        Assertions.assertEquals(4, countMinJump(new int[]{2, 1, 1, 1, 4}));
    }
}
