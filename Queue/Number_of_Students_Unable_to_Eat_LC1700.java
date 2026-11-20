import java.util.ArrayDeque;
import java.util.Queue;

public class Number_of_Students_Unable_to_Eat_LC1700 {
    public static int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int student : students) {
            queue.offer(student);
        }
        int index = 0;
        int skipped = 0;
        while (!queue.isEmpty() && skipped < queue.size()) {
            if (queue.peek() == sandwiches[index]) {
                queue.poll();
                index++;
                skipped = 0;
            } else {
                queue.offer(queue.poll());
                skipped++;
            }
        }
        return queue.size();
    }

    public static void main(String[] args) {
        System.out.println(countStudents(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1}));
    }
}
