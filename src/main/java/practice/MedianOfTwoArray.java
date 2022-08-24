package practice;

import java.util.Arrays;

public class MedianOfTwoArray {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergedList = new int[nums1.length + nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            mergedList[i] = nums1[i];
        }
        int counter = nums1.length;

        for (int i = 0;  i < nums2.length; i++) {
            mergedList[counter] = nums2[i];
            counter++;
        }
        Arrays.sort(mergedList);

        double median;
        if (mergedList.length % 2 == 0)
            median = ((double) mergedList[mergedList.length / 2] + (double) mergedList[mergedList.length / 2 - 1]) / 2;
        else
            median = (double) mergedList[mergedList.length / 2];

        return median;
    }

    public static void main (String [] args){
        MedianOfTwoArray medianOfTwoArray = new MedianOfTwoArray();
        int [] nums1 = {1,3};
        int [] nums2 = {2};
        System.out.println(medianOfTwoArray.findMedianSortedArrays(nums1,nums2));
        int [] nums3 = {1,2};
        int [] nums4 = {3,4};
        System.out.println(medianOfTwoArray.findMedianSortedArrays(nums3,nums4));
    }
}
