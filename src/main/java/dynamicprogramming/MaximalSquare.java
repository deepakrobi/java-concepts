package dynamicprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 *
 * Example 2:
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 *
 *Example 3:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */
public class MaximalSquare {
    int result = 0;
    Integer [][] cache;
    int totalRow;
    int totalColumn;
    public int maximalSquare(char[][] matrix) {
        cache = new Integer[matrix.length + 1][matrix[0].length + 1];
        totalRow = matrix.length;
        totalColumn = matrix[0].length;
        maximallength(matrix, 0, 0);
        return result * result;
    }

    private int maximallength(char[][] matrix, int row, int column) {
        if (row >= totalRow || column >= totalColumn) {
            return cache[row][column] = 0;
        }

        if (cache[row][column] != null) {
            return cache[row][column];
        }

        int down = maximallength(matrix, row + 1, column);
        int diagonal = maximallength(matrix, row + 1, column + 1);
        int right = maximallength(matrix, row, column + 1);

        if (matrix[row][column] == '1') {
            int length = 1 + Math.min(down, Math.min(diagonal, right));
            result = Math.max(result, length);
            return cache[row][column] = length;
        } else {
            return cache[row][column] = 0;
        }
    }

    @Test
    void test() {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        Assertions.assertEquals(4, new MaximalSquare().maximalSquare(matrix));

        char[][] matrix1 = {{'0', '1'}, {'1', '0'}};
        Assertions.assertEquals(1, new MaximalSquare().maximalSquare(matrix1));

        char[][] matrix2 = {{'1', '0'}, {'1', '1'}, {'1', '1'}};
        Assertions.assertEquals(4, new MaximalSquare().maximalSquare(matrix2));
    }
}
