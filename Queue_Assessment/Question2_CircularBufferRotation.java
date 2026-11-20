public class Question2_CircularBufferRotation {
    private final int[] buffer;
    private int front;
    private int rear;
    private int size;

    public Question2_CircularBufferRotation(int capacity) {
        buffer = new int[capacity];
        front = 0;
        rear = -1;
    }

    public boolean insert(int value) {
        if (size == buffer.length) {
            return false;
        }
        rear = (rear + 1) % buffer.length;
        buffer[rear] = value;
        size++;
        return true;
    }

    public Integer remove() {
        if (size == 0) {
            return null;
        }
        int value = buffer[front];
        front = (front + 1) % buffer.length;
        size--;
        return value;
    }

    public static void main(String[] args) {
        Question2_CircularBufferRotation buffer =
                new Question2_CircularBufferRotation(4);
        buffer.insert(1);
        buffer.insert(2);
        buffer.insert(3);
        System.out.println(buffer.remove());
        buffer.insert(4);
        buffer.insert(5);
        while (buffer.size > 0) {
            System.out.print(buffer.remove() + " ");
        }
        System.out.println();
    }
}
