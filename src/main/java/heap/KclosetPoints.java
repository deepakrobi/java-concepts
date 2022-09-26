package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an array of points in a 2D2D plane, find ‘K’ closest points to the origin.
 *
 * Example 1:
 *
 * Input: points = [[1,2],[1,3]], K = 1
 * Output: [[1,2]]
 * Explanation: The Euclidean distance between (1, 2) and the origin is sqrt(5).
 * The Euclidean distance between (1, 3) and the origin is sqrt(10).
 * Since sqrt(5) < sqrt(10), therefore (1, 2) is closer to the origin.
 * Example 2:
 *
 * Input: point = [[1, 3], [3, 4], [2, -1]], K = 2
 * Output: [[1, 3], [2, -1]]
 */

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int distFromOrigin() {
        return ((x * x) + (y * y));
    }
}

public class KclosetPoints {
    public static List<Point> findClosestPoints(Point[] points, int k) {

        PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(points.length, (p1, p2) -> p2.distFromOrigin() - p1.distFromOrigin());

        // put first 'k' points in the max heap
        for (int i = 0; i < k; i++) {
            maxHeap.add(points[i]);
        }

        // go through the remaining points of the input array, if a point is closer to the
        // origin than the top point of the max-heap, remove the top point from heap and add
        // the point from the input array
        for (int i = k; i < points.length; i++) {
            if (points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) {
                maxHeap.poll();
                maxHeap.add(points[i]);
            }
        }
        return new ArrayList<>(maxHeap);
    }
}
