package Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class Last_Stone_Weight_LC1046 {
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) heap.offer(stone);
        while (heap.size() > 1) {
            int first = heap.poll();
            int second = heap.poll();
            if (first != second) heap.offer(first - second);
        }
        return heap.isEmpty() ? 0 : heap.peek();
    }

    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
    }
}
