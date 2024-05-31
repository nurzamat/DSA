package com.dsa.solutions.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Find_K_Pairs_with_Smallest_Sums_373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> allPairs = new ArrayList();

        for(int i=0; i < nums1.length; i++){
            for(int j=0; j < nums2.length; j++){
                List<Integer> list = new ArrayList();
                list.add(nums1[i]);
                list.add(nums2[j]);
                allPairs.add(list);
            }
        }

        int n = allPairs.size();
        // Find the (n-k)th smallest element because the kth largest is also the (n-k)th smallest when sorted in ascending order
        return quickSelect(allPairs, 0, n - 1, n - k);
    }

    // Helper function to perform quick select
    private List<List<Integer>> quickSelect(List<List<Integer>> nums, int left, int right, int kSmallest) {
        // When the left and right pointers meet, we've found the kSmallest element
        if (left == right) {
            return nums.stream().limit(left).collect(Collectors.toList());
        }

        // Initialize two pointers for the partitioning step
        int i = left - 1;
        int j = right + 1;
        // Choose pivot as the middle element
        int pivot = getSum(nums.get((left + right) >>> 1));

        while (i < j) {
            // Move i right past any elements less than the pivot
            do {
                i++;
            } while (getSum(nums.get(i)) < pivot);

            // Move j left past any elements greater than the pivot
            do {
                j--;
            } while (getSum(nums.get(j)) > pivot);

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

    // Swap function to swap two elements in the list
    private void swap(List<List<Integer>> nums, int i, int j) {
        List<Integer> temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

    private Integer getSum(List<Integer> list){
        return list.get(0) + list.get(1);
    }

}
