package matrixTraversal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Problem Statement
 *
 * Given a 2D array (i.e., a matrix) containing only 1s (land) and 0s (water), find the biggest island in it. Write a
 * function to return the area of the biggest island. An island is a connected set of 1s (land) and is surrounded by
 * either an edge or 0s (water). Each cell is considered.connected to other cells horizontally or vertically (not diagonally).
 *
 * Example 1
 *  Input: matrix =
 *             { 1, 1, 1, 0, 0 },
 *             { 0, 1, 0, 0, 1 },
 *             { 0, 0, 1, 1, 0 },
 *             { 0, 1, 1, 0, 0 },
 *             { 0, 0, 1, 0, 0 }
 *

 * Output: 5
 *
 *  * Example 2
 *  *  Input: matrix =
         { 1, 1, 1, 0, 0 },
         { 0, 1, 0, 0, 1 },
         { 0, 0, 1, 1, 0 },
         { 0, 0, 1, 0, 0 },
         { 0, 0, 1, 0, 0 }
 *
 *  * Output: 4
 */
public class BiggestIsland {

    public static  int find(int [][] matrix) {
        int answer = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    // we found an island
                    answer = Math.max(answer, findAreaDFS(matrix, row, col));
                }
            }
        }
        return answer;
    }

    static int findAreaDFS(int [][] matrix, int row, int col){
        if(row < 0 || row >= matrix.length || col <0 || col >= matrix[0].length){
            return 0; // return 0 if it is invalid cell
        }

        if(matrix[row][col] ==0){
            return 0; // return 0 if it is water or already visited
        }
        matrix[row][col] =0;// visit the cell.
        int area =1;
        area+= findAreaDFS(matrix,row-1,col); // upper cell
        area+= findAreaDFS(matrix,row+1,col); // lower cell
        area+= findAreaDFS(matrix,row,col-1); // left cell
        area+= findAreaDFS(matrix,row,col+1); // right cell

        return area;
    }

    @Test
    void test1() {
        int[][] matrix = new int[][]{
                { 1, 1, 1, 0, 0 },
                { 0, 1, 0, 0, 1 },
                { 0, 0, 1, 1, 0 },
                { 0, 1, 1, 0, 0 },
                { 0, 0, 1, 0, 0 }
        };
        Assertions.assertEquals(5,find(matrix));
    }

    @Test
    void test2(){
        int[][] matrix = new int[][]{
                { 1, 1, 1, 0, 0 },
                { 0, 1, 0, 0, 1 },
                { 0, 0, 1, 1, 0 },
                { 0, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 0 }
        };
        Assertions.assertEquals(4,find(matrix));
    }
}
