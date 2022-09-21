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
 * A closed island is an island that is totally surrounded by 0s (i.e., water). This means all horizontally and vertically connected cells of a closed island are water. This also means that, by definition, a closed island can't touch an edge (as then the edge cells are not connected to any water cell).
 *
 * Write a function to find the number of closed islands in the given matrix.
 *
 * Example 1
 * Input: matrix =
 *             { 1, 1, 0, 0, 0 },
 *             { 0, 1, 0, 0, 0 },
 *             { 0, 0, 1, 1, 0 },
 *             { 0, 1, 1, 0, 0 },
 *             { 0, 0, 0, 0, 0 }
 * Output: 1
 */
public class NbrOfClosedIslands {
    public static int countClosedIslands(int[][] matrix) {
        int answer = 0;
        int rows = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    // we found an island. Need to find out if it is closed or not
                   if(dfs(matrix, i, j)){
                       answer++;
                   }
                }
            }
        }
        return answer;
    }

    private static boolean dfs(int[] [] matrix, int row, int col) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return false; // return if it is not valid cell.
        }
        if (matrix[row][col] == 0) {
            return true;// if water or visited return;
        }

        boolean isClosed = true;
        matrix[row][col] = 0; // marking the cell visited;
        isClosed &= dfs(matrix, row - 1, col); // upper cell
        isClosed &= dfs(matrix, row + 1, col); // lower cell
        isClosed &= dfs(matrix, row, col - 1); // left cell
        isClosed &= dfs(matrix, row, col + 1); // right cell
        return isClosed;
    }

    @Test
    void test1(){
        int[][] matrix = new int[][]{
                { 1, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 0 },
                { 0, 0, 1, 1, 0 },
                { 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0 }
        };

        Assertions.assertEquals(1,countClosedIslands(matrix));
    }
    @Test
    void test2(){
        int[][] matrix = new int[][]{
                { 0, 0, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 0 }
        };

        Assertions.assertEquals(2,countClosedIslands(matrix));
    }
}
