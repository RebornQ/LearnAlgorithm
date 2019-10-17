package datastructure._4_tree;

import java.util.LinkedList;

public class BinaryTree<T> {

    /**
     * 内部结点类
     *
     * @param <U> 结点内容的数据类型
     */
    public static class TreeNode<U> {
        U data;
        TreeNode<U> leftChild;
        TreeNode<U> rightChild;

        public TreeNode(U data) {
            this.data = data;
        }
    }

    /**
     * 递归建立二叉树
     *
     * @param nodeList
     * @return
     */
    public TreeNode<T> createBinaryTree(LinkedList<T> nodeList) {
        TreeNode<T> node = null;
        if (nodeList == null || nodeList.isEmpty()) {
            return null;
        }
        T data = nodeList.removeFirst();
        if (data != null) {
            node = new TreeNode<>(data);
            node.leftChild = createBinaryTree(nodeList);
            node.rightChild = createBinaryTree(nodeList);
        }
        return node;
    }

    public TreeNode<Integer> createBinaryTreeV2(TreeNode<Integer> node, Integer data) {
        if (data < node.data) {
            if (node.leftChild == null) {
                node.leftChild = new TreeNode<>(data);
            } else {
                createBinaryTreeV2(node.leftChild, data);
            }
        } else {
            if (node.rightChild == null) {
                node.rightChild = new TreeNode<>(data);
            } else {
                createBinaryTreeV2(node.rightChild, data);
            }
        }
        return node;
    }

    /**
     * 层序建立二叉树
     *
     * @param nodeList
     * @return
     */
    public TreeNode<T> createBinaryTreeV3(LinkedList<T> nodeList) {
        TreeNode<T> node = null;
        if (nodeList == null || nodeList.isEmpty()) {
            return null;
        }
        T data = nodeList.removeFirst();
        node = new TreeNode<>(data);
        // 创建一个队列
        LinkedList<TreeNode<T>> nodeQueue = new LinkedList<>();
        // 将根结点放入队列
        nodeQueue.offer(node);
        /*
         * 循环判断队列是否为空，
         * 如果非空，将结点的左右孩子放入队列
         * 为空的话，结束遍历
         */
        while (!nodeList.isEmpty()) {
            // 取出结点
            TreeNode<T> temp = nodeQueue.poll();
            // 如果当前结点的左孩子不为空,放入队列
            if (temp.leftChild == null) {
                T leftData = nodeList.removeFirst();
                if (leftData == null)
                    temp.leftChild = null;
                else
                    temp.leftChild = new TreeNode<>(leftData);
            }
            nodeQueue.offer(temp.leftChild);
            // 如果当前结点的又孩子不为空,放入队列
            if (temp.rightChild == null) {
                T rightData = nodeList.removeFirst();
                if (rightData == null)
                    temp.rightChild = null;
                else
                    temp.rightChild = new TreeNode<>(rightData);
            }
            nodeQueue.offer(temp.rightChild);
        }
        return node;
    }

    /**
     * 前序遍历
     *
     * @param node 根结点
     */
    public void preOrder(TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }
    public void preOrderWithTips(String msg, TreeNode<T> node) {
        System.out.print(msg);
        preOrder(node);
    }
    public void preOrderWithTips(TreeNode<T> node) {
        preOrderWithTips("前序遍历：", node);
    }


    /**
     * 中序遍历
     *
     * @param node 根结点
     */
    public void inOrder(TreeNode<T> node) {
        if (node != null) {
            inOrder(node.leftChild);
            System.out.print(node.data + " ");
            inOrder(node.rightChild);
        }
    }
    public void inOrderWithTips(String msg, TreeNode<T> node) {
        System.out.print(msg);
        inOrder(node);
    }
    public void inOrderWithTips(TreeNode<T> node) {
        inOrderWithTips("中序遍历：", node);
    }

    /**
     * 后序遍历
     *
     * @param node 根结点
     */
    public void postOrder(TreeNode<T> node) {
        if (node != null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.print(node.data + " ");
        }
    }
    public void postOrderWithTips(String msg, TreeNode<T> node) {
        System.out.print(msg);
        postOrder(node);
    }
    public void postOrderWithTips(TreeNode<T> node) {
        postOrderWithTips("后序遍历：", node);
    }

    /**
     * 层序遍历
     *
     * @param node 根结点
     */
    public void levelOrder(TreeNode<T> node) {
        // 如果结点为空,返回
        if (node == null)
            return;
        // 创建一个队列
        LinkedList<TreeNode<T>> nodeQueue = new LinkedList<>();
        // 将根结点放入队列
        nodeQueue.offer(node);
        /*
         * 循环判断队列是否为空，
         * 如果非空，将结点的左右孩子放入队列，并且取出data域的数据
         * 为空的话，结束遍历
         */
        while (!nodeQueue.isEmpty()) {
            // 取出结点
            TreeNode<T> temp = nodeQueue.poll();
            // 打印结点信息
            System.out.print(temp.data + " ");
            // 如果当前结点的左孩子不为空,放入队列
            if (temp.leftChild != null)
                nodeQueue.offer(temp.leftChild);
            // 如果当前结点的又孩子不为空,放入队列
            if (temp.rightChild != null)
                nodeQueue.offer(temp.rightChild);
        }
    }
    public void levelOrderWithTips(String msg, TreeNode<T> node) {
        System.out.print(msg);
        levelOrder(node);
    }
    public void levelOrderWithTips(TreeNode<T> node) {
        levelOrderWithTips("层序遍历：", node);
    }

    /**
     * 镜像二叉树
     * <p>
     * 用例:
     * {8,6,10,5,7,9,11}
     * <p>
     *      8
     *   6    10
     * 5  7  9  11
     *
     * <p>
     * 对应输出应该为:
     * <p>
     * {8,10,6,11,9,7,5}
     * <p>
     *      8
     *   10    6
     * 11  9  7  5
     *
     * @param root 根结点
     */
    public TreeNode<T> mirrorBinaryTree(TreeNode<T> root) {

        if (root == null) return null;  // 此树为空

        // 交换左右节点
        TreeNode<T> nodeTemp = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = nodeTemp;

        // 先递归遍历当前节点的左子树
        if (root.leftChild != null)
            mirrorBinaryTree(root.leftChild);
        // 左子树遍历为空，开始遍历右子树
        if (root.rightChild != null)
            mirrorBinaryTree(root.rightChild);
        // 当右子树遍历完成，返回
        return root;
    }

}
