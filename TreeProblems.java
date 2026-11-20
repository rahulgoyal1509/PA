import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeProblems {
    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private static void preorder(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    public static List<Integer> inorder(Node root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private static void inorder(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    public static List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private static void postorder(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }

    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    public static int nodeCount(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static boolean isBalanced(Node root) {
        return heightBalanced(root) != -1;
    }

    private static int heightBalanced(Node root) {
        if (root == null) {
            return 0;
        }
        int left = heightBalanced(root.left);
        if (left == -1) {
            return -1;
        }
        int right = heightBalanced(root.right);
        if (right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }

    public static boolean isComplete(Node root) {
        if (root == null) {
            return true;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean missingChild = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left == null) {
                missingChild = true;
            } else {
                if (missingChild) {
                    return false;
                }
                queue.offer(node.left);
            }
            if (node.right == null) {
                missingChild = true;
            } else {
                if (missingChild) {
                    return false;
                }
                queue.offer(node.right);
            }
        }
        return true;
    }

    public static List<List<Integer>> zigzag(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (leftToRight) {
                    level.add(node.val);
                } else {
                    level.add(0, node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
            leftToRight = !leftToRight;
        }
        return result;
    }

    public static Node constructFromPreorderInorder(int[] preorder, int[] inorder) {
        java.util.Map<Integer, Integer> positions = new java.util.HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            positions.put(inorder[i], i);
        }
        return construct(preorder, 0, preorder.length - 1, positions, 0, inorder.length - 1);
    }

    private static Node construct(int[] preorder, int preLeft, int preRight,
                                  java.util.Map<Integer, Integer> positions,
                                  int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        Node root = new Node(preorder[preLeft]);
        int rootIndex = positions.get(root.val);
        int leftSize = rootIndex - inLeft;
        root.left = construct(preorder, preLeft + 1, preLeft + leftSize,
                              positions, inLeft, rootIndex - 1);
        root.right = construct(preorder, preLeft + leftSize + 1, preRight,
                               positions, rootIndex + 1, inRight);
        return root;
    }

    public static int sumNodesOnLongestPath(Node root) {
        int[] answer = {0, 0};
        longest(root, 1, 0, answer);
        return answer[1];
    }

    private static void longest(Node root, int depth, int sum, int[] answer) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (root.left == null && root.right == null) {
            if (depth > answer[0] || depth == answer[0] && sum > answer[1]) {
                answer[0] = depth;
                answer[1] = sum;
            }
            return;
        }
        longest(root.left, depth + 1, sum, answer);
        longest(root.right, depth + 1, sum, answer);
    }

    public static void main(String[] args) {
        Node root = new Node(1,
                new Node(2, new Node(4), new Node(5)),
                new Node(3, null, new Node(6)));
        System.out.println("Preorder: " + preorder(root));
        System.out.println("Inorder: " + inorder(root));
        System.out.println("Postorder: " + postorder(root));
        System.out.println("Level order: " + levelOrder(root));
        System.out.println("Zigzag: " + zigzag(root));
        System.out.println("Count: " + nodeCount(root));
        System.out.println("Height: " + height(root));
        System.out.println("Balanced: " + isBalanced(root));
        System.out.println("Complete: " + isComplete(root));
        Node constructed = constructFromPreorderInorder(new int[]{3, 9, 20, 15, 7},
                                                        new int[]{9, 3, 15, 20, 7});
        System.out.println("Constructed inorder: " + inorder(constructed));
        System.out.println("Longest path sum: " + sumNodesOnLongestPath(root));
    }
}
