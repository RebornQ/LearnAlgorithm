package coding_interviews.solution;

import util.aspects.annotations.RuntimeLogAnnotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by reborn on 2019-10-21 21:17
 *
 * 二叉树的镜像
 *
 * 题目描述：
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *
 * 输入描述:
 * 二叉树的镜像定义：
 *
 *     	  源二叉树
 *     	     8
 *     	   /  \
 *     	  6   10
 *     	 / \  / \
 *     	5  7 9 11
 *
 *     	镜像二叉树
 *     	     8
 *     	   /  \
 *     	  10   6
 *     	 / \  / \
 *     	11 9 7  5
 */
public class Offer018 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /*
     * 思路：
     * 尾递归遍历每一个结点，并使每一个结点的左右结点交换
     */
    public void Mirror(TreeNode root) {
        if (root == null) return;

        switchNode(root);

        if(root.left != null)
            Mirror(root.left);
        if(root.right != null)
            Mirror(root.right);
    }

    private void switchNode(TreeNode root) {

        TreeNode nodeTemp = root.left;
        root.left = root.right;
        root.right = nodeTemp;
    }

    public TreeNode createTree(TreeNode node, Integer data) {
        if (data < node.val) {
            if (node.left == null) {
                node.left = new TreeNode(data);
            } else {
                createTree(node.left, data);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(data);
            } else {
                createTree(node.right, data);
            }
        }
        return node;
    }

    public ArrayList<Integer> preOrderToList(TreeNode root, ArrayList<Integer> list) {
        if (root != null && list != null) {
            list.add(root.val);
            preOrderToList(root.left, list);
            preOrderToList(root.right, list);
        }
        return list;
    }

    @RuntimeLogAnnotation
    public ArrayList<Integer> execute(TreeNode root) {
        Mirror(root);
        return preOrderToList(root, new ArrayList<>());
    }

    public static void main(String[] args) {
        Offer018 offer018 = new Offer018();
        LinkedList<Integer> treeVal = new LinkedList<>(Arrays.asList(8,6,10,5,7,9,11));
        TreeNode treeNode = new TreeNode(treeVal.removeFirst());
        while (!treeVal.isEmpty()) {
            offer018.createTree(treeNode, treeVal.removeFirst());
        }
        offer018.execute(treeNode);
    }
}
