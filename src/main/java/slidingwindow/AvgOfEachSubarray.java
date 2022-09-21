package slidingwindow;

import java.util.Arrays;

/**
 * Given an array, find the average of each subarray of ‘K’ contiguous elements in it.
 *Input: Array: [1, 3, 2, 6, -1, 4, 1, 8, 2], K=5
 *  Output: [2.2, 2.8, 2.4, 3.6, 2.8]
 *
 */
public class AvgOfEachSubarray {

    public static double[] findAverages(int k, int[] arr) {
        double[] result = new double[arr.length - k + 1];
        int windowStart = 0;
        double windowSum = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            if (windowEnd - windowStart == k - 1) {
                result[windowStart] = windowSum / k;
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        double[] result = AvgOfEachSubarray.findAverages(5,
                new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2});
        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
    }
}
