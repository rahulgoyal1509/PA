import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Sliding_Window_Maximum_LC239 {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> indexes = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!indexes.isEmpty() && indexes.peekFirst() <= i - k) {
                indexes.pollFirst();
            }
            while (!indexes.isEmpty() && nums[indexes.peekLast()] <= nums[i]) {
                indexes.pollLast();
            }
            indexes.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[indexes.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
