public class Circular_Queue_Implementation {
    static class CircularQueue {
        private final int[] data;
        private int front;
        private int rear;
        private int size;

        CircularQueue(int capacity) {
            data = new int[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }

        boolean enqueue(int value) {
            if (isFull()) {
                return false;
            }
            rear = (rear + 1) % data.length;
            data[rear] = value;
            size++;
            return true;
        }

        boolean dequeue() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % data.length;
            size--;
            return true;
        }

        int front() {
            return isEmpty() ? -1 : data[front];
        }

        int rear() {
            return isEmpty() ? -1 : data[rear];
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isFull() {
            return size == data.length;
        }
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(3);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue.dequeue());
        queue.enqueue(40);
        System.out.println(queue.front());
        System.out.println(queue.rear());
    }
}
