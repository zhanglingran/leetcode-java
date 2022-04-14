import java.util.Stack;

/**
 * @Author: ZhangLingRan
 * @Description: TODO
 * @DateTime: 2022/4/12 9:27
 */
public class Offer36 {

    public Node treeToDoublyList(Node root) {

        // 使用非递归的方式中旬遍历二叉树，找到最左端的节点后，将其设为根节点
        Node head = null;
        Node cur = null;
        Stack<Node> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            Node top = stack.pop();
            root = top.right;

            if (head == null) {

                head = top;
                cur = head;
            } else {

                cur.right = top;
                top.left = cur;
                cur = top;
            }
        }

        head.left = cur;
        cur.right = head;

        return head;
    }

    public static void main(String[] args) {
        Offer36 obj = new Offer36();
        Node root = new Node(4);
        Node node2 = new Node(2);
        Node node1 = new Node(1);
        Node node3 = new Node(3);
        Node node5 = new Node(5);
        root.left = node2;
        root.right = node5;
        node2.left = node1;
        node2.right = node3;

        obj.treeToDoublyList(root);
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
