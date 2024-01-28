package com.dsa.solutions;

import com.dsa.solutions.tree.TreeNode;

import java.util.*;

public class Solutions {
    //soltion O(n)
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new LinkedHashMap<>();
        for(int i=0; i<nums.length; i++){
            map.put(target - nums[i], i);
        }

        for(int j=0; j<nums.length; j++){
            if(map.containsKey(nums[j]) && map.get(nums[j]) != j){
                return new int[]{map.get(nums[j]), j};
            }
        }

        return new int[]{-1,-1};
    }


    //solution brute force
    //O(n2)
    /*
    public int[] twoSum2(int[] nums, int target) {
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                if((nums[i] + nums[j]) == target){
                   return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }
    */

    //merge two sorted array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = n-1;
        int j = m-1;
        int k=m+n;
        while(i>=0){
            k--;
            if(j>=0){
                if(nums2[i] >= nums1[j]){
                    nums1[k] = nums2[i];
                    i--;
                }
                else{
                    nums1[k] = nums1[j];
                    nums1[j] = nums2[i];
                    j--;
                }
            }
            else {
                nums1[k] = nums2[i];
                i--;
            }
        }
    }

    //elegant solution
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int j = m - 1;
        int i = n - 1;
        int k = m + n - 1;

        while (i >= 0) {
            if (j >= 0 && nums1[j] > nums2[i]) {
                nums1[k--] = nums1[j--];
            } else {
                nums1[k--] = nums2[i--];
            }
        }
    }

    //task Valid Parentheses
    public boolean isValid(String s) {
        if(s == null || s.isEmpty())
            return false;
        char[] arr = s.toCharArray();
        if(arr.length%2!=0){
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');

        Stack<Character> stack = new Stack();
        for (int i=0; i<arr.length; i++){
            if(map.containsKey(arr[i])){
                stack.push(arr[i]);
            }
            else {
                if(!stack.isEmpty()){
                    char open = stack.pop();
                    if(!map.get(open).equals(arr[i])){
                        return false;
                    }
                }
                else return false;
            }
        }

        if(!stack.isEmpty()){
            return false;
        }

        return true;
    }

    //21. Merge Two Sorted Linked Lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 == null){
            return null;
        }
        if(list1 == null && list2 != null){
            return list2;
        }
        if(list1 != null && list2 == null){
            return list1;
        }
        ListNode head;
        ListNode ptr1 = list1;
        ListNode ptr2 = list2;

        if(ptr1.val > ptr2.val){
            head = ptr2;
            ptr2 = ptr2.next;
        }
        else{
            head = ptr1;
            ptr1 = ptr1.next;
        }

        ListNode next = head;

        while(next != null){
            if(ptr1 != null && ptr2 != null){
                if(ptr1.val > ptr2.val){
                    next.next = ptr2;
                    ptr2 = ptr2.next;
                }
                else{
                    next.next = ptr1;
                    ptr1 = ptr1.next;
                }
            }
            else {
                if(ptr1 == null && ptr2 == null){
                    next.next = null;
                } else if (ptr1 != null) {
                    next.next = ptr1;
                    ptr1 = ptr1.next;
                } else {
                    next.next = ptr2;
                    ptr2 = ptr2.next;
                }
            }

            next = next.next;
        }



        return head;
    }

    //141. Linked List Cycle
    public boolean hasCycle(ListNode head) {

        if(head == null || head.next == null){
            return false;
        }

        ListNode ptr1 = head;
        ListNode ptr2 = head.next.next;

        while(ptr1 != null && ptr2 != null){
            if(ptr1 == ptr2){
                return true;
            }
            ptr1 = ptr1.next;
            if(ptr2.next == null)
                return false;
            ptr2 = ptr2.next.next;
        }

        return false;
    }

    //2. Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode ptr1 = l1;
        ListNode ptr2 = l2;

        ListNode head = null;
        ListNode next = null;
        int caret = 0;
        int sum = 0;
        int mod = 0;
        while(ptr1 != null || ptr2 != null){
            if(ptr1 != null && ptr2 != null){
                sum = ptr1.val + ptr2.val + caret;
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
            } else if(ptr1 != null && ptr2 == null){
                sum = ptr1.val + caret;
                ptr1 = ptr1.next;
            } else {
                sum = ptr2.val + caret;
                ptr2 = ptr2.next;
            }

            caret = sum/10;
            mod = sum%10;

            if(head == null){
                head = new ListNode(mod);
                next = head;
            }
            else{
                next.next = new ListNode(mod);
                next = next.next;
            }
        }
        if(caret > 0){
            next.next = new ListNode(caret);
        }

        return head;
    }

    //104. Maximum Depth of Binary Tree
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    //111. Minimum Depth of Binary Tree
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left != null && root.right != null)
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        else
            return 1 + Math.max(minDepth(root.left), minDepth(root.right));
    }

    //110. Balanced Binary Tree
    public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        return checkHeight(root) != -1;
    }

    public int checkHeight(TreeNode node){
        if(node == null)
            return 0;
        int lHeight = checkHeight(node.left);
        if(lHeight == -1)
            return -1;
        int rHeight = checkHeight(node.right);
        if(rHeight == -1)
            return -1;
        if(Math.abs(lHeight - rHeight)>1){
            return -1;
        }
        else return 1 + Math.max(lHeight, rHeight);
    }

    TreeNode prev;
    Integer minDiff = null;

    //Minimum Absolute Difference in BST
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDiff!=null?minDiff:0;
    }

    private void inorder(TreeNode root){
        //base
        if(root == null)
            return;

        //logic
        inorder(root.left);
        if(prev != null){
            int diff = root.data-prev.data;
            if(minDiff == null)
                minDiff = diff;
            if(diff<minDiff)
                minDiff = diff;
        }
        prev = root;
        inorder(root.right);
    }

    int i = 0;
    TreeNode node;

    //Kth Smallest Element in a BST
    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return i;
    }

    private void inorder(TreeNode root, int k){
        //base
        if(root == null)
            return;

        //logic
        inorder(root.left, k);
        if(i == k){
            node = root;
            return;
        }
        i++;
        inorder(root.right, k);
    }

    TreeNode parent = null;

    //236. Lowest Common Ancestor of a Binary Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        postorder(root, p, q);
        return parent;
    }

    private TreeNode postorder(TreeNode node, TreeNode p, TreeNode q){

        if(node == null)
            return null;

        TreeNode n1 = postorder(node.left, p, q);
        TreeNode n2 = postorder(node.right, p, q);

        //parent left right
        if((n1 == p || n1 == q) && (n2 == p || n2 == q))
            parent = node;
        if((n1 == p || n1 == q) && (node == p || node == q))
            parent = node;
        if((n2 == p || n2 == q) && (node == p || node == q))
            parent = node;

        //up
        if(n1 == p || n1 == q)
            return n1;
        if(n2 == p || n2 == q)
            return n2;

        return node;
    }

    //Average of Levels in Binary Tree
    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> result = new ArrayList();
        int popCount = 1;
        int addCount = 0;
        int size=0;
        double sum = 0d;
        if(root != null){
            Queue<TreeNode> queue = new LinkedList();
            queue.add(root);
            TreeNode node;
            while(!queue.isEmpty()){
                node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                    addCount++;
                }
                if(node.right != null){
                    queue.add(node.right);
                    addCount++;
                }

                //average logic
                if(popCount>0){
                    popCount--;
                    size++;
                    sum = sum + node.data;
                }

                if(popCount == 0){
                    Double average = sum/size;
                    result.add(average);

                    popCount = addCount;
                    addCount = 0;
                    size=0;
                    sum = 0d;
                }
            }
        }
        return result;
    }

    //199. Binary Tree Right Side View
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList();

        int popCount = 1;
        int addCount = 0;
        Integer last = null;
        if(root != null){
            Queue<TreeNode> queue = new LinkedList();
            queue.add(root);
            TreeNode node;
            while(!queue.isEmpty()){
                node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                    addCount++;
                }
                if(node.right != null){
                    queue.add(node.right);
                    addCount++;
                }

                //average logic
                if(popCount>0){
                    popCount--;
                    last = node.data;
                }

                if(popCount == 0){
                    result.add(last);

                    popCount = addCount;
                    addCount = 0;
                }
            }
        }
        return result;
    }


    public int numIslands(char[][] grid) {

        int numRows = grid.length;
        int numColumns = grid[0].length;
        boolean[][] visited = new boolean[numRows][numColumns];

        int numberOfIslands = 0;
        Queue<Spot> queue = new LinkedList();

        for(int i=0; i<numRows; i++){
            for(int j=0; j<numColumns; j++){
                if(grid[i][j]=='1' && !visited[i][j]){
                    numberOfIslands++;
                    Spot spot = new Spot(i,j);
                    //BFS
                    visited[spot.i][spot.j] = true;
                    queue.add(spot);

                    while(!queue.isEmpty()){
                        spot = queue.poll();
                        //left neighbor
                        if(spot.j-1>=0 && !visited[spot.i][spot.j-1] && grid[spot.i][spot.j-1]=='1'){
                            visited[spot.i][spot.j-1] = true;
                            queue.add(new Spot(spot.i, spot.j-1));
                        }
                        //above neighbor
                        if(spot.i-1>=0 && !visited[spot.i-1][spot.j] && grid[spot.i-1][spot.j]=='1'){
                            visited[spot.i-1][spot.j] = true;
                            queue.add(new Spot(spot.i-1, spot.j));
                        }
                        //right neighbor
                        if(spot.j+1<numColumns && !visited[spot.i][spot.j+1] && grid[spot.i][spot.j+1]=='1'){
                            visited[spot.i][spot.j+1] = true;
                            queue.add(new Spot(spot.i, spot.j+1));
                        }
                        //below neighbor
                        if(spot.i+1<numRows && !visited[spot.i+1][spot.j] && grid[spot.i+1][spot.j]=='1'){
                            visited[spot.i+1][spot.j] = true;
                            queue.add(new Spot(spot.i+1, spot.j));
                        }
                    }
                }
            }
        }

        return numberOfIslands;
    }

    class Spot{
        int i;
        int j;

        public Spot(){
        }
        public Spot(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args){
        Solutions solutions = new Solutions();
        char[][] grid = {{"1","1","0","0","0"},{"1","1","0","0","0"},{"0","0","1","0","0"},{"0","0","0","1","1"}};
        solutions.numIslands(grid);
    }
}
