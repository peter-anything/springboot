import java.util.Stack;

public class SubTreeSum {
    public static int cal(TreeNode root) {
        if (root == null) return 0;

        int result = 0;
        Stack<TreeNode> stack = new Stack();
        TreeNode current = root;
        boolean isRootVisited = false;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (!stack.isEmpty()) {
                TreeNode tmp = stack.peek();
                if (tmp.left == null && tmp.right == null) {
                    int i = 0;
                    for (TreeNode node : stack) {
                        result += Math.pow(10, stack.size() - i - 1) * node.val;
                        i ++;
                    }
                }

                if (tmp != root) {
                    tmp = stack.pop();
                    current = tmp.right;
                } else if (tmp == root && !isRootVisited){
                    isRootVisited = true;
                    current = root.right;
                } else {
                    break;
                    // 算法终止
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println(cal(root));
    }
}
