package matrixTraversal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Problem Statement
 *
 * You are given a 2D matrix containing only 1s (land) and 0s (water).
 *
 * An island is a connected set of 1s (land) and is surrounded by either an edge or 0s (water). Each cell is considered connected to other cells horizontally or vertically (not diagonally).
 *
 * There are no lakes on the island, so the water inside the island is not connected to the water around it. A cell is a square with a side length of 1..
 *
 * The given matrix has only one island, write a function to find the perimeter of that island.
 *
 * Example 1
 * Input: matrix =
 *         {1, 1, 0, 0, 0},
 *         {0, 1, 0, 0, 0},
 *         {0, 1, 0, 0, 0},
 *         {0, 1, 1, 0, 0},
 *         {0, 0, 0, 0, 0}}
 * Output: 14
 * Explanation: The boundary of the island constitute 14 sides.
 *
 * Example 2
 * Input: matrix =
 *             { 0, 0, 0, 0 },
 *             { 0, 1, 0, 0 },
 *             { 0, 1, 0, 0 },
 *             { 0, 1, 1, 0 },
 *             { 0, 1, 0, 0 }
 * Output: 12
 * Explanation: The boundary of the island constitute 12 sides.
 */
public class IslandPerimeter {
    public static int findPerimeter(int[][] matrix) {
        int answer = 0;
        int rows = matrix.length;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    // we found an island. Need to find perimeter and return it
                    return dfs(matrix, i, j,visited);
                }
            }
        }
        return answer;
    }

    private static int dfs(int[][] matrix, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return 1; // return 1 as it is outside edge
        }

        if(visited[row][col]){
            return 0; // we have already visited it, no need to count the edge again;
        }

        if (matrix[row][col] == 0) {
            return 1; // return 1 as we need to count 1 for water edges. this is a water cell and it has an edge with 1 ( island).
        }

        int edgeCount = 0;
        visited[row][col] = true; // mark the cell visited
        edgeCount += dfs(matrix, row - 1, col, visited); // upper cell
        edgeCount += dfs(matrix, row + 1, col, visited); // lower cell
        edgeCount += dfs(matrix, row, col - 1, visited); // left cell
        edgeCount += dfs(matrix, row, col + 1, visited); // right cell
        return edgeCount;
    }

    @Test
    void test1() {
        int[][] matrix = new int[][]{
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0}
        };

        Assertions.assertEquals(14, findPerimeter(matrix));
    }

    @Test
    void test2() {
        int[][] matrix = new int[][]{
                { 0, 0, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 1, 0, 0 }
        };

        Assertions.assertEquals(12, findPerimeter(matrix));
    }
}
