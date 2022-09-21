package matrixTraversal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem Statement
 *
 * You are given a 2D matrix containing only 1s (land) and 0s (water).
 *
 * An island is a connected set of 1s (land) and is surrounded by either an edge or 0s (water). Each cell is considered
 * connected to other cells horizontally or vertically (not diagonally).
 *
 * Two islands are considered the same if and only if they can be translated (not rotated or reflected) to equal each other.
 *
 * Write a function to find the number of distinct islands in the given matrix.
 *
 * Example 1
 * Input: matrix =
 *             { 1, 1, 0, 1, 1 },
 *             { 1, 1, 0, 1, 1 },
 *             { 0, 0, 0, 0, 0 },
 *             { 0, 1, 1, 0, 1 },
 *             { 0, 1, 1, 0, 1 }
 * Output: 2
 * Explanation: There are four islands in the given matrix, but three of them are the same; hence, there are only two distinct islands.
 *
 * Example 2
 *
 * Input: matrix =
 *             { 1, 1, 0, 1 },
 *             { 0, 1, 1, 0 },
 *             { 0, 0, 0, 0 },
 *             { 1, 1, 0, 0 },
 *             { 0, 1, 1, 0 }
 * Output: 2
 * Explanation: There are three islands in the given matrix, but two of them are the same; hence, there are only two distinct islands.
 */
public class DistinctIslands {

    public static int countDistinct(int[][] matrix) {
        Set<String> uniqueIsland = new HashSet<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                StringBuilder path = new StringBuilder();
                if (matrix[row][col] == 1) {
                    // found an island. need to find traversal order so it can be used to find distinct island
                    dfs(matrix, row, col, path, "O");
                    uniqueIsland.add(path.toString());
                }
            }
        }
        return uniqueIsland.size();
    }

    static void dfs(int[][] matrix, int row, int col, StringBuilder path, String direction) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return; // invalid cell
        }
        if (matrix[row][col] == 0) {
            return; // water or already visited.
        }
        matrix[row][col] = 0; // visit cell
        path.append(direction);
        dfs(matrix, row - 1, col, path, "U"); // up
        dfs(matrix, row + 1, col, path, "D"); // down
        dfs(matrix, row, col + 1, path, "R"); // right
        dfs(matrix, row, col - 1, path, "L"); // left
    }

    @Test
    void test1() {
        int[][] matrix = new int[][]{
                { 1, 1, 0, 1, 1 },
                { 1, 1, 0, 1, 1 },
                { 0, 0, 0, 0, 0 },
                { 0, 1, 1, 0, 1 },
                { 0, 1, 1, 0, 1 }
        };

        Assertions.assertEquals(2, countDistinct(matrix));
    }

    @Test
    void test2() {
        int[][] matrix = new int[][]{
                { 1, 1, 0, 1 },
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 0 }
        };

        Assertions.assertEquals(2, countDistinct(matrix));
    }
}
