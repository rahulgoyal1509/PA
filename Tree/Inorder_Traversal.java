package Tree;

import java.util.ArrayList;
import java.util.List;

public class Inorder_Traversal {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static List<Integer> inorder(Node root) {
        List<Integer> result = new ArrayList<>();
        visit(root, result);
        return result;
    }

    private static void visit(Node root, List<Integer> result) {
        if (root == null) return;
        visit(root.left, result);
        result.add(root.val);
        visit(root.right, result);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        System.out.println(inorder(root));
    }
}
