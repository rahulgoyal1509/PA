package HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Sort_Characters_By_Frequency_LC451 {
    public static String frequencySort(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char ch : s.toCharArray()) counts.put(ch, counts.getOrDefault(ch, 0) + 1);
        PriorityQueue<Character> heap = new PriorityQueue<>((a, b) ->
                Integer.compare(counts.get(b), counts.get(a)));
        heap.addAll(counts.keySet());
        StringBuilder result = new StringBuilder(s.length());
        while (!heap.isEmpty()) {
            char ch = heap.poll();
            for (int i = 0; i < counts.get(ch); i++) result.append(ch);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
    }
}
