package matrixTraversal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Statement
 *
 * Given a 2D array (i.e., a matrix) containing only 1s (land) and 0s (water), count the number of islands in it.
 * An island is a connected set of 1s (land) and is surrounded by either an edge or 0s (water). Each cell is considered
 * connected to other cells horizontally or vertically (not diagonally).
 *
 * Example 1
 *
 * Input: matrix =
 *             { 0, 1, 1, 1, 0 },
 *             { 0, 0, 0, 1, 1 },
 *             { 0, 1, 1, 1, 0 },
 *             { 0, 1, 1, 0, 0 },
 *             { 0, 0, 0, 0, 0 }
 *
 * Output: 1
 *
 * Example 2
 * Input: matrix =
 *             { 1, 1, 1, 0, 0 },
 *             { 0, 1, 0, 0, 1 },
 *             { 0, 0, 1, 1, 0 },
 *             { 0, 0, 1, 0, 0 },
 *             { 0, 0, 1, 0, 0 }
 *
 *  * Output: 3
 */
public class NumberOfIslands {

    public static int countIslands(int [][] matrix) {
        int answer = 0;
        int rows = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) { // only if the cell is valid land
                    // we have found an island
                    answer++;
                    dfs(matrix, i, j);
                }
            }
        }
        return answer;
    }

    public static int countIslandsBFS(int [][] matrix) {
        int answer = 0;
        int rows = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) { // only if the cell is valid land
                    // we have found an island
                    answer++;
                    bfs(matrix, i, j);
                }
            }
        }
        return answer;
    }

    private static void dfs(int[] [] matrix, int row, int col) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return; // return if it is not valid cell.
        }

        if (matrix[row][col] == 0) {
            return;// if water or visited return;
        }

        matrix[row][col] = 0; // marking the cell visited;
        dfs(matrix, row - 1, col); // upper cell
        dfs(matrix, row + 1, col); // lower cell
        dfs(matrix, row, col - 1); // left cell
        dfs(matrix, row, col + 1); // right cell
    }

    private static void bfs(int[] [] matrix, int x, int y) {
        Queue<int[]> neighbors = new LinkedList<>();
        neighbors.add(new int[]{x, y});
        while (!neighbors.isEmpty()) {
            int [] neighbor =  neighbors.remove();
            int row = neighbor[0];
            int col = neighbor[1];

            if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
                continue;// continue if it is not a valid cell
            }
            if (matrix[row][col] == 0) {
                continue; // continue if it is water or already visited
            }
            matrix[row][col] = 0; // visit the cell
            neighbors.add(new int[]{row - 1, col}); // upper cell
            neighbors.add(new int[]{row + 1, col}); // lower cell
            neighbors.add(new int[]{row, col - 1}); // left cell
            neighbors.add(new int[]{row, col + 1}); // right cell
        }
    }

    @Test
    void test1() {
        int[][] matrix = new int[][]{
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 1},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0}
        };

        Assertions.assertEquals(1,countIslands(matrix));
    }
    @Test
    void test2() {
        int[][] matrix = new int[][]{
                { 1, 1, 1, 0, 0 },
                { 0, 1, 0, 0, 1 },
                { 0, 0, 1, 1, 0 },
                { 0, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 0 }
        };

        Assertions.assertEquals(3,countIslandsBFS(matrix));
    }
}
