public class Design_Circular_Deque_LC641 {
    static class MyCircularDeque {
        private final int[] data;
        private int front;
        private int rear;
        private int size;

        public MyCircularDeque(int k) {
            data = new int[k];
            front = 0;
            rear = k - 1;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            front = (front - 1 + data.length) % data.length;
            data[front] = value;
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            rear = (rear + 1) % data.length;
            data[rear] = value;
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % data.length;
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            rear = (rear - 1 + data.length) % data.length;
            size--;
            return true;
        }

        public int getFront() {
            return isEmpty() ? -1 : data[front];
        }

        public int getRear() {
            return isEmpty() ? -1 : data[rear];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == data.length;
        }
    }

    public static void main(String[] args) {
        MyCircularDeque deque = new MyCircularDeque(3);
        System.out.println(deque.insertLast(1));
        System.out.println(deque.insertLast(2));
        System.out.println(deque.insertFront(3));
        System.out.println(deque.getRear());
    }
}
