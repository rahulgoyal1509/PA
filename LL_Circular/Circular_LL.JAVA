package LL_Circular;

public class Circular_LL {

    static class Node {
        int data;
        Node next;

        Node(int val) {
            data = val;
            next = null;
        }
    }

    static class CircularLL {
        Node head;
        Node tail;

        CircularLL() {
            head = tail = null;
        }

        void pushFront(int val) {
            Node newNode = new Node(val);
            if (head == null) {
                head = tail = newNode;
                tail.next = head;
                return;
            }
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }

        void pushBack(int val) {
            Node newNode = new Node(val);
            if (head == null) {
                head = tail = newNode;
                tail.next = head;
                return;
            }
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }

        void popFront() {
            if (head == null) {
                return;
            }
            if (head == tail) {
                head = tail = null;
                return;
            }
            head = head.next;
            tail.next = head;
        }

        void popBack() {
            if (head == null) {
                return;
            }
            if (head == tail) {
                head = tail = null;
                return;
            }
            Node temp = head;
            while (temp.next != tail) {
                temp = temp.next;
            }
            tail = temp;
            tail.next = head;
        }

        void insert(int val, int pos) {
            if (pos <= 0) {
                System.out.println("Invalid position");
                return;
            }
            if (pos == 1) {
                pushFront(val);
                return;
            }

            Node temp = head;
            for (int i = 1; i < pos - 1 && temp != null; i++) {
                temp = temp.next;
                if (temp == head) {
                    System.out.println("Invalid position");
                    return;
                }
            }
            if (temp == null || temp.next == head && pos > countNodes() + 1) {
                System.out.println("Invalid position");
                return;
            }

            Node newNode = new Node(val);
            newNode.next = temp.next;
            temp.next = newNode;
            if (temp == tail) {
                tail = newNode;
            }
            tail.next = head;
        }

        void deleteNode(int k) {
            if (head == null || k <= 0) {
                return;
            }
            if (k == 1) {
                popFront();
                return;
            }

            Node prev = head;
            for (int i = 1; i < k - 1; i++) {
                prev = prev.next;
                if (prev == head) {
                    return;
                }
            }
            if (prev.next == head) {
                return;
            }

            Node toDelete = prev.next;
            prev.next = toDelete.next;
            if (toDelete == tail) {
                tail = prev;
            }
            tail.next = head;
        }

        int countNodes() {
            if (head == null) {
                return 0;
            }
            int count = 0;
            Node temp = head;
            do {
                count++;
                temp = temp.next;
            } while (temp != head);
            return count;
        }

        boolean search(int key) {
            if (head == null) {
                return false;
            }
            Node temp = head;
            do {
                if (temp.data == key) {
                    return true;
                }
                temp = temp.next;
            } while (temp != head);
            return false;
        }

        int findMax() {
            if (head == null) {
                return Integer.MIN_VALUE;
            }
            int max = head.data;
            Node temp = head.next;
            while (temp != head) {
                max = Math.max(max, temp.data);
                temp = temp.next;
            }
            return max;
        }

        int findMiddle() {
            if (head == null) {
                return Integer.MIN_VALUE;
            }
            Node slow = head;
            Node fast = head;
            while (fast.next != head && fast.next.next != head) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow.data;
        }

        void reverse() {
            if (head == null || head == tail) {
                return;
            }

            Node oldHead = head;
            Node prev = tail;
            Node curr = head;
            int size = countNodes();
            for (int i = 0; i < size; i++) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            tail = oldHead;
            head = prev;
            tail.next = head;
        }

        void printReverse() {
            if (head == null) {
                System.out.println();
                return;
            }
            printReverse(head);
            System.out.println();
        }

        private void printReverse(Node curr) {
            if (curr.next == head) {
                System.out.print(curr.data + " ");
                return;
            }
            printReverse(curr.next);
            System.out.print(curr.data + " ");
        }

        void display() {
            if (head == null) {
                System.out.println();
                return;
            }
            Node temp = head;
            do {
                System.out.print(temp.data + " ");
                temp = temp.next;
            } while (temp != head);
            System.out.println();
        }

        CircularLL[] splitHalves() {
            CircularLL[] result = { new CircularLL(), new CircularLL() };
            if (head == null) {
                return result;
            }
            if (head == tail) {
                result[0].pushBack(head.data);
                return result;
            }

            Node slow = head;
            Node fast = head;
            while (fast.next != head && fast.next.next != head) {
                slow = slow.next;
                fast = fast.next.next;
            }

            Node secondHead = slow.next;
            if (fast.next != head) {
                fast = fast.next;
            }
            fast.next = slow.next;
            slow.next = head;

            Node temp = head;
            do {
                result[0].pushBack(temp.data);
                temp = temp.next;
            } while (temp != head);

            temp = secondHead;
            do {
                result[1].pushBack(temp.data);
                temp = temp.next;
            } while (temp != secondHead);
            return result;
        }

        int josephus(int k) {
            if (head == null || k <= 0) {
                return -1;
            }

            Node curr = head;
            Node prev = tail;
            while (curr != curr.next) {
                for (int i = 1; i < k; i++) {
                    prev = curr;
                    curr = curr.next;
                }
                prev.next = curr.next;
                curr = curr.next;
            }

            head = tail = curr;
            tail.next = head;
            return curr.data;
        }
    }

    public static void main(String[] args) {
        CircularLL cll = new CircularLL();
        cll.pushBack(1);
        cll.pushBack(2);
        cll.pushBack(3);
        cll.pushBack(4);
        cll.pushBack(5);

        cll.pushFront(0);
        cll.insert(10, 3);
        cll.deleteNode(2);
        cll.popFront();
        cll.popBack();

        System.out.print("List: ");
        cll.display();
        System.out.println("Count: " + cll.countNodes());
        System.out.println("Search 3: " + cll.search(3));
        System.out.println("Max: " + cll.findMax());
        System.out.println("Middle: " + cll.findMiddle());

        System.out.print("Reverse print: ");
        cll.printReverse();
        cll.reverse();
        System.out.print("Reversed: ");
        cll.display();

        CircularLL[] halves = cll.splitHalves();
        System.out.print("First half: ");
        halves[0].display();
        System.out.print("Second half: ");
        halves[1].display();

        CircularLL game = new CircularLL();
        game.pushBack(1);
        game.pushBack(2);
        game.pushBack(3);
        game.pushBack(4);
        game.pushBack(5);
        System.out.println("Josephus winner: " + game.josephus(2));
    }
}
