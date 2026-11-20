package LL_Singly;

import java.util.HashMap;
import java.util.Map;

public class LinkedListProblems {
    static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
        }
    }

    public static Node reverseList(Node head) {
        Node previous = null;
        while (head != null) {
            Node next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        return previous;
    }

    public static Node reverseBetween(Node head, int left, int right) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node before = dummy;
        for (int i = 1; i < left; i++) {
            before = before.next;
        }
        Node previous = before;
        Node current = before.next;
        for (int i = left; i <= right; i++) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        Node firstReversed = before.next;
        before.next = previous;
        firstReversed.next = current;
        return dummy.next;
    }

    public static Node reverseKGroup(Node head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        Node dummy = new Node(0);
        dummy.next = head;
        Node before = dummy;
        while (true) {
            Node end = before;
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            Node nextGroup = end.next;
            Node previous = nextGroup;
            Node current = before.next;
            for (int i = 0; i < k; i++) {
                Node next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            Node first = before.next;
            before.next = previous;
            before = first;
        }
        return dummy.next;
    }

    public static boolean hasCycle(Node head) {
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

    public static Node detectCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                Node entry = head;
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry;
            }
        }
        return null;
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node second = reverseList(slow.next);
        Node left = head;
        while (second != null) {
            if (left.val != second.val) {
                return false;
            }
            left = left.next;
            second = second.next;
        }
        return true;
    }

    public static Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node fast = dummy;
        Node slow = dummy;
        for (int i = 0; i < n; i++) {
            if (fast == null) {
                return head;
            }
            fast = fast.next;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        if (slow.next != null) {
            slow.next = slow.next.next;
        }
        return dummy.next;
    }

    public static Node swapPairs(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node previous = dummy;
        while (previous.next != null && previous.next.next != null) {
            Node first = previous.next;
            Node second = first.next;
            first.next = second.next;
            second.next = first;
            previous.next = second;
            previous = first;
        }
        return dummy.next;
    }

    public static Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int length = 1;
        Node tail = head;
        while (tail.next != null) {
            length++;
            tail = tail.next;
        }
        k %= length;
        if (k == 0) {
            return head;
        }
        tail.next = head;
        Node newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }
        Node newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }

    public static Node partition(Node head, int x) {
        Node beforeDummy = new Node(0);
        Node afterDummy = new Node(0);
        Node before = beforeDummy;
        Node after = afterDummy;
        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null;
        before.next = afterDummy.next;
        return beforeDummy.next;
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> copies = new HashMap<>();
        Node current = head;
        while (current != null) {
            copies.put(current, new Node(current.val));
            current = current.next;
        }
        current = head;
        while (current != null) {
            Node copy = copies.get(current);
            copy.next = copies.get(current.next);
            copy.random = copies.get(current.random);
            current = current.next;
        }
        return copies.get(head);
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        print(reverseList(a));

        Node one = new Node(1);
        one.next = new Node(2);
        one.next.next = new Node(3);
        one.next.next.next = new Node(4);
        print(reverseKGroup(one, 2));

        Node cycle = new Node(3);
        cycle.next = new Node(2);
        cycle.next.next = new Node(0);
        cycle.next.next.next = new Node(-4);
        cycle.next.next.next.next = cycle.next;
        System.out.println("Cycle start: " + detectCycle(cycle).val);
    }
}
