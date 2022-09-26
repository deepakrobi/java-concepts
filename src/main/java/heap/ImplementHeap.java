package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ImplementHeap {

    public static void maxHeap(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(nums.length, new NumComparator());
        for (int i = 0; i < nums.length; i++) {
            maxHeap.add(nums[i]);
        }

        System.out.println(" Max Heap: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(" " + maxHeap.poll() + "");
        }

        PriorityQueue<Integer> kmaxHeap = new PriorityQueue<>(nums.length, new NumComparator());

        for (int i = 0; i < k; i++) {
            kmaxHeap.add(nums[i]);
        }

        System.out.println();
        System.out.println( k + " Max Heap: ");
        while (!kmaxHeap.isEmpty()) {
            System.out.print(" " + kmaxHeap.poll() + "");
        }
    }

    public static void main(String[] args) {
        maxHeap(new int[]{3, 1, 5, 12, 2, 11, 6, 13, 4},4);
    }
}
class NumComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer num1, Integer num2) {
        return num2 - num1;
    }
}

