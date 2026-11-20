import java.util.ArrayDeque;
import java.util.Queue;

public class Find_Winner_Circular_Game_LC1823 {
    public static int findTheWinner(int n, int k) {
        Queue<Integer> players = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            players.offer(i);
        }
        while (players.size() > 1) {
            for (int i = 1; i < k; i++) {
                players.offer(players.poll());
            }
            players.poll();
        }
        return players.peek();
    }

    public static void main(String[] args) {
        System.out.println(findTheWinner(5, 2));
        System.out.println(findTheWinner(6, 5));
    }
}
