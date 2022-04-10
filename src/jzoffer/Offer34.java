import java.util.*;

/**
 * @Author: ZhangLingRan
 * @Description: 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。 叶子节点 是指没有子节点的节点。
 * @DateTime: 2022/4/6 22:37
 */



public class Offer34 {


    /**
     * 返回所有路径，满足路径之和为 target
     *
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {


        List<List<Integer>> ret = new ArrayList<>();

        if (root == null) {
            return ret;
        }

        Stack<TreeNode> stack = new Stack<>();
        Deque<Integer> path = new ArrayDeque<>();

        stack.push(root);
        path.addLast(root.val);
        int sum = 0;
        sum += root.val;
        // 使用栈完成先序遍历
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (sum > target) {
                continue;
            }
            if (sum == target && node.left == null && node.right == null) {
                ret.add(new ArrayList<>(path));
            }
            if (node.left != null && node.left.val != Integer.MAX_VALUE) {
                sum += node.left.val;
                path.addLast(node.left.val);
                stack.push(node.left);
            } else if (node.right != null && node.right.val != Integer.MAX_VALUE) {
                sum += node.right.val;
                path.addLast(node.right.val);
                stack.push(node.right);
            } else {
                node = stack.pop();
                sum -= node.val;
                node.val = Integer.MAX_VALUE;
                path.removeLast();
            }
        }

        return ret;
    }

    /**
     * 剑指Offer中的解法
     *
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> pathSum1(TreeNode root, int target) {


        List<List<Integer>> ret = new ArrayList<>();

        if (root == null) {
            return ret;
        }

        Deque<Integer> path = new ArrayDeque<>();
        findPath(root, target, path, 0, ret);

        return ret;
    }

    public void findPath(TreeNode root, int target, Deque<Integer> path, int sum, List<List<Integer>> ret) {
        sum += root.val;
        path.addLast(root.val);

        // 如果是叶子节点且路径和为输入值，将其保存到res
        boolean isLeaf = root.left == null && root.right == null;
        if (isLeaf && sum == target) {
            ret.add(new ArrayList<>(path));
        }

        // 如果不是叶子，那么遍历左右子树
        if (root.left != null) {
            findPath(root.left, target, path, sum, ret);
        }
        if (root.right != null) {
            findPath(root.right, target, path, sum, ret);
        }

        path.removeLast();
    }


    public static void main(String[] args) {
        Offer34 obj = new Offer34();

        TreeNode root = new TreeNode(5,null, null);
        TreeNode l4 = new TreeNode(4,null, null);
        TreeNode r8 = new TreeNode(8,null, null);
        TreeNode l11 = new TreeNode(11,null, null);
        TreeNode l7 = new TreeNode(7,null, null);
        TreeNode r2 = new TreeNode(2,null, null);
        TreeNode l13 = new TreeNode(13,null, null);
        TreeNode r4 = new TreeNode(4,null, null);
        TreeNode l5 = new TreeNode(5,null, null);
        TreeNode r1 = new TreeNode(1,null, null);
        root.left = l4;root.right = r8;l4.left=l11;l11.left=l7;l11.right=r2;
        r8.left=l13;r8.right=r4;r4.left=l5;r4.right=r1;
        int target = 22;
        obj.pathSum(root, target);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}