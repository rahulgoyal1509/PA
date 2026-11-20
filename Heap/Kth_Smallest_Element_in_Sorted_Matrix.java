package Heap;

import java.util.PriorityQueue;

public class Kth_Smallest_Element_in_Sorted_Matrix {
    public static int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int row = 0; row < Math.min(rows, k); row++) {
            heap.offer(new int[]{matrix[row][0], row, 0});
        }
        for (int count = 0; count < k; count++) {
            int[] current = heap.poll();
            if (count == k - 1) return current[0];
            int nextCol = current[2] + 1;
            if (nextCol < cols) {
                heap.offer(new int[]{matrix[current[1]][nextCol], current[1], nextCol});
            }
        }
        throw new IllegalArgumentException("k is outside the matrix element range");
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        System.out.println(kthSmallest(matrix, 8));
    }
}
