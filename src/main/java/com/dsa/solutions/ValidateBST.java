package com.dsa.solutions;

import com.dsa.solutions.tree.TreeNode;
public class ValidateBST {
    public static Integer lastPrinted = null;

    public static boolean checkBST(TreeNode node) {
        return checkBST(node, true);
    }

    // Allow "equal" value only for left child. This validates the BST property.
    public static boolean checkBST(TreeNode n, boolean isLeft) {
        if (n == null) {
            return true;
        }

        // Check / recurse left
        if (!checkBST(n.left, true)) {
            return false;
        }

        // Check current
        if (lastPrinted != null) {
            if (isLeft) {
                // left child "is allowed" be equal to parent.
                if (n.data < lastPrinted) {
                    return false;
                }
            } else {
                // Right child "is not allowed" be equal to parent.
                if (n.data <= lastPrinted) {
                    return false;
                }
            }
        }
        lastPrinted = n.data;

        // Check / recurse right
        if (!checkBST(n.right, false)) {
            return false;
        }
        return true;
    }


}