package base.datastructure._4_tree;

import java.util.Arrays;
import java.util.LinkedList;

public class Client {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        BinaryTree.TreeNode<Integer> treeNode = null;
        LinkedList<Integer> linkedList = null;

        // {8,6,10,5,7,9,11}
        // V1
        linkedList = new LinkedList<>(Arrays.asList(8,6,5,null,null,7,null,null,10,9,null,null,11));
        treeNode = binaryTree.createBinaryTree(linkedList);
        binaryTree.inOrderWithTips("中序遍历（镜像前）：", treeNode);
        System.out.println();
        treeNode = binaryTree.mirrorBinaryTree(treeNode);
        binaryTree.inOrderWithTips("中序遍历（镜像后）：", treeNode);
        System.out.println();

        // V2
        linkedList = new LinkedList<>(Arrays.asList(8,6,10,5,7,9,11));
        treeNode = new BinaryTree.TreeNode<>(linkedList.removeFirst());
        while (!linkedList.isEmpty()){
            binaryTree.createBinaryTreeV2(treeNode, linkedList.removeFirst());
        }
        binaryTree.levelOrderWithTips("层序遍历（镜像前）：", treeNode);
        System.out.println();
        treeNode = binaryTree.mirrorBinaryTree(treeNode);
        binaryTree.levelOrderWithTips("层序遍历（镜像后）：", treeNode);
        System.out.println();

        // V3
        linkedList = new LinkedList<>(Arrays.asList(8,6,10,5,7,9,11));
//        linkedList = new LinkedList<>(Arrays.asList(8,6,10,5,7,9,null));
        treeNode = binaryTree.createBinaryTreeV3(linkedList);
        binaryTree.levelOrderWithTips("层序遍历（镜像前）：", treeNode);
        System.out.println();
        treeNode = binaryTree.mirrorBinaryTree(treeNode);
        binaryTree.levelOrderWithTips("层序遍历（镜像后）：", treeNode);
        System.out.println();
    }
}
