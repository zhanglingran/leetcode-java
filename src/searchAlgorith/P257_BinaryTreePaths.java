package searchAlgorith;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author: ZhangLingRan
 * @Description: 给你一个二叉树的根节点 root ，按任意顺序 ，返回所有从根节点到叶子节点的路径。 叶子节点 是指没有子节点的节点。
 * @DateTime: 2021/11/12 22:22
 */
public class P257_BinaryTreePaths {


    /**
     * 使用队列来实现回溯
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode1 root) {

        List<String> res = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        dfs(root, res, deque);

        return res;
    }

    private void dfs(TreeNode1 root, List<String> res, Deque<Integer> deque) {

        deque.add(root.val);

        // 是叶子节点的时候
        if (root.left == null && root.right == null) {
            StringBuilder builder = new StringBuilder();

            for (Integer val : deque) {
                builder.append(val + "->");
            }
            res.add(builder.substring(0, builder.length()-2));
            return;
        }

        // 不是叶子节点的时候
        if (root.left != null) {
            dfs(root.left, res, deque);
            deque.removeLast();
        }
        if (root.right != null) {
            dfs(root.right, res, deque);
            deque.removeLast();
        }

    }

}


class TreeNode {
    int val;
    TreeNode1 left;
    TreeNode1 right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode1 left, TreeNode1 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
