package com.dsa.solutions;

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

    public static void main(String[] args){
        Solutions solutions = new Solutions();
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        solutions.merge(nums1, m, nums2, n);
    }
}
