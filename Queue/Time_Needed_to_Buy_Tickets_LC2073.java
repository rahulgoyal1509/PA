import java.util.ArrayDeque;
import java.util.Queue;

public class Time_Needed_to_Buy_Tickets_LC2073 {
    public static int timeRequiredToBuy(int[] tickets, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < tickets.length; i++) {
            queue.offer(i);
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int person = queue.poll();
            tickets[person]--;
            time++;
            if (tickets[person] == 0 && person == k) {
                return time;
            }
            if (tickets[person] > 0) {
                queue.offer(person);
            }
        }
        return time;
    }

    public static void main(String[] args) {
        System.out.println(timeRequiredToBuy(new int[]{2, 3, 2}, 2));
    }
}
