package Array;

import java.util.HashMap;

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Example 2:
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 */
public class RemoveDuplicateFromSortedArray {

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums == null) {  //for corner cases if array is empty
            return 0;
        }
        int i = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[j - 1]) {
                nums[i] = nums[j];
                i++;

            }
        }
        return i;
    }

    public static int removeDuplicatesManual(int[] nums) {
        if (nums.length == 0 || nums == null) {  //for corner cases if array is empty
            return 0;
        }
        if(nums.length ==1){
            return 1;
        }
        int uniqueLength = 1;
        for (int j = 0; j < nums.length - 1; j++) {
            if (nums[j] != nums[j + 1]) {
                nums[uniqueLength-1] = nums[j];
                uniqueLength++;

                if (j == nums.length - 2) {
                    nums[uniqueLength-1] = nums[j + 1];
                    uniqueLength++;
                }
            }
        }
        return uniqueLength;
    }


    public static int removeDuplicatesMap(int[] nums) {
        int uniqueLength = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] result = new int[nums.length];
       int  j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], nums[i]);
                result[j] = nums[i];
                j++;
            }
        }

        return j;

    }

    public static int findNonUniqueIndex(int [] nums,int value , int currentIndex) {
        if (currentIndex == nums.length) {
            return currentIndex;
        }
        if (value == nums[currentIndex]) {
            currentIndex = currentIndex + 1;
            findNonUniqueIndex(nums, value, currentIndex);
        }
        return currentIndex;
    }


    public static void main(String[] args) {
        int[] input = {1,1,1,1};
       // System.out.println(removeDuplicatesMap(input));
        System.out.println(removeDuplicates(input));
        //System.out.println(removeDuplicatesManual(input));
    }
}
