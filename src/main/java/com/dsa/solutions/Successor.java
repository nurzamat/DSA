package com.dsa.solutions;

import com.dsa.solutions.tree.TreeNode;

public class Successor {

    //Inorder Successor in Binary Search Tree
    public static TreeNode inorderSucc(TreeNode n) {
        if (n == null) return null;

        // Found right children -> return left most node of right subtree
        if (n.parent == null || n.right != null) {
            return leftMostChild(n.right);
        } else {
            TreeNode q = n;
            TreeNode x = q.parent;
            // Go up until we're on left instead of right
            while (x != null && x.left != q) {
                q = x;
                x = x.parent;
            }
            return x;
        }
    }

    public static TreeNode leftMostChild(TreeNode n) {
        if (n == null) {
            return null;
        }
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

}
