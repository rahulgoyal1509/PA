package LL_Singly;

public class Singly_LL {

    static class Node {
        int data;
        Node next;
        Node(int val) { data = val; next = null; }
    }

    static class LinkedList {
        private Node head;
        private Node tail;

        LinkedList() { head = null; tail = null; }
        // Java's garbage collector reclaims nodes automatically —
        // no destructor/free loop needed (unlike the original C++ ~LinkedList()).

        void push_front(int val) {
            Node newNode = new Node(val);
            if (head == null) {
                head = tail = newNode;
                return;
            }
            newNode.next = head;
            head = newNode;
        }

        void push_back(int val) {
            Node newNode = new Node(val);
            if (head == null) {
                head = tail = newNode;
                return;
            }
            tail.next = newNode;
            tail = newNode;
        }

        void pop_front() {
            if (head == null) {
                System.out.println("LL is empty");
                return;
            }
            if (head == tail) { // only one node
                head = tail = null;
                return;
            } else {
                head = head.next;
            }
        }

        void pop_back() {
            if (head == null) {
                System.out.println("LL is empty");
                return;
            }
            if (head == tail) {
                head = tail = null;
                return;
            }
            Node temp = head;
            while (temp.next != tail)
                temp = temp.next;
            tail = temp;
            tail.next = null;
        }

        void insertNode(int val, int pos) {
            if (pos <= 0) {
                System.out.println("Invalid pos");
                return;
            }
            if (pos == 1) {
                push_front(val);
                return;
            }
            Node temp = head;
            for (int i = 1; i < pos - 1; i++) {
                if (temp == null) { // position out of range
                    System.out.println("Invalid pos");
                    return;
                }
                temp = temp.next;
            }
            Node newNode = new Node(val);
            newNode.next = temp.next;
            temp.next = newNode;
            if (newNode.next == null) { // inserted at end -> update tail
                tail = newNode;
            }
        }

        void deleteNode(int k) {
            if (head == null || k <= 0)
                return;

            if (k == 1) {
                if (head == tail) { // single node
                    head = tail = null;
                } else {
                    head = head.next;
                }
                return;
            }

            Node prev = head;
            for (int i = 1; i < k - 1 && prev != null; ++i)
                prev = prev.next;

            if (prev == null || prev.next == null)
                return; // kth node doesn't exist

            Node toDelete = prev.next;
            prev.next = toDelete.next;

            if (toDelete == tail) { // deleted last node -> update tail
                tail = prev;
            }
        }

        int searchNode(int key) {
            Node temp = head;
            int idx = 1;
            while (temp != null) {
                if (temp.data == key)
                    return idx;
                temp = temp.next;
                idx++;
            }
            return -1;
        }

        void printAll() {
            Node temp = head;
            StringBuilder sb = new StringBuilder();
            while (temp != null) {
                sb.append(temp.data).append(" ");
                temp = temp.next;
            }
            System.out.println(sb.toString());
        }

        int countNodes() {
            int count = 0;
            Node temp = head;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            return count;
        }

        void reverse() {
            Node prev = null;
            Node curr = head;
            tail = head;
            while (curr != null) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            head = prev;
        }

        Node findMiddle() {
            Node slow = head;
            Node fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        boolean isPalindrome() {
            if (head == null || head == tail) {
                return true;
            }
            Node middle = findMiddle();
            Node firstHalf = head;
            Node secondHalf = reverseList(middle);
            Node copy = secondHalf;
            boolean result = true;
            while (secondHalf != null) {
                if (firstHalf.data != secondHalf.data) {
                    result = false;
                    break;
                }
                firstHalf = firstHalf.next;
                secondHalf = secondHalf.next;
            }
            reverseList(copy);
            return result;
        }

        private Node reverseList(Node node) {
            Node prev = null;
            while (node != null) {
                Node next = node.next;
                node.next = prev;
                prev = node;
                node = next;
            }
            return prev;
        }

        boolean hasCycle() {
            Node slow = head;
            Node fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    return true;
                }
            }
            return false;
        }

        void removeDuplicatesSorted() {
            Node curr = head;
            while (curr != null && curr.next != null) {
                if (curr.data == curr.next.data) {
                    curr.next = curr.next.next;
                } else {
                    curr = curr.next;
                }
            }
            Node temp = head;
            if (temp == null) {
                tail = null;
                return;
            }
            while (temp.next != null) {
                temp = temp.next;
            }
            tail = temp;
        }

        Node nthFromEnd(int n) {
            if (n <= 0) {
                return null;
            }
            Node first = head;
            Node second = head;
            for (int i = 0; i < n; i++) {
                if (first == null) {
                    return null;
                }
                first = first.next;
            }
            while (first != null) {
                first = first.next;
                second = second.next;
            }
            return second;
        }

        void removeNthFromEnd(int n) {
            Node dummy = new Node(0);
            dummy.next = head;
            Node fast = dummy;
            Node slow = dummy;
            for (int i = 0; i < n; i++) {
                if (fast != null) {
                    fast = fast.next;
                }
            }
            while (fast != null && fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
            if (slow.next != null) {
                slow.next = slow.next.next;
            }
            head = dummy.next;
            if (head == null) {
                tail = null;
            } else {
                Node temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                tail = temp;
            }
        }

        void reorderList() {
            if (head == null || head.next == null) {
                return;
            }
            Node slow = head;
            Node fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            Node second = reverseList(slow.next);
            slow.next = null;
            tail = slow;
            Node first = head;
            while (second != null) {
                Node nextFirst = first.next;
                Node nextSecond = second.next;
                first.next = second;
                second.next = nextFirst;
                first = nextFirst;
                second = nextSecond;
            }
        }

        Node partition(int x) {
            Node lessDummy = new Node(0);
            Node greaterDummy = new Node(0);
            Node less = lessDummy;
            Node greater = greaterDummy;
            Node curr = head;
            while (curr != null) {
                if (curr.data < x) {
                    less.next = curr;
                    less = less.next;
                } else {
                    greater.next = curr;
                    greater = greater.next;
                }
                curr = curr.next;
            }
            greater.next = null;
            less.next = greaterDummy.next;
            head = lessDummy.next;
            tail = head;
            while (tail != null && tail.next != null) {
                tail = tail.next;
            }
            return head;
        }

        void swapPairs() {
            Node dummy = new Node(0);
            dummy.next = head;
            Node prev = dummy;
            while (prev.next != null && prev.next.next != null) {
                Node first = prev.next;
                Node second = first.next;
                first.next = second.next;
                second.next = first;
                prev.next = second;
                prev = first;
            }
            head = dummy.next;
            Node temp = head;
            tail = temp;
            while (temp != null && temp.next != null) {
                temp = temp.next;
            }
            tail = temp;
        }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.push_front(1);
        ll.push_front(2);
        ll.push_front(3);
        ll.push_back(4);

        System.out.print("Before ops: ");
        ll.printAll(); // 3 2 1 4

        ll.pop_front();
        System.out.print("After pop_front: ");
        ll.printAll(); // 2 1 4

        ll.pop_back();
        System.out.print("After pop_back: ");
        ll.printAll(); // 2 1

        ll.insertNode(10, 2);
        System.out.print("After insertNode(10,1): ");
        ll.printAll(); // 2 10 1

        ll.deleteNode(1);
        System.out.print("After deleteNode(1): ");
        ll.printAll(); // 10 1

        ll.deleteNode(2);
        System.out.print("After deleteNode(2): ");
        ll.printAll(); // 10

        ll.deleteNode(1);
        System.out.print("After deleteNode(1): ");
        ll.printAll(); // (empty line)
    }
}
