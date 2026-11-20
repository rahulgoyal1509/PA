package Tree;

import java.util.ArrayList;
import java.util.List;

public class Postorder_Traversal {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        visit(root, result);
        return result;
    }

    private static void visit(Node root, List<Integer> result) {
        if (root == null) return;
        visit(root.left, result);
        visit(root.right, result);
        result.add(root.val);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        System.out.println(postorder(root));
    }
}
