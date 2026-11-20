package Tree;

import java.util.ArrayList;
import java.util.List;

public class Preorder_Traversal_List_LC144 {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static List<Integer> preorderTraversal(Node root) {
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
        root.right = new Node(2);
        root.right.left = new Node(3);
        System.out.println(preorderTraversal(root));
    }
}
