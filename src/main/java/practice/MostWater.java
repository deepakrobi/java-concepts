package practice;

import com.google.common.base.Stopwatch;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MostWater {
    public static int maxArea(int[] height) {

        int max = 0;
        for (int i=0;i<height.length-1;i++){
            for(int j=i+1;j<height.length;j++){
                int length = Math.min(height[i],height[j]);
                int width = j-i;
                max = Math.max(max,length*width);
            }
        }
        return max;
    }

    public static int maxAreaTwoPointer(int[] height) {
        int max = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            if (height[i] < height[j]) {
                max = Math.max(max, height[i] * (j - i));
                i++;
            } else {
                max = Math.max(max, height[j] * (j - i));
                j--;
            }
        }
        return max;
    }

    public static void main (String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
        try (InputStream input = new FileInputStream("src/main/resources/config.poperties")) {
            Properties prop = new Properties();
            prop.load(input);
            String line = prop.getProperty("line");
            int[] height1 = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            Stopwatch stopwatch = Stopwatch.createStarted();
            System.out.println(maxArea(height1));
            System.out.println("Total Time taken : " + stopwatch.elapsed(TimeUnit.SECONDS) + " seconds");
            stopwatch.reset();
            System.out.println(maxAreaTwoPointer(height1));
            System.out.println("Total Time taken using 2 pointer : " + stopwatch.elapsed(TimeUnit.SECONDS) + " seconds");
            int[] height2 = {99, 100, 1, 2, 3, 4, 5, 6, 100};
            System.out.println(maxArea(height2));
            System.out.println(maxAreaTwoPointer(height2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
