package com.dsa.solutions;

import com.dsa.solutions.tree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

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

    public int numIslandsNew(char[][] grid) {

        int numRows = grid.length;
        int numColumns = grid[0].length;
        int numberOfIslands = 0;
        Queue<Spot> queue = new LinkedList();
        Spot spot;
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numColumns; j++){
                if(grid[i][j]=='1'){
                    numberOfIslands++;
                    spot = new Spot(i,j);
                    //BFS
                    grid[spot.i][spot.j] = '0';
                    queue.add(spot);

                    while(!queue.isEmpty()){
                        spot = queue.poll();
                        //left neighbor
                        if(spot.j-1>=0 && grid[spot.i][spot.j-1]=='1'){
                            grid[spot.i][spot.j-1] = '0';
                            queue.add(new Spot(spot.i, spot.j-1));
                        }
                        //above neighbor
                        if(spot.i-1>=0 && grid[spot.i-1][spot.j]=='1'){
                            grid[spot.i-1][spot.j] = '0';
                            queue.add(new Spot(spot.i-1, spot.j));
                        }
                        //right neighbor
                        if(spot.j+1<numColumns && grid[spot.i][spot.j+1]=='1'){
                            grid[spot.i][spot.j+1] = '0';
                            queue.add(new Spot(spot.i, spot.j+1));
                        }
                        //below neighbor
                        if(spot.i+1<numRows && grid[spot.i+1][spot.j]=='1'){
                            grid[spot.i+1][spot.j] = '0';
                            queue.add(new Spot(spot.i+1, spot.j));
                        }
                    }
                }
            }
        }

        return numberOfIslands;
    }

