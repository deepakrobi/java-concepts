package dynamicprogramming;

import java.util.HashMap;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and
 * it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can
 rob tonight without alerting the police.

 Example 1:
 Input: nums = [1,2,3,1]
 Output: 4
 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 Total amount you can rob = 1 + 3 = 4.
 Example 2:

 Input: nums = [2,7,9,3,1]
 Output: 12
 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 Total amount you can rob = 2 + 9 + 1 = 12.
 *
 */
public class HouseRobber {
    private HashMap<Integer,Integer> cache = new HashMap<>();
    public int rob(int[] nums) {

        return robRecursive(nums,nums.length-1);
    }

    public int robRecursive(int[] nums, int currentHouse){

        if (currentHouse ==0){
            return nums[0];
        }
        if(currentHouse ==1){
            return Math.max(nums[0],nums[1]);
        }

        if(!cache.containsKey(currentHouse)){
            int profitFromNotRobbingCurrentHouse = robRecursive(nums,currentHouse -1);
            int profitFromRobbingCurrentHouse = robRecursive(nums,currentHouse -2) + nums[currentHouse];

            int profit = Math.max(profitFromNotRobbingCurrentHouse,profitFromRobbingCurrentHouse);
            cache.put(currentHouse,profit);
        }

        return cache.get(currentHouse);
    }

    public int robBottomUp(int[] nums) {
        int profits[] = new int[nums.length];
        if (nums.length == 1) {
            return nums[0];
        }
        profits[0] = nums[0];
        profits[1] = Math.max(nums[0], nums[1]);
        for (int currentHouse = 2; currentHouse < nums.length; currentHouse++) {
            int profitFromNotRobbingCurrentHouse = profits[currentHouse - 1];
            int profitFromRobbingCurrentHouse = profits[currentHouse - 2] + nums[currentHouse];
            profits[currentHouse] = Math.max(profitFromNotRobbingCurrentHouse, profitFromRobbingCurrentHouse);
        }
        return profits[nums.length - 1];
    }

    public static void main(String[] args) {
        HouseRobber robber = new HouseRobber();
        int [] input = {1,2,3,1};
       System.out.println( robber.rob(input));
        System.out.println( robber.robBottomUp(input));
    }
}
