package towpointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem Statement
 *
 * Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
 *
 * Example 1:
 *
 * Input: [-3, 0, 1, 2, -1, 1, -2]
 * Output: [-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]
 * Explanation: There are four unique triplets whose sum is equal to zero.
 * Example 2:
 *
 * Input: [-5, 2, -1, -2, 3]
 * Output: [[-5, 2, 3], [-2, -1, 3]]
 * Explanation: There are two unique triplets whose sum is equal to zero.
 */
public class TripletSumToZero {

    public static List<List<Integer>> searchTriplets(int[] arr) {
        List<List<Integer>> triplets = new ArrayList<List<Integer>>();
        if(arr.length==0){
            return triplets;
        }

        //sort the array
         Arrays.sort(arr);
        for (int i =0; i<arr.length -2;i++){
                if(i >0 && arr[i] == arr[i-1]){
                    // skip the same element
                    continue;
                }
            searchPair(arr, -arr[i], i+1,triplets);
        }
        return triplets;
    }

    private static void searchPair(int[] arr, int targetSum, int leftIndex, List<List<Integer>> triplets) {

        int rightIndex = arr.length-1;
        while (leftIndex < rightIndex){
            int currentSum = arr[leftIndex] + arr[rightIndex];
            if(currentSum == targetSum){
                // we have found one triplet
                triplets.add(Arrays.asList(-targetSum,arr[leftIndex],arr[rightIndex]));
                leftIndex ++; rightIndex --;

                while (leftIndex < rightIndex && arr[leftIndex] == arr[leftIndex -1]){
                    // to avoid duplicate
                    leftIndex ++;
                }

                while (leftIndex < rightIndex && arr[rightIndex] == arr[rightIndex +1]){
                    // to avoid duplicate
                    rightIndex --;
                }

            } else if (currentSum < targetSum) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }
    }

    public static void main(String[] args) {
       // System.out.println(TripletSumToZero.searchTriplets(new int[] { -3, 0, 1, 2, -1, 1, -2 }));
        //System.out.println(TripletSumToZero.searchTriplets(new int[] { -5, 2, -1, -2, 3 }));
        System.out.println(TripletSumToZero.searchTriplets(new int[] { -0,0,0 }));

    }
}
