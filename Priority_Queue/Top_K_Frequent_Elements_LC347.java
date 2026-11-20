import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Top_K_Frequent_Elements_LC347 {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int value : nums) {
            frequency.put(value, frequency.getOrDefault(value, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> heap =
                new PriorityQueue<>((a, b) ->
                        Integer.compare(a.getValue(), b.getValue()));
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            heap.offer(entry);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] result = new int[k];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : heap) {
            result[index++] = entry.getKey();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        System.out.println(java.util.Arrays.toString(result));
    }
}
