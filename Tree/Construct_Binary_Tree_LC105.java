package Tree;

import java.util.HashMap;
import java.util.Map;

public class Construct_Binary_Tree_LC105 {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static Node buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> positions = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            positions.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1,
                positions, 0, inorder.length - 1);
    }

    private static Node build(int[] preorder, int preLeft, int preRight,
                              Map<Integer, Integer> positions,
                              int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) return null;
        Node root = new Node(preorder[preLeft]);
        int rootIndex = positions.get(root.val);
        int leftSize = rootIndex - inLeft;
        root.left = build(preorder, preLeft + 1, preLeft + leftSize,
                positions, inLeft, rootIndex - 1);
        root.right = build(preorder, preLeft + leftSize + 1, preRight,
                positions, rootIndex + 1, inRight);
        return root;
    }

    private static void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        Node root = buildTree(new int[]{3, 9, 20, 15, 7},
                              new int[]{9, 3, 15, 20, 7});
        printInorder(root);
        System.out.println();
    }
}
