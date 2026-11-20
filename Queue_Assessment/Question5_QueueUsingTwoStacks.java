import java.util.ArrayDeque;
import java.util.Deque;

public class Question5_QueueUsingTwoStacks {
    private final Deque<Integer> input = new ArrayDeque<>();
    private final Deque<Integer> output = new ArrayDeque<>();

    public void enqueue(int value) {
        input.push(value);
    }

    public Integer dequeue() {
        moveInputToOutput();
        return output.isEmpty() ? null : output.pop();
    }

    private void moveInputToOutput() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
    }

    public static void main(String[] args) {
        Question5_QueueUsingTwoStacks queue =
                new Question5_QueueUsingTwoStacks();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
