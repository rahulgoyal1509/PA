import java.util.ArrayDeque;
import java.util.Queue;

public class Number_of_Recent_Calls_LC933 {
    static class RecentCounter {
        private final Queue<Integer> requests = new ArrayDeque<>();

        public int ping(int t) {
            requests.offer(t);
            while (!requests.isEmpty() && requests.peek() < t - 3000) {
                requests.poll();
            }
            return requests.size();
        }
    }

    public static void main(String[] args) {
        RecentCounter counter = new RecentCounter();
        System.out.println(counter.ping(1));
        System.out.println(counter.ping(100));
        System.out.println(counter.ping(3001));
        System.out.println(counter.ping(3002));
    }
}
