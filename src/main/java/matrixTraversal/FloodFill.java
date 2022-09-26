package matrixTraversal;

import java.util.Arrays;

/**
 * Problem Statement
 *
 * Any image can be represented by a 2D integer array (i.e., a matrix) where each cell represents the pixel value of the
 * image.
 *Flood fill algorithm takes a starting cell (i.e., a pixel) and a color. The given color is applied to all horizontally
 *  and vertically connected cells with the same color as that of the starting cell. Recursively, the algorithm fills
 *  cells with the new color until it encounters a cell with a different color than the starting cell.
 *
 * Given a matrix, a starting cell, and a color, flood fill the matrix.
 */
public class FloodFill {

    public static int[][] floodFill(int[][] matrix, int x, int y, int newColor) {
       /* for ( int i = 0; i< matrix.length ; i ++){
            for ( int j = 0; j < matrix[0].length; j ++){

            }
        }*/

        floodFilldfs(matrix, x, y, matrix[x][y], newColor);

        return matrix;
    }

    private static void floodFilldfs(int[][] matrix, int row, int col, int existingColor, int newColor) {

        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            // invalid cell outside the boundary of matrix.
            return;
        }
        if (matrix[row][col] != existingColor) {
            return;
        }

        matrix[row][col] = newColor;

        floodFilldfs(matrix, row - 1, col, existingColor, newColor); // upper cell
        floodFilldfs(matrix, row + 1, col, existingColor, newColor); // lower cell
        floodFilldfs(matrix, row, col - 1, existingColor, newColor); // left cell
        floodFilldfs(matrix, row, col + 1, existingColor, newColor); // right cell
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(FloodFill.floodFill(
                new int[][] {
                        { 0, 1, 1, 1, 0 },
                        { 0, 0, 0, 1, 1 },
                        { 0, 1, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }, 1, 3, 2)));

        System.out.println(Arrays.deepToString(FloodFill.floodFill(
                new int[][] {
                        { 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 0, 1, 0, 0 },
                        { 0, 0, 1, 0, 0 }
                }, 3, 2, 5)));
    }
}
