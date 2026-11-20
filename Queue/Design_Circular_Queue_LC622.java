public class Design_Circular_Queue_LC622 {
    static class MyCircularQueue {
        private final int[] queue;
        private int head = -1;
        private int tail = -1;
        private int size;

        public MyCircularQueue(int k) {
            queue = new int[k];
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            if (isEmpty()) {
                head = 0;
            }
            tail = (tail + 1) % queue.length;
            queue[tail] = value;
            size++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            if (head == tail) {
                head = tail = -1;
            } else {
                head = (head + 1) % queue.length;
            }
            size--;
            return true;
        }

        public int Front() {
            return isEmpty() ? -1 : queue[head];
        }

        public int Rear() {
            return isEmpty() ? -1 : queue[tail];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == queue.length;
        }
    }

    public static void main(String[] args) {
        MyCircularQueue queue = new MyCircularQueue(3);
        System.out.println(queue.enQueue(1));
        System.out.println(queue.enQueue(2));
        System.out.println(queue.enQueue(3));
        System.out.println(queue.enQueue(4));
        System.out.println(queue.Rear());
        System.out.println(queue.deQueue());
    }
}
