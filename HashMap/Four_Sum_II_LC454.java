package HashMap;

import java.util.HashMap;
import java.util.Map;

public class Four_Sum_II_LC454 {
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> pairSums = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) pairSums.merge(a + b, 1, Integer::sum);
        }
        int count = 0;
        for (int c : nums3) {
            for (int d : nums4) count += pairSums.getOrDefault(-(c + d), 0);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(fourSumCount(
                new int[]{1, 2}, new int[]{-2, -1},
                new int[]{-1, 2}, new int[]{0, 2}));
    }
}
