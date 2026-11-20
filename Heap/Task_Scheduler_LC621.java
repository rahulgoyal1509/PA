package Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Task_Scheduler_LC621 {
    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char task : tasks) counts.put(task, counts.getOrDefault(task, 0) + 1);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        maxHeap.addAll(counts.values());
        int intervals = 0;
        while (!maxHeap.isEmpty()) {
            int cycle = n + 1;
            int executed = 0;
            int[] remaining = new int[cycle];
            while (cycle > 0 && !maxHeap.isEmpty()) {
                int count = maxHeap.poll() - 1;
                if (count > 0) remaining[executed++] = count;
                intervals++;
                cycle--;
            }
            for (int i = 0; i < executed; i++) maxHeap.offer(remaining[i]);
            if (maxHeap.isEmpty()) break;
            intervals += cycle;
        }
        return intervals;
    }

    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    }
}
