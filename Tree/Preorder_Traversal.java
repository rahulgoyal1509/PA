package Tree;

import java.util.ArrayList;
import java.util.List;

public class Preorder_Traversal {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        visit(root, result);
        return result;
    }

    private static void visit(Node root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        visit(root.left, result);
        visit(root.right, result);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        System.out.println(preorder(root));
    }
}
