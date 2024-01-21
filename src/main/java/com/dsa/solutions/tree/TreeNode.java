package com.dsa.solutions.tree;

public class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    public int size = 0;

    public TreeNode(int d) {
        data = d;
        size = 1;
    }

    public static TreeNode createMinimalBST(int[] array, int start, int end) {
        if(start > end){
            return null;
        }
        int mid = (start + end)/2;
        TreeNode treeNode = new TreeNode(array[mid]);
        treeNode.left = createMinimalBST(array, start, mid-1);
        if(treeNode.left != null){
            treeNode.left.parent= treeNode;
        }
        treeNode.right = createMinimalBST(array, mid+1, end);
        if(treeNode.right != null){
            treeNode.right.parent= treeNode;
        }
        return  treeNode;
    }
    public static TreeNode createMinimalBST(int[] array) {
        return createMinimalBST(array, 0, array.length-1);
    }
}
