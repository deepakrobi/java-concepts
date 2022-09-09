package dynamicprogramming;

/**
 * You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.
 *
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
 *
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Remove x from the array nums.
 * Return the maximum score after performing m operations.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 * Example 2:
 *
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 103
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 */
public class MaximumScore {
    int totalNumbers;
    int totalOperations;
    int[][] cache;
    int[] nums, multipliers;

    public int maximumScore(int[] nums, int[] multipliers) {
        this.nums = nums;
        this.multipliers = multipliers;
        this.totalNumbers = nums.length;
        this.totalOperations = multipliers.length;
        cache = new int[multipliers.length][nums.length];

        return maximumScoreRecursive(0, 0);
    }

    private int maximumScoreRecursive(int operations, int left) {
        if (operations == totalOperations) {
            return 0; // Base case
        }

        int right = totalNumbers - 1 - (operations - left);
        if (cache[operations][left] == 0) {
            int multipler = multipliers[operations];
            int pickFromLeft = multipler * nums[left];
            int pickFromRight = multipler * nums[right];
            cache[operations][left] = Math.max(multipler * nums[left] + maximumScoreRecursive(operations + 1, left + 1),
                    multipler * nums[right] + maximumScoreRecursive(operations + 1, left));
        }
        return cache[operations][left];
    }

        private int[][] memo;
        private int n, m;

        private int dp(int i, int left) {
            if (i == m) {
                return 0; // Base case
            }

            int mult = multipliers[i];
            int right = n - 1 - (i - left);

            if (memo[i][left] == 0) {
                // Recurrence relation
                memo[i][left] = Math.max(mult * nums[left] + dp(i + 1, left + 1),
                        mult * nums[right] + dp(i + 1, left));
            }

            return memo[i][left];
        }

        public int maximumScoreDP(int[] nums, int[] multipliers) {
            n = nums.length;
            m = multipliers.length;
            this.nums = nums;
            this.multipliers = multipliers;
            this.memo = new int[n][m];
            return dp(0, 0);
        }

    public static  void main (String [] args) {
        MaximumScore score = new MaximumScore();
       int []  nums = {1,2,3}, multipliers = {3,2,1};
       // System.out.println(score.maximumScore(nums,multipliers));
        int [] nums1= {-5,-3,-3,-2,7,1}, multipliers1 = {-10,-5,3,4,6};
        System.out.println(score.maximumScore(nums1,multipliers1));
        System.out.println(score.maximumScoreDP(nums1,multipliers1));
    }
}