//best solution for number of islands
   public int numIslands2(char[][] grid) {
       int numOfIslands = 0;
       int m = grid.length;
       int n = grid[0].length;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == '1') {
                dfs(i, j, grid);
                numOfIslands += 1;
            }
        }
    }
    return numOfIslands;
   }

    private void dfs(int x, int y, char[][] grid) {
        if ((x >= grid.length) || (x < 0) || (y >= grid[0].length) || (y < 0) || (grid[x][y] == '0')) {
            return;
        }
        grid[x][y] = '0'; // mark as visited

        dfs(x+1, y, grid);
        dfs(x-1, y, grid);
        dfs(x, y+1, grid);
        dfs(x, y-1, grid);
        return;
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

    //108. Convert Sorted Array to Binary Search Tree
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] arr, int start, int end){
        if(end<start)
            return null;
        int mid = (end+start)/2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = sortedArrayToBST(arr, start, mid-1);
        node.right = sortedArrayToBST(arr, mid+1, end);

        return node;
    }

    //26. Remove Duplicates from Sorted Array
    public int removeDuplicates(int[] nums) {

        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }

        int k = 1;

        for(int i=0; i<nums.length-1; i++){
            if(nums[i+1]>nums[i]){
                k++;
                nums[k-1] = nums[i+1];
            }
        }

        return k;
    }

    //27. Remove Element
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != val){
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }

    //169. Majority Element
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];

        Arrays.sort(nums);
        int prevCount = 0;
        int prevValue = 0;
        int currentCount = 1;
        int currentValue = nums[0];

        for(int i=0; i<nums.length-1; i++){
            if((nums[i+1]-nums[i])>0){
                currentCount = 1;
                currentValue = nums[i+1];
            }
            else {
                currentCount++;
            }
            if(currentCount > prevCount){
                prevCount = currentCount;
                prevValue = currentValue;
            }
        }
        return prevValue;
    }

    //121. Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        // Initialize 'maxProfit' to 0, which is the minimum profit that can be made.
        int maxProfit = 0;

        // Assume the first price is the minimum buying price.
        int minPrice = prices[0];

        // Loop through all the prices to find the maximum profit.
        for (int price : prices) {
            // Calculate the maximum profit by comparing the current 'maxProfit'
            // with the difference of the current price and the 'minPrice'.
            maxProfit = Math.max(maxProfit, price - minPrice);

            // Update the 'minPrice' if a lower price is found.
            minPrice = Math.min(minPrice, price);
        }

        // Return the maximum profit that can be achieved.
        return maxProfit;
    }

    //100. Same Tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
        boolean isSameTree = checkTree(p, q);
        return isSameTree;
    }

    public boolean checkTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p == null || q == null){
            return false;
        }

        if(p.data != q.data){
            return false;
        }
        boolean sameLeft = checkTree(p.left, q.left);
        if(!sameLeft)
            return false;
        boolean sameRight = checkTree(p.right, q.right);
        if(!sameRight)
            return false;

        return true;
    }

    //226. Invert Binary Tree
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    private void invert(TreeNode root){
        if(root == null)
            return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invert(root.left);
        invert(root.right);
    }

    //101. Symmetric Tree
    public boolean isSymmetric(TreeNode root) {
        return checkSymmetry(root.left, root.right);
    }

    private TreeNode invertNode(TreeNode root){
        if(root != null){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        return root;
    }

    public boolean checkSymmetry(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p == null || q == null){
            return false;
        }

        if(p.data != invertNode(q).data){
            return false;
        }
        boolean sameLeft = checkSymmetry(p.left, q.left);
        if(!sameLeft)
            return false;
        boolean sameRight = checkSymmetry(p.right, q.right);
        if(!sameRight)
            return false;

        return true;
    }

    //28. Find the Index of the First Occurrence in a String
    public int strStr(String haystack, String needle) {
        if(haystack == null)
            return -1;
        if(haystack.contains(needle))
            return haystack.indexOf(needle);
        return -1;
    }

    //58. Length of Last Word
    public int lengthOfLastWord(String s) {
        if(s != null){
            String[] arr = s.split(" ");
            String word = arr[arr.length-1];
            return word.length();
        }

        return 0;
    }

    //14. Longest Common Prefix
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0)
            return "";
        char[] firstArr = strs[0].toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<firstArr.length; i++){
            for(int j=1; j<strs.length; j++){
                if((strs[j].length()-1)<i || firstArr[i] != strs[j].charAt(i)){
                    return sb.toString();
                }
            }
            sb.append(firstArr[i]);
        }
        return sb.toString();
    }

    //13. Roman to Integer
    public int romanToInt(String s) {
        if(s == null)
            return -1;
        int sum = 0;
        char[] arr = s.toCharArray();
        if(arr.length == 1)
            return symbolToInt(arr[0]);
        int current = 0;
        int next = 0;
        for(int i=0; i<arr.length-1; i++){
            current = symbolToInt(arr[i]);
            next = symbolToInt(arr[i+1]);
            if(current >= next)
                sum = sum + current;
            else sum = sum - current;
        }
        sum = sum + next;

        return sum;
    }

    private int symbolToInt(char symbol){
        switch(symbol){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    //222. Count Complete Tree Nodes
    public int countNodes(TreeNode root) {
        if(root == null)
            return 0;
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);

        return leftCount + rightCount + 1;
    }

    int[] preorder;
    int[] inorder;

    //105. Construct Binary Tree from Preorder and Inorder Traversal
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        if(preorder == null || inorder == null){
            return null;
        }
        if(preorder.length != inorder.length){
            return null;
        }
        if(inorder.length == 1){
            return new TreeNode(inorder[0]);
        }
        if(inorder.length == 2){
            TreeNode node = new TreeNode(preorder[0]);
            if(inorder[0]==node.data)
                node.right = new TreeNode(inorder[1]);
            else node.left = new TreeNode(inorder[0]);
            return node;
        }
        TreeNode root = getNode(0, preorder.length-1, 0, inorder.length-1);
        return root;
    }

    private TreeNode getNode(int pStart, int pEnd, int inStart, int inEnd){

        int val = preorder[pStart];
        TreeNode root = new TreeNode(val);

        int lInStart = inStart;
        int lInEnd = 0;
        int rInStart = 0;
        int rInEnd = inEnd;
        for(int i=inStart; i<inEnd; i++){
            if(inorder[i]==val){
                lInEnd = i-1;
                rInStart = i+1;
            }
        }
        int lpStart = pStart + 1;
        int lpEnd = lpStart + lInEnd - lInStart;
        int rpStart = lpEnd + 1;
        int rpEnd = pEnd;

        if(lInStart == lInEnd){
            root.left = new TreeNode(inorder[lInStart]);
        }
        else {
            if(lInStart<lInEnd)
                root.left = getNode(lpStart, lpEnd, lInStart, lInEnd);
        }

        if(rInStart == rInEnd){
            root.right = new TreeNode(inorder[rInStart]);
        }
        else {
            if(rInStart<rInEnd)
                root.right = getNode(rpStart, rpEnd, rInStart, rInEnd);
        }

        return root;
    }

    int[] postorder;

    //106. Construct Binary Tree from Inorder and Postorder Traversal
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;

        if(postorder == null || inorder == null){
            return null;
        }
        if(postorder.length != inorder.length){
            return null;
        }
        if(inorder.length == 1){
            return new TreeNode(inorder[0]);
        }
        TreeNode root = getNode(0, postorder.length-1, 0, inorder.length-1);
        return root;
    }

    private TreeNode getNode(int pStart, int pEnd, int inStart, int inEnd){
        if(pStart>pEnd || inStart>inEnd)
            return null;
        int val = postorder[pEnd];
        TreeNode root = new TreeNode(val);

        int lInStart = inStart;
        int lInEnd = 0;
        int rInStart = 0;
        int rInEnd = inEnd;
        for(int i=inStart; i<=inEnd; i++){
            if(inorder[i]==val){
                lInEnd = i-1;
                rInStart = i+1;
            }
        }
        int lpStart = pStart;
        int lpEnd = lpStart + lInEnd - lInStart;
        int rpStart = lpEnd + 1;
        int rpEnd = pEnd-1;

        if(lInStart == lInEnd){
            root.left = new TreeNode(inorder[lInStart]);
        }
        else {
            root.left = getNode(lpStart, lpEnd, lInStart, lInEnd);
        }

        if(rInStart == rInEnd){
            root.right = new TreeNode(inorder[rInStart]);
        }
        else {
            root.right = getNode(rpStart, rpEnd, rInStart, rInEnd);
        }

        return root;
    }

    int popCount = 1;
    int addCount = 0;

    //117. Populating Next Right Pointers in Each Node II
    public Node connect(Node root) {
        if(root == null)
            return null;

        Queue<Node> queue = new LinkedList();
        queue.add(root);
        Node prev = null;
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node.left != null){
                addCount++;
                queue.add(node.left);
            }
            if(node.right != null){
                addCount++;
                queue.add(node.right);
            }

            if(prev == null)
                prev = node;
            else {
                prev.next = node;
                prev = node;
            }

            popCount--;
            if(popCount == 0){
                popCount = addCount;
                addCount = 0;
                prev = null;
            }
        }

        return root;
    }

    //114. Flatten Binary Tree to Linked List
    public void flatten(TreeNode root) {
        root = preorder(root);
    }

    public TreeNode preorder(TreeNode root){
        if(root == null)
            return null;

        TreeNode left = preorder(root.left);
        TreeNode right = preorder(root.right);
        if(left == null)
            return root;

        if(left.right != null){
            TreeNode ptr = left.right;
            while(ptr.right != null){
                ptr = ptr.right;
            }
            ptr.right = right;
        }
        else {
            left.right = right;
        }
        root.right = left;
        root.left = null;
        return root;
    }

    //129. Sum Root to Leaf Numbers
    public int sumNumbers(TreeNode root) {
        return sumNumbersDFS(root, 0);
    }

    private int sumNumbersDFS(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }
        currentSum = currentSum * 10 + node.val;
        if (node.left == null && node.right == null) {
            return currentSum;
        }
        int leftSum = sumNumbersDFS(node.left, currentSum);
        int rightSum = sumNumbersDFS(node.right, currentSum);
        return leftSum + rightSum;
    }

    //112. Path Sum
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasSumDFS(root, 0, targetSum);
    }

    private boolean hasSumDFS(TreeNode node, int currentSum, int targetSum) {
        if (node == null) {
            return false;
        }
        currentSum = currentSum + node.val;
        if (node.left == null && node.right == null) {
            if(currentSum == targetSum)
                return true;
        }
        boolean hasSumLeft = hasSumDFS(node.left, currentSum, targetSum);
        if(hasSumLeft)
            return true;
        boolean hasSumRight = hasSumDFS(node.right, currentSum, targetSum);
        if(hasSumRight)
            return true;
        return false;
    }

    //173. Binary Search Tree Iterator
    class BSTIterator {
        TreeNode node = new TreeNode(-1);
        TreeNode ptr = node;

        public BSTIterator(TreeNode root) {
            inorder(root);
            ptr = node;
        }

        public int next() {
            int val = 0;
            if(ptr.right != null){
                val = ptr.right.val;
                ptr = ptr.right;
            }
            return val;
        }

        public boolean hasNext() {
            return ptr.right != null;
        }

        private void inorder(TreeNode root){
            if(root == null)
                return;
            inorder(root.left);
            ptr.right = new TreeNode(root.val);
            ptr = ptr.right;
            inorder(root.right);
        }
    }


    //130. Surrounded Regions
    public void solve(char[][] board) {

        int m = board.length;
        int n = board[0].length;

        if(m == 1 || n == 1){
            return;
        }

        Queue<Spot> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j]=='O' && !visited[i][j]){
                    Spot spot = new Spot(i,j);
                    board[i][j] = 'T';
                    visited[i][j] = true;
                    queue.add(spot);
                    boolean borderFound = false;

                    while (!queue.isEmpty()){
                        spot = queue.poll();
                        //left neighbor
                        if(spot.j-1>=0 && board[spot.i][spot.j-1]=='O'){
                            board[spot.i][spot.j-1] = 'T';
                            visited[spot.i][spot.j-1] = true;
                            queue.add(new Spot(spot.i, spot.j-1));
                        }
                        //above neighbor
                        if(spot.i-1>=0 && board[spot.i-1][spot.j]=='O'){
                            board[spot.i-1][spot.j] = 'T';
                            visited[spot.i-1][spot.j] = true;
                            queue.add(new Spot(spot.i-1, spot.j));
                        }
                        //right neighbor
                        if(spot.j+1<n && board[spot.i][spot.j+1]=='O'){
                            board[spot.i][spot.j+1] = 'T';
                            visited[spot.i][spot.j+1]= true;
                            queue.add(new Spot(spot.i, spot.j+1));
                        }
                        //below neighbor
                        if(spot.i+1<m && board[spot.i+1][spot.j]=='O'){
                            board[spot.i+1][spot.j] = 'T';
                            visited[spot.i+1][spot.j] = true;
                            queue.add(new Spot(spot.i+1, spot.j));
                        }

                        //check border
                        if(spot.j-1<0 || spot.i-1<0 || spot.j+1>=n || spot.i+1>=m){
                            borderFound = true;
                        }
                    }

                    for(int l=0; l<m; l++){
                        for(int k=0; k<n; k++){
                            if(!borderFound){
                                if(board[l][k]=='T'){
                                    board[l][k]='X';
                                }
                            }
                            else
                            {
                                if(board[l][k]=='T'){
                                    board[l][k]='O';
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //133. Clone Graph
    public Node cloneGraph(Node node) {
        if(node == null)
            return null;
        Map<Integer, Node> map = new HashMap();
        Queue<Node> queue = new LinkedList();

        Node clone = new Node(node.val);
        map.put(clone.val, clone);
        queue.add(node);
        Node temp;
        Node visited;
        Node n1;
        while(!queue.isEmpty()){
            temp = queue.poll();
            visited = map.get(temp.val);
            for(Node n: temp.neighbors){
                if(!map.containsKey(n.val)){
                    n1 = new Node(n.val);
                    map.put(n1.val, n1);
                    visited.neighbors.add(n1);
                    queue.add(n);
                }
                else{
                    visited.neighbors.add(map.get(n.val));
                }
            }
        }

        return clone;
    }

    //207. Course Schedule
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList();
        for(int i=0; i<numCourses; i++){
            graph.add(new ArrayList());
        }

        //Create an array to store the in-degree (number of prerequisites) for each course
        int[] inDegrees = new int[numCourses];

        //Populate the graph and update the in-degrees based on prerequisites
        for(int[] prerequisite : prerequisites){
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            graph.get(prerequisiteCourse).add(course);
            //Increment the in-degree of the course
            inDegrees[course]++;
        }

        //Queue to hold courses with in-degree of 0
        Queue<Integer> queue = new LinkedList();

        //Enqueue all courses wich have no prerequisites
        for(int i=0; i<numCourses; i++){
            if(inDegrees[i]==0){
                queue.offer(i);
            }
        }

        //Counter for number of courses that have been processed
        int processedCourses = 0;

        //Process the courses in the queue
        while(!queue.isEmpty()){
            int course = queue.poll();
            processedCourses++;

            //Iterate over the neighbors of the current course
            for(int neighbor : graph.get(course)){
                //Decrement the in-degreee of each neighbor, since we have processed one of their prerequisites
                inDegrees[neighbor]--;
                if(inDegrees[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
        }
        //If the number of processed courses equals the total number of courses, all can be finished
        return processedCourses == numCourses;

    }

    //210. Course Schedule II
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList();
        for(int i=0; i<numCourses; i++){
            graph.add(new ArrayList());
        }

        int[] inDegrees = new int[numCourses];

        for(int[] prerequisite : prerequisites){
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            graph.get(prerequisiteCourse).add(course);

            inDegrees[course]++;
        }

        Queue<Integer> queue = new LinkedList();

        for(int i=0; i<numCourses; i++){
            if(inDegrees[i] == 0){
                queue.add(i);
            }
        }

        int[] result = new int[numCourses];
        int cnt = 0;
        while(!queue.isEmpty()){
            int course  = queue.poll();
            result[cnt] = course;
            cnt++;

            for(Integer neighbor: graph.get(course)){
                inDegrees[neighbor]--;
                if(inDegrees[neighbor] == 0){
                    queue.add(neighbor);
                }
            }
        }
        if(cnt == numCourses){
            return result;
        }
        return new int[0];
    }

    //909. Snakes and Ladders
    public int snakesAndLadders0(int[][] board) {

        int length = board.length;
        if(length == 2)
            return 1;

        Map<Integer, Integer> destMap = getDestinationMap(board);

        boolean[] visited = new boolean[length*length+1];
        Queue<Integer> queue = new LinkedList();

        visited[1] = true;
        queue.add(1);
        int cnt = 0;
        int in = 0;
        int out = 1;
        while(!queue.isEmpty()){
            int current = queue.poll();
            if(current == length*length)
                return cnt;
            for(int i=current+1; i<=Math.min(current+6, length*length); i++){
                int next = destMap.get(i) != -1?destMap.get(i):i;
                if(!visited[next]){
                    visited[next] = true;
                    queue.add(next);
                    in++;
                }
            }
            out--;
            if(out<=0){
                out = in;
                in = 0;
                cnt++;
            }
        }

        return -1;
    }

    private  Map<Integer, Integer> getDestinationMap(int[][] board){
        boolean directionRight = false;
        int currSquare = 0;
        Map<Integer, Integer> destMap = new HashMap();

        for(int i=board.length-1; i>=0; i--){
            if(!directionRight)
                directionRight = true;
            else directionRight = false;

            if(directionRight){
                for(int j=0; j<board.length; j++){
                    currSquare++;
                    destMap.put(currSquare, board[i][j]);
                }
            }
            else {
                for(int j=board.length-1; j>=0; j--){
                    currSquare++;
                    destMap.put(currSquare, board[i][j]);
                }
            }
        }

        return destMap;
    }


    //909. Snakes and Ladders
    private int boardSize;

    // Method to find the shortest path to reach the last square
    public int snakesAndLadders(int[][] board) {
        boardSize = board.length; // Get the size of the board (n x n)
        Deque<Integer> queue = new ArrayDeque<>(); // A queue to perform BFS
        queue.offer(1); // Start from the first square
        boolean[] visited = new boolean[boardSize * boardSize + 1]; // Visited array to keep track of visited squares
        visited[1] = true; // Mark the first square as visited
        int moves = 0; // Moves counter

        // Perform BFS to reach the last square
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; --i) { // Iterate over current level
                int current = queue.poll(); // Get the current square
                if (current == boardSize * boardSize) { // Check if reached the end
                    return moves;
                }
                // Explore the next 6 possible moves
                for (int k = current + 1; k <= Math.min(current + 6, boardSize * boardSize); ++k) {
                    int[] position = convertToPosition(k); // Convert square number to board coordinates
                    int next = k; // Next square
                    // Check if there's a snake or ladder in the square
                    if (board[position[0]][position[1]] != -1) {
                        next = board[position[0]][position[1]]; // Go to the new square
                    }
                    // If it's not visited, mark as visited and add to the queue
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            moves++; // Increment move count after finishing one level in BFS
        }
        return -1; // Return -1 if it's impossible to reach the end
    }

    // Convert the square number to board coordinates (i, j)
    private int[] convertToPosition(int squareNum) {
        int row = (squareNum - 1) / boardSize;
        int col = (squareNum - 1) % boardSize;
        // In even rows (from the top), the order is right to left
        if (row % 2 == 1) {
            col = boardSize - 1 - col;
        }
        // Convert row to the actual row in the board from the bottom
        return new int[] {boardSize - 1 - row, col};
    }

    //433. Minimum Genetic Mutation
    public int minMutation(String startGene, String endGene, String[] bank) {

        if(bank.length == 0)
            return -1;

        boolean[] visited = new boolean[bank.length];

        Queue<Integer> queue = new LinkedList();
        List<Integer> nextList = getBankMutation(bank, startGene);
        int cnt = 0;
        int in = 0;
        int out = 0;
        for (int index:nextList) {
            visited[index] = true;
            queue.add(index);
            out++;
        }

        while(!queue.isEmpty()){
            int index = queue.poll();
            String gene = bank[index];
            if(gene.equals(endGene))
                return cnt+1;
            nextList = getBankMutation(bank, gene);
            for (int idx:nextList) {
                if(!visited[idx]){
                    visited[idx] = true;
                    queue.add(idx);
                    in++;
                }
            }

            out--;
            if(out <= 0){
                cnt++;
                out = in;
                in = 0;
            }
        }

        return -1;
    }

    private List<Integer> getBankMutation(String[] bank, String gene){
        char[] startArr = gene.toCharArray();
        int length = startArr.length;
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<bank.length; i++){
            char[] endArr = bank[i].toCharArray();
            int mutCount = 0;

            for(int j=0; j<length; j++){
                if(startArr[j] != endArr[j]){
                    mutCount++;
                }
            }
            if(mutCount ==  1){
                list.add(i);
            }
        }
        return list;
    }

    //399. Evaluate Division
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        //fill hashmap with values
        String var1 = null;
        String var2 = null;
        List<String> list = null;

        Map<String, Integer> outMap = new HashMap();
        //find last elements
        for(int i=0; i<equations.size(); i++){
            list = equations.get(i);
            var1 = list.get(0);
            var2 = list.get(1);
            if(!outMap.containsKey(var1)){
                outMap.put(var1, 1);
            }
            else{
                outMap.put(var1, outMap.get(var1)+1);
            }

            if(!outMap.containsKey(var2)){
                outMap.put(var2, 0);
            }
        }

        List<Map<String, Double>> graphMap = new ArrayList();
        Queue<String> queue = new LinkedList();

        List<String> lastElements = new ArrayList();
        for (Map.Entry<String, Integer> entry : outMap.entrySet()) {
            if(entry.getValue() == 0){
                if(!checkInGraphMap(graphMap, entry.getKey())){
                    Map<String, Double> map = new HashMap();
                    map.put(entry.getKey(), 1.00);
                    queue.add(entry.getKey());

                    while(!queue.isEmpty()){
                        String element = queue.poll();

                        for(int i=0; i<equations.size(); i++){
                            list = equations.get(i);
                            var1 = list.get(0);
                            var2 = list.get(1);
                            if(var2.equals(element)){
                                if(!map.containsKey(var1)){
                                    map.put(var1, map.get(var2)*values[i]);
                                    queue.add(var1);
                                }
                            }
                            if(var1.equals(element)){
                                if(!map.containsKey(var2)){
                                    map.put(var2, map.get(var1)/values[i]);
                                    queue.add(var2);
                                }
                            }
                        }
                    }
                    graphMap.add(map);
                }
            }
        }

        double[] result = new double[queries.size()];

        for(int i=0; i<queries.size(); i++){
            list = queries.get(i);
            var1 = list.get(0);
            var2 = list.get(1);
            result[i]=getDivision(graphMap, var1, var2);
        }
        return result;
    }

    private boolean checkInGraphMap(List<Map<String, Double>> graphMap, String element){
        if(graphMap.size()>0){
            for(Map<String, Double> map: graphMap){
                if(map.containsKey(element)){
                    return true;
                }
            }
        }
        return false;
    }

    private Double getDivision(List<Map<String, Double>> graphMap, String var1, String var2){
        for(Map<String, Double> map: graphMap){
            if(map.containsKey(var1) && map.containsKey(var2)){
                return map.get(var1)/map.get(var2);
            }
        }
        return -1.00;
    }


    //383. Ransom Note
    public boolean canConstruct(String ransomNote, String magazine) {
        //edge cases
        if(magazine == null)
            return false;
        //store letters from magazine in map
        Map<Character, Integer> map = new HashMap();
        char[] magArr = magazine.toCharArray();
        for(int i=0; i<magArr.length; i++){
            if(!map.containsKey(magArr[i])){
                map.put(magArr[i], 1);
            }
            else{
                map.put(magArr[i], map.get(magArr[i])+1);
            }
        }

        //check letters from ransomNote
        char[] arr = ransomNote.toCharArray();
        for(int i=0; i<arr.length; i++){
            if(!map.containsKey(arr[i]) || map.get(arr[i])<1){
                return false;
            }
            else{
                map.put(arr[i], map.get(arr[i])-1);
            }
        }

        return true;
    }

    //125. Valid Palindrome
    public boolean isPalindrome1(String s) {
        if(s == null || s.trim() == "")
            return true;
        String str1 = "";
        String str2 = "";

        for(int i = 0; i<s.length(); i++){
            if(Character.isLetter(s.charAt(i))){
                str1 = str1 + String.valueOf(s.charAt(i)).toLowerCase();
            }
            else if(Character.isDigit(s.charAt(i))){
                str1 = str1 + s.charAt(i);
            }
        }

        for(int i = str1.length()-1; i>=0; i--){
            str2 = str2 + str1.charAt(i);
        }

        return str1.equals(str2);
    }

    //125. Valid Palindrome 2-nd solution
    public boolean isPalindrome2(String s) {
        if(s == null || s.trim() == "")
            return true;
        String str1 = "";

        for(int i = 0; i<s.length(); i++){
            if(Character.isLetter(s.charAt(i))){
                str1 = str1 + String.valueOf(s.charAt(i)).toLowerCase();
            }
            else if(Character.isDigit(s.charAt(i))){
                str1 = str1 + s.charAt(i);
            }
        }

        for(int i = 0; i<str1.length()/2; i++){
            if(str1.charAt(i) != str1.charAt(str1.length()-1-i))
                return false;
        }

        return true;
    }

    //125. Valid Palindrome best solution via 2 pointer method
    public boolean isPalindrome(String s) {
        if(s == null || s.trim() == "")
            return true;

        int left = 0;
        int right = s.length()-1;

        while(left < right){
            if(isLetterOrDigit(s.charAt(left)) && isLetterOrDigit(s.charAt(right))){
                if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                    return false;
                else{
                    left++;
                    right--;
                }
            }
            else if(!isLetterOrDigit(s.charAt(left)) && !isLetterOrDigit(s.charAt(right))){
                left++;
                right--;
            }
            else if(!isLetterOrDigit(s.charAt(left))){
                left++;
            }
            else{
                right--;
            }
        }

        return true;
    }

    /*
    //compact solution
    public boolean isPalindrome(String s) {
        if (s.length() == 0)
            return false;
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } else {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;

    }
     */

    private boolean isLetterOrDigit(char ch){
        if(Character.isLetter(ch) || Character.isDigit(ch)){
            return true;
        }
        return false;
    }

    //392. Is Subsequence
    public boolean isSubsequence(String s, String t) {
        if(s == null || s.length() == 0)
            return true;

        int sPos = 0;
        int sLength = s.length();

        for(int i=0; i<t.length(); i++){
            if(sPos<sLength && t.charAt(i) == s.charAt(sPos)){
                sPos++;
            }
        }

        return sPos == s.length();
    }

    //167. Two Sum II - Input Array Is Sorted
    public int[] twoSum(int[] numbers, int target) {

        int ptr1 = 0;
        int ptr2 = numbers.length-1;
        int sum;

        while(ptr2 > ptr1){
            sum = numbers[ptr1]+numbers[ptr2];
            if(sum == target){
                return new int[] {ptr1+1, ptr2+1};
            }
            else if(sum > target){
                ptr2--;
            }
            else if(sum < target){
                ptr1++;
            }
        }
        return new int[] {-1, -1};
    }

    //11. Container With Most Water
    public int maxArea(int[] height) {
        int maxArea = 0;
        if(height == null || height.length == 0 || height.length == 1)
            return maxArea;

        int currArea = 0;
        int minHeight = 0;
        int left = 0;
        int right = height.length-1;

        while(left < right){

            if(height[left] == height[right]){
                minHeight = height[left];
                currArea = (right-left)*minHeight;
                left++;
                right--;
            }
            else if(height[left] < height[right]){
                minHeight = height[left];
                currArea = (right-left)*minHeight;
                left++;
            }
            else {
                minHeight = height[right];
                currArea = (right-left)*minHeight;
                right--;
            }

            if(currArea > maxArea)
                maxArea = currArea;
        }

        return maxArea;
    }

    //35. Search Insert Position
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return 0;

        int left = 0;
        int right = nums.length-1;
        int mid = 0;

        while(left <= right){
            mid = left + (right - left)/2;
            if(nums[mid] == target)
                return mid;

            if(nums[mid] < target){
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return left;

    }

    //74. Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {


        int rows = matrix.length;
        int columns = matrix[0].length;
        int l = 0;
        int r = rows * columns -1;
        int mid = 0;
        int i = 0;
        int j = 0;

        while(l<=r){
            mid = l + (r - l)/2;
            i = mid/columns;
            j = mid - i*columns;
            if(matrix[i][j] == target){
                return true;
            }
            if(matrix[i][j] < target)
                l = mid + 1;
            else r = mid -1;
        }

        return false;
    }

    //162. Find Peak Element
    public int findPeakElement(int[] nums) {
        //edge case
        if(nums == null || nums.length == 0 || nums.length == 1)
            return 0;
        if(nums.length == 2){
            if(nums[1]>nums[0])
                return 1;
            else return 0;
        }
        return findMid(nums, 0, nums.length-1);
    }

    private int findMid(int[] nums, int l, int r){

        //base case
        if(l>r){
            return -1;
        }
        int mid = l + (r-l)/2;
        if(mid+1<nums.length && mid-1>=0 && nums[mid]>nums[mid+1] && nums[mid]>nums[mid-1]){
            return mid;
        }
        if(mid-1<0 && nums[mid]>nums[mid+1]){
            return mid;
        }
        if(mid+1>nums.length-1 && nums[mid]>nums[mid-1]){
            return mid;
        }

        int left = findMid(nums, l, mid-1);
        if(left >= 0)
            return left;
        int right = findMid(nums, mid+1, r);
        return right;
    }

    //33. Search in Rotated Sorted Array
    public int search(int[] nums, int target) {

        //edge case
        if(nums == null || nums.length == 0){
            return -1;
        }
        if(nums.length == 1 && nums[0] != target){
            return -1;
        }

        //base case
        int l=0;
        int r=nums.length-1;
        int mid = 0;

        while(l<=r){
            mid = l + (r-l)/2;
            if(nums[mid] == target){
                return mid;
            }

            if(nums[mid]>=nums[l]){
                if(nums[mid]>target){
                    if(nums[l]<=target)
                        r = mid - 1;
                    else l = mid + 1;
                }else l = mid + 1;
            }
            else {
                if(nums[mid]>target){
                    r = mid - 1;
                }else {
                    if(nums[r]>=target)
                        l =  mid + 1;
                    else r = mid - 1;
                }
            }
        }

        return -1;
    }

    //34. Find First and Last Position of Element in Sorted Array
    public int[] searchRange(int[] nums, int target) {

        int[] result = {-1, -1};
        //edge case
        if(nums.length == 1){
            if(nums[0] == target)
            {
                result[0] = 0;
                result[1] = 0;
            }
            return result;
        }

        //base case
        int l = 0;
        int r = nums.length-1;
        int m = 0;

        while(l<=r){
            m = l + (r-l)/2;
            if(nums[m] == target){
                //define first position
                if(m>0 && nums[m] == nums[m-1]){
                    result[0] = searchFirstPosition(nums, target, l, m-1);
                }
                else result[0] = m;

                //define last position
                if(m<nums.length-1 && nums[m] == nums[m+1]){
                    result[1] = searchLastPosition(nums, target, m+1, r);
                }
                else result[1] = m;

                return result;
            }
            if(nums[m] < target){
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }

        return result;
    }

    private int searchFirstPosition(int[] nums, int target, int l, int r){
        int m = 0;
        while(l<=r){
            m = l + (r-l)/2;
            if(nums[m] == target){
                if(m>0 && nums[m] == nums[m-1]){
                    return searchFirstPosition(nums, target, l, m-1);
                }
                else return m;
            }
            if(nums[m] < target){
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }

        return -1;
    }

    private int searchLastPosition(int[] nums, int target, int l, int r){
        int m = 0;
        while(l<=r){
            m = l + (r-l)/2;
            if(nums[m] == target){
                if(m<nums.length-1 && nums[m] == nums[m+1]){
                    return searchLastPosition(nums, target, m+1, r);
                }
                else return m;
            }

            if(nums[m] < target){
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }

        return -1;
    }

    //153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {

        //edge case
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2){
            if(nums[0]>=nums[1])
                return nums[1];
            else return nums[0];
        }

        //base case
        int mid = 0;
        int l = 0;
        int r = nums.length - 1;

        if(nums[r]>nums[l])
            return nums[l];

        while(l<=r){
            mid = l + (r - l)/2;
            if(mid>=1 && nums[mid]<nums[mid-1]){
                return nums[mid];
            }
            if(nums[mid]>=nums[0]){
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }

        return nums[mid];
    }

    //4. Median of Two Sorted Arrays
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int mer = (m+n)/2;

        double result = 0.0;

        int l1 = -1;
        int r1 = -1;
        int l2 = -1;
        int r2 = -1;

        int d1 = 0;
        int d2 = 0;
        int d3 = 0;

        //check left edges
        if(nums1[0] < nums2[0]){
            l2 = 0;
            //find l1
            l1 = getEdgePosition(nums1, nums2[l2], false);
            //meridian in left
            if(mer<l1)
                return nums1[mer];
        }else {
            l1 = 0;
            //find l2
            l2 = getEdgePosition(nums2, nums1[l1], false);
            //meridian in left
            if(mer<l2)
                return nums2[mer];
        }

        d1 = Math.max(l1, l2);
        int position = mer - d1;
        //check right edges
        if(nums1[m-1] < nums2[n-1]){
            r1 = m - 1;
            //find r2
            r2 = getEdgePosition(nums2, nums1[r1], true);

            d2 = (r1-l1) + (r2-l2);
            //meridian in right
            if(d1+d2<mer){
                int diff = mer-d1-d2;
                return nums2[r2+diff];
            }
        }else {
            r2 = n - 1;
            //find r1
            r1 = getEdgePosition(nums1, nums2[r2], true);

            d2 = (r1-l1) + (r2-l2);
            //meridian in right
            if(d1+d2<mer){
                int diff = mer-d1-d2;
                return nums1[r1+diff];
            }
        }
        //meridian in intersection
        result = findValueInMergedArr(nums1, l1, r1, nums2, l2, r2, position);

        return result;
    }

    private int getEdgePosition(int[] nums, int target, boolean isRight){
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;

        while(l<=r){
          mid = l + (r-1)/2;
          if(nums[mid] == target){
              return mid;
          }
          if(nums[mid] < target){
              l = mid+1;
          }
          else {
              r = mid-1;
          }
        }

        return isRight?l-1:l;
    }

    //Merge two sorted arrays
    public int[] mergeArrays(int[] arr1, int[] arr2){
        int m = arr1.length;
        int n = arr2.length;
        int[] arr = new int[m+n];
        int ptr1 = 0;
        int ptr2 = 0;
        int i = -1;

        while(ptr1<=m-1 || ptr2<=n-1){
            i++;
            if(ptr1>m-1){
                arr[i] = arr2[ptr2];
                ptr2++;
                continue;
            }

            if(ptr2>n-1){
                arr[i] = arr1[ptr1];
                ptr1++;
                continue;
            }

            if(arr1[ptr1] == arr2[ptr2]) {
                arr[i] = arr1[ptr1];
                i++;
                arr[i] = arr1[ptr1];
                ptr1++;
                ptr2++;
            } else if(arr1[ptr1] < arr2[ptr2]) {
                arr[i] = arr1[ptr1];
                ptr1++;
            }
            else {
                arr[i] = arr2[ptr2];
                ptr2++;
            }
        }
        return arr;
    }

    //find value in merged two arrays by position
    public int findValueInMergedArr(int[] arr1, int l1, int r1, int[] arr2, int l2, int r2, int position){
        int m = r1 - l1;
        int n = r2 - l2;
        int mid = (m+n)/2;

        int ptr1 = l1;
        int ptr2 = l2;
        int i = -1;

        if(position<=mid){
            while(ptr1<=r1 || ptr2<=r2){
                i++;
                if(ptr1>r1){
                    if(i==position)
                        return arr2[ptr2];
                    ptr2++;
                    continue;
                }

                if(ptr2>r2){
                    if(i==position)
                        return arr1[ptr1];
                    ptr1++;
                    continue;
                }

                if(arr1[ptr1] == arr2[ptr2]) {
                    if(i==position || i+1==position)
                        return arr1[ptr1];
                    i++;
                    ptr1++;
                    ptr2++;
                } else if(arr1[ptr1] < arr2[ptr2]) {
                    if(i==position)
                        return arr1[ptr1];
                    ptr1++;
                }
                else {
                    if(i==position)
                        return arr2[ptr2];
                    ptr2++;
                }
            }
        }
        else {
            i=m+n+2;
            ptr1 = r1;
            ptr2 = r2;

            while(ptr1>=0 || ptr2>=0){
                i--;
                if(ptr1<0){
                    if(i==position)
                        return arr2[ptr2];
                    ptr2--;
                    continue;
                }

                if(ptr2<0){
                    if(i==position)
                        return arr1[ptr1];
                    ptr1--;
                    continue;
                }

                if(arr1[ptr1] == arr2[ptr2]) {
                    if(i==position || i-1==position)
                        return arr1[ptr1];
                    i--;
                    ptr1--;
                    ptr2--;
                } else if(arr1[ptr1] < arr2[ptr2]) {
                    if(i==position)
                        return arr2[ptr2];
                    ptr2--;
                }
                else {
                    if(i==position)
                        return arr1[ptr1];
                    ptr1--;
                }
            }
        }

        return -1;
    }

    //find value in merged two arrays by position
    public int findValueInMergedArr(int[] arr1, int[] arr2, int position){
        int m = arr1.length;
        int n = arr2.length;
        int mid = (m+n)/2;

        int ptr1 = 0;
        int ptr2 = 0;
        int i = -1;

        if(position<=mid){
            while(ptr1<=m-1 || ptr2<=n-1){
                i++;
                if(ptr1>m-1){
                    if(i==position)
                        return arr2[ptr2];
                    ptr2++;
                    continue;
                }

                if(ptr2>n-1){
                    if(i==position)
                        return arr1[ptr1];
                    ptr1++;
                    continue;
                }

                if(arr1[ptr1] == arr2[ptr2]) {
                    if(i==position || i+1==position)
                        return arr1[ptr1];
                    i++;
                    ptr1++;
                    ptr2++;
                } else if(arr1[ptr1] < arr2[ptr2]) {
                    if(i==position)
                        return arr1[ptr1];
                    ptr1++;
                }
                else {
                    if(i==position)
                        return arr2[ptr2];
                    ptr2++;
                }
            }
        }
        else {
            i=0;
            ptr1 = m-1;
            ptr2 = n-1;

            while(ptr1>=0 || ptr2>=0){
                i=i+1;
                if(ptr1<0){
                    if(i==position)
                        return arr2[ptr2];
                    ptr2--;
                    continue;
                }

                if(ptr2<0){
                    if(i==position)
                        return arr1[ptr1];
                    ptr1--;
                    continue;
                }

                if(arr1[ptr1] == arr2[ptr2]) {
                    if(m+n-i==position || m+n-i-1==position)
                        return arr1[ptr1];
                    i++;
                    ptr1--;
                    ptr2--;
                } else if(arr1[ptr1] < arr2[ptr2]) {
                    if(m+n-i==position)
                        return arr2[ptr2];
                    ptr2--;
                }
                else {
                    if(m+n-i==position)
                        return arr1[ptr1];
                    ptr1--;
                }
            }

        }

        return -1;
    }

    //9. Palindrome Number
    public boolean isPalindrome(int x) {
        String s = x+"";
        if(s == null || s.trim() == "")
            return true;

        int left = 0;
        int right = s.length()-1;

        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }else{
                left++;
                right--;
            }
        }

        return true;
    }


    //215. Kth Largest Element in an Array
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int val: nums){
            maxHeap.add(val);
        }
        int result = 0;
        for(int i=0; i<k; i++){
            result = maxHeap.poll();
        }
        return result;
    }

    //215. Kth Largest Element in an Array (Second solution)
    // Function to find the kth largest element in the array
    public int findKthLargest2(int[] nums, int k) {
        int n = nums.length;
        // Find the (n-k)th smallest element because the kth largest is also the (n-k)th smallest when sorted in ascending order
        return quickSelect(nums, 0, n - 1, n - k);
    }

    // Helper function to perform quick select
    private int quickSelect(int[] nums, int left, int right, int kSmallest) {
        // When the left and right pointers meet, we've found the kSmallest element
        if (left == right) {
            return nums[left];
        }

        // Initialize two pointers for the partitioning step
        int i = left - 1;
        int j = right + 1;

        // Choose pivot as the middle element
        int pivot = nums[(left + right) >>> 1];

        while (i < j) {
            // Move i right past any elements less than the pivot
            do {
                i++;
            } while (nums[i] < pivot);

            // Move j left past any elements greater than the pivot
            do {
                j--;
            } while (nums[j] > pivot);

            // Swap elements at i and j if they are out of order with respect to the pivot
            if (i < j) {
                swap(nums, i, j);
            }
        }

        // After partitioning, the pivot is now at index j
        // If we found the kSmallest element, return it
        if (j >= kSmallest) {
            return quickSelect(nums, left, j, kSmallest);
        }

        // Otherwise, continue the search in the right partition
        return quickSelect(nums, j + 1, right, kSmallest);
    }

    // Swap function to swap two elements in the array
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //373. Find K Pairs with Smallest Sums (not optimal solution)
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        int ptr1 = 0;
        int ptr2 = 0;

        List<List<Integer>> result = new ArrayList();
        while(ptr1<nums1.length || ptr2<nums2.length){
            List<Integer> list = new ArrayList();
            list.add(nums1[ptr1]);
            list.add(nums2[ptr2]);
            result.add(list);

            if(result.size() == k){
                return result;
            }

            if(ptr1+1<nums1.length && ptr2+1<nums2.length){
                if((nums1[ptr1+1]+nums2[ptr2])<=(nums1[ptr1]+nums2[ptr2+1]))
                    ptr1++;
                else ptr2++;
            } else if(ptr1+1<nums1.length){
                ptr1++;
            } else {
                ptr2++;
            }

            if(ptr1 == nums1.length)
            {
                ptr1 = 0;
                ptr2++;
            }

            if(ptr2 == nums2.length)
            {
                ptr2 = 0;
                ptr1++;
            }
        }

        return result;
    }

    //373. Find K Pairs with Smallest Sums (not optimal solution)
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> allPairs = new ArrayList();

        for(int i=0; i < nums1.length; i++){
            for(int j=0; j < nums2.length; j++){
                List<Integer> list = new ArrayList();
                list.add(nums1[i]);
                list.add(nums2[j]);
                allPairs.add(list);
            }
        }

        //selection sort
        for(int i=0; i<k; i++){
            int minIndex = i;

            for(int j=i+1; j < allPairs.size(); j++){
                if(getSum(allPairs.get(j))<getSum(allPairs.get(minIndex))){
                    minIndex = j;
                }
            }
            List<Integer> tempList = allPairs.get(i);
            allPairs.set(i, allPairs.get(minIndex));
            allPairs.set(minIndex, tempList);
        }
        List<List<Integer>> firstKElements = allPairs.stream().limit(k).collect(Collectors.toList());
        return firstKElements;
    }

    private Integer getSum(List<Integer> list){
        return list.get(0) + list.get(1);
    }

    //373. Find K Pairs with Smallest Sums
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // Create a priority queue to hold the arrays with a comparator to prioritize by the sum of pairs.
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Initialize the priority queue with the first k pairs from nums1 and the first element from nums2.
        for (int i = 0; i < Math.min(nums1.length, k); ++i) {
            queue.offer(new int[] {nums1[i] + nums2[0], i, 0});
        }

        // Prepare a list to store the k smallest pairs.
        List<List<Integer>> result = new ArrayList<>();

        // Keep polling from the priority queue to find the next smallest pair
        while (!queue.isEmpty() && k > 0) {
            // Poll the smallest sum pair from the priority queue.
            int[] currentPair = queue.poll();

            // Add the new pair [nums1[index1], nums2[index2]] to the result list.
            result.add(Arrays.asList(nums1[currentPair[1]], nums2[currentPair[2]]));

            // Decrease the remaining pairs count.
            --k;

            // If there's a next element in nums2, offer the next pair from nums1 and nums2 into the priority queue.
            if (currentPair[2] + 1 < nums2.length) {
                queue.offer(new int[] {nums1[currentPair[1]] + nums2[currentPair[2] + 1], currentPair[1], currentPair[2] + 1});
            }
        }

        // Return the list of k smallest pairs.
        return result;
    }

    //205. Isomorphic Strings
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            if(!map1.containsKey(s.charAt(i))){
                if(map2.containsKey(t.charAt(i)))
                    return false;
                map1.put(s.charAt(i), t.charAt(i));
                map2.put(t.charAt(i), s.charAt(i));
            }
            else if(!map1.get(s.charAt(i)).equals(t.charAt(i)))
                return false;
        }

        return true;
    }

    //290. Word Pattern
    public boolean wordPattern(String pattern, String s) {

        String[] arr = s.split(" ");
        if(arr.length != pattern.length())
            return false;
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();

        for(int i=0; i<arr.length; i++){
            if(!map1.containsKey(pattern.charAt(i))){
                if(map2.containsKey(arr[i]))
                    return false;
                map1.put(pattern.charAt(i), arr[i]);
                map2.put(arr[i], pattern.charAt(i));
            }
            else if(!map1.get(pattern.charAt(i)).equals(arr[i]))
                return false;
        }

        return true;
    }

    //242. Valid Anagram
    public boolean isAnagram(String s, String t) {
        if(s == null || t == null){
            return false;
        }
        if(s.length() != t.length()){
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        Character ch;
        for(int i=0; i<s.length(); i++){
            ch = s.charAt(i);
            if(!map.containsKey(ch)){
                map.put(ch, 1);
            }
            else{
                map.put(ch, map.get(ch) + 1);
            }
        }

        for(int i=0; i<t.length(); i++){
            ch = t.charAt(i);
            if(!map.containsKey(ch)){
                return false;
            }
            else{
                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) == 0){
                    map.remove(ch);
                }
            }
        }

        return map.isEmpty();
    }

    //202. Happy Number
    public boolean isHappy(int n) {
        // Initialize slow and fast pointers to detect cycle.
        int slowRunner = n;
        int fastRunner = getNext(n);

        // Loop until the two pointers meet or we find a happy number.
        while (slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);       // Move slow pointer by one step.
            fastRunner = getNext(getNext(fastRunner)); // Move fast pointer by two steps.
        }

        // If the slow runner reaches 1, then the number is happy.
        // If the pointers meet and it's not at 1, then a cycle is detected and the number is not happy.
        return slowRunner == 1;
    }

    //219. Contains Duplicate II
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Initialize a HashMap to store the value and its most recent index
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int currentIndex = 0; currentIndex < nums.length; ++currentIndex) {
            // Use getOrDefault to find the last index of the current number.
            // If it's not found, use a default value that is far away from any possible index.
            int lastIndex = indexMap.getOrDefault(nums[currentIndex], -1000000);

            // Check if the current index and the last index of the same value are within k steps
            if (currentIndex - lastIndex <= k) {
                // If so, we found a nearby duplicate and return true.
                return true;
            }

            // Update the map with the current value and its index for the next iteration checks
            indexMap.put(nums[currentIndex], currentIndex);
        }

        // If no nearby duplicates are found, return false.
        return false;
    }

    //49. Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        // Create a map to group the anagrams, where the key is the sorted string, and the value is a list of original strings.
        Map<String, List<String>> anagramsMap = new HashMap<>();

        // Iterate over each string in the array.
        for (String str : strs) {
            // Convert the string to a character array and sort it.
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);

            // Create a new string from the sorted character array.
            String sortedStr = String.valueOf(charArray);

            // If the sorted string key is not present in the map, initialize the list.
            // Then add the original string to the list associated with the sorted string key.
            anagramsMap.computeIfAbsent(sortedStr, key -> new ArrayList<>()).add(str);
        }

        // Return a new list containing all values from the map's lists,
        // effectively grouping all anagrams together.
        return new ArrayList<>(anagramsMap.values());
    }

    //15. 3Sum
    public List<List<Integer>> threeSum(int[] nums) {
        // Sort the array to make the two-pointer technique applicable
        Arrays.sort(nums);

        // Initialize the list to store the triplets
        List<List<Integer>> triplets = new ArrayList<>();

        // Get the length of the array
        int length = nums.length;

        // Iterate over the array, looking for the first element of the triplet
        for (int first = 0; first < length - 2 && nums[first] <= 0; ++first) {
            // Skip duplicate elements to avoid duplicate triplets
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            // Initialize the second and third pointers
            int second = first + 1;
            int third = length - 1;

            // Use two-pointer technique to find the remaining two elements
            while (second < third) {
                int sum = nums[first] + nums[second] + nums[third];

                // If the sum is less than zero, move the second pointer to the right
                if (sum < 0) {
                    ++second;
                }
                // If the sum is greater than zero, move the third pointer to the left
                else if (sum > 0) {
                    --third;
                }
                // If the sum is zero, we've found a valid triplet
                else {
                    triplets.add(List.of(nums[first], nums[second], nums[third]));

                    // Move the second pointer to the right and skip duplicates
                    while (second < third && nums[second] == nums[second + 1]) {
                        ++second;
                    }
                    // Move the third pointer to the left and skip duplicates
                    while (second < third && nums[third] == nums[third - 1]) {
                        --third;
                    }

                    // Move both pointers for the next potential triplet
                    ++second;
                    --third;
                }
            }
        }
        // Return the list of triplets
        return triplets;
    }

    //36. Valid Sudoku
    public boolean isValidSudoku(char[][] board) {
        // Arrays to check the validity of rows, columns, and sub-grids.
        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] subgrids = new boolean[9][9];

        // Iterate over the board by rows and columns.
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char currentChar = board[i][j];

                // If the current character is a dot, ignore and continue to the next iteration.
                if (currentChar == '.') {
                    continue;
                }

                // Convert char to its corresponding number (1-9).
                int number = currentChar - '0' - 1;

                // Calculate index for the subgrids.
                int subgridIndex = (i / 3) * 3 + j / 3;

                // Check if the number has already been recorded in the current row, column, or subgrid.
                if (rows[i][number] || columns[j][number] || subgrids[subgridIndex][number]) {
                    // If any is true, then the board is not valid.
                    return false;
                }

                // Mark the presence of the number in the current row, column, and subgrid.
                rows[i][number] = true;
                columns[j][number] = true;
                subgrids[subgridIndex][number] = true;
            }
        }

        // If no conflicts are found, the board is valid.
        return true;
    }

    //80. Remove Duplicates from Sorted Array II
    public int removeDuplicates2(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;

        int j = 0;
        int current = nums[0];
        int twoSize = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] == current){
                if(twoSize < 2){
                    twoSize++;
                    nums[j] = current;
                    j++;
                }
            }
            else{
                current = nums[i];
                twoSize = 1;
                nums[j] = current;
                j = j + 1;
            }
        }

        return j;
    }

    // Helper method to calculate the next number in the sequence.
    private int getNext(int number) {
        int sumOfSquares = 0;
        while (number > 0) {
            int digit = number % 10;               // Extract the last digit of the current number.
            sumOfSquares += digit * digit;         // Add the square of the extracted digit to the sum.
            number /= 10;                          // Remove the last digit from the current number.
        }
        return sumOfSquares;
    }

    //189. Rotate Array
    private int[] nums;

   /**
    * Rotates the given array to the right by k steps.
    * @param nums Array to be rotated.
    * @param k Number of steps to rotate the array to the right.
    */
    public void rotate(int[] nums, int k) {
        // Assign the input array to the class-level variable
        this.nums = nums;

        // Number of elements in the array
        int n = nums.length;

        // Normalize the number of steps k to avoid extra rotations
        k %= n;

        // Reverse the entire array
        reverse(0, n - 1);

        // Reverse the first part (up to k elements)
        reverse(0, k - 1);

        // Reverse the second part (from k to the end of the array)
        reverse(k, n - 1);
    }

    /**
     * Reverses elements in the array between indices i and j.
     * @param i Starting index for reversal.
     * @param j Ending index for reversal.
     */
    private void reverse(int i, int j) {
        // Using two pointers approach, swap elements until pointers meet or cross
        while (i < j) {
            // Temporary variable to hold a value during the swap
            int temp = nums[i];

            // Perform swap
            nums[i] = nums[j];
            nums[j] = temp;

            // Move pointers towards each other
            ++i;
            --j;
        }
    }

