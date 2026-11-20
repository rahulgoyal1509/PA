package Tree;

import java.util.ArrayList;
import java.util.List;

public class Inorder_Traversal_List_LC94 {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static List<Integer> inorderTraversal(Node root) {
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
        root.right = new Node(2);
        root.right.left = new Node(3);
        System.out.println(inorderTraversal(root));
    }
}
