package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class K_Closest_Points_to_Origin_LC973 {
    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) ->
                Long.compare(distance(b), distance(a)));
        for (int[] point : points) {
            heap.offer(point);
            if (heap.size() > k) heap.poll();
        }
        int[][] result = new int[heap.size()][];
        for (int i = 0; i < result.length; i++) result[i] = heap.poll();
        return result;
    }

    private static long distance(int[] point) {
        return (long) point[0] * point[0] + (long) point[1] * point[1];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(kClosest(
                new int[][]{{1, 3}, {-2, 2}, {5, 8}}, 2)));
    }
}
