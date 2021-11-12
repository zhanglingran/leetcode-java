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
public class BinaryTreePaths257AboutString {


    /**
     * 使用字符串来实现回溯
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode1 root) {

        List<String> res = new ArrayList<>();
        dfs(root, res, "");

        return res;
    }

    /**
     * 该方法将运行时间提高到1ms之内，击败了100%的java用户，那么需要注意的点就是：
     * 在Java开发过程中，尽最大的努力使用StringBuilder、StringBuffer类，因为String类的每次操作都会进行一次对象的创建。此外也尽量不要使用字符串的拼接操作。
     * @param root
     * @param res
     * @param path
     */
    private void dfs(TreeNode1 root, List<String> res, String path) {

        if (root != null) {
            StringBuilder pathSb = new StringBuilder(path);
            pathSb.append(root.val);
            // 是叶子节点的时候
            if (root.left == null && root.right == null) {
                res.add(pathSb.toString());
            }else {
                pathSb.append("->");
                dfs(root.left, res, pathSb.toString());
                dfs(root.right, res, pathSb.toString());
            }
        }

    }

}


class TreeNode1 {
    int val;
    TreeNode1 left;
    TreeNode1 right;

    TreeNode1() {
    }

    TreeNode1(int val) {
        this.val = val;
    }

    TreeNode1(int val, TreeNode1 left, TreeNode1 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