public int findKthLargest(int[] nums, int k) {
       int n = nums.length;
       // Find the (n-k)th smallest element because the kth largest is also the (n-k)th smallest when sorted in ascending order
       return quickSelect(nums, 0, n - 1, n - k);
   }

   // Helper function to perform quick select
    private int quickSelect(int[] nums, int left, int right, int kSmallest) {
        // When the left and right pointers meet, we've found the kSmallest element
        if (left == right) {
            return nums[left];
        }

        // Initialize two pointers for the partitioning step
        int i = left - 1;
        int j = right + 1;
        // Choose pivot as the middle element
        int pivot = nums[(left + right) >>> 1];

        while (i < j) {
            // Move i right past any elements less than the pivot
            do {
                i++;
            } while (nums[i] < pivot);

            // Move j left past any elements greater than the pivot
            do {
                j--;
            } while (nums[j] > pivot);

            // Swap elements at i and j if they are out of order with respect to the pivot
            if (i < j) {
                swap(nums, i, j);
            }
        }

        // After partitioning, the pivot is now at index j
        // If we found the kSmallest element, return it
        if (j >= kSmallest) {
            return quickSelect(nums, left, j, kSmallest);
        }

        // Otherwise, continue the search in the right partition
        return quickSelect(nums, j + 1, right, kSmallest);
    }

    // Swap function to swap two elements in the array
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //198. House Robber
    public int rob(int[] nums) {
        // f represents the max profit we can get from the previous house
        int prevNoRob = 0;
        // g represents the max profit we can get if we rob the current house
        int prevRob = 0;

        // Iterate over all the houses in the array
        for (int currentHouseValue : nums) {
            // Store max profit of robbing/not robbing the previous house
            int tempPrevNoRob = Math.max(prevNoRob, prevRob);

            // If we rob the current house, we cannot rob the previous one
            // hence our current profit is previous house's no-rob profit + current house value
            prevRob = prevNoRob + currentHouseValue;

            // Update the previous no-rob profit to be the best of robbing or not robbing the last house
            prevNoRob = tempPrevNoRob;
        }

        // Return the max profit we can get from the last house,
        // regardless of whether we rob it or not
        return Math.max(prevNoRob, prevRob);
    }

    // task 70
    public int climbStairs(int n) {
      int first = 0, second = 1;

      // Loop through number of steps n
      for (int i = 0; i < n; i++) {
          // Calculate next number in the series
           int next = first + second;

           // Update the previous two numbers for next iteration
           first = second;
           second = next;
       }

       // The 'second' variable holds the total ways to reach the top
       return second;
   }

    //139. Word Break
    public boolean wordBreak(String s, List<String> wordDict) {

        HashSet<String> hashSet = new HashSet<>(wordDict);
        int length = s.length();
        Boolean[] cache = new Boolean[length+1];

        return findWord(s, length,0, hashSet, cache);
    }

    private boolean findWord(String s, int length, int k, HashSet<String> hashSet, Boolean[] cache){
        if(k>=length)
            return true;
        if(cache[k] != null){
            return cache[k];
        }

        for(int i=k; i<length; i++){
            if(hashSet.contains(s.substring(k,i+1))){
                boolean found = findWord(s, length, i+1, hashSet, cache);
                cache[i+1]=found;
                if(found)
                    return true;
            }
        }

        return false;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        // Convert the list of words into a hash set for efficient look-up
        Set<String> wordSet = new HashSet<>(wordDict);

        // Get the length of the string 's'
        int strLength = s.length();

        // Initialize a boolean array to keep track of possible word breaks
        // f[i] is true if first i characters of the string can be segmented into dictionary words
        boolean[] wordBreakTracker = new boolean[strLength + 1];

        // Empty string is a valid decomposition
        wordBreakTracker[0] = true;

        // Check each substring starting from length 1 to strLength
        for (int i = 1; i <= strLength; ++i) {

            // Try different break points
            for (int j = 0; j < i; ++j) {

                // If the string up to j can be broken into valid words, and the substring from j to i is in the dictionary
                // Then, mark the position i as true in wordBreakTracker
                if (wordBreakTracker[j] && wordSet.contains(s.substring(j, i))) {
                    wordBreakTracker[i] = true;

                    // Break out of the loop since we have found a valid break point
                    break;
                }
            }
        }

        // The last entry in wordBreakTracker tells if the entire string can be segmented or not
        return wordBreakTracker[strLength];
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
