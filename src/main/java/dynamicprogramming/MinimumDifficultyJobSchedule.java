package dynamicprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 * Example 1:
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 *
 * Example 2:
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 *
 * Example 3:
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 *
 * Constraints:
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 */
public class MinimumDifficultyJobSchedule {
    int nbrOfJobs;
    int nbrOfDays;
     int[] jobDifficulty;
     int jobCompleted =0;
     int answer;
    int min = Integer.MAX_VALUE;

    public int minDifficulty(int[] jobDifficulty, int d) {
        this.jobDifficulty = jobDifficulty;
        this.nbrOfJobs = jobDifficulty.length;
        this.nbrOfDays = d;
        if (nbrOfDays > nbrOfJobs) {
            return -1;
        }
        for(int i=0;i<d;i++){
            answer+=solve(i+1);
        }
       return answer;
    }

    private int solve(int currentDay){
        int max =0;
        while (nbrOfJobs-jobCompleted > nbrOfDays - currentDay){
            max =  Math.max(jobDifficulty[jobCompleted],max);
            jobCompleted++;
        }
        return max;
    }

    @Test
    void test1() {
        int [] input ={6,5,4,3,2,1};
        Assertions.assertEquals(7, new MinimumDifficultyJobSchedule().minDifficulty(input,2) );
    }

    @Test
    void test2() {
        int [] input ={1,1,1};
        Assertions.assertEquals(3, new MinimumDifficultyJobSchedule().minDifficulty(input,3) );
    }

    @Test
    void test3(){
        int [] input ={9,9,9};
        Assertions.assertEquals(-1, new MinimumDifficultyJobSchedule().minDifficulty(input,4) );
    }

    @Test
    void test4(){
        int [] input ={11,111,22,222,33,333,44,444};
        Assertions.assertEquals(843, new MinimumDifficultyJobSchedule().minDifficulty(input,6) );
    }
}
