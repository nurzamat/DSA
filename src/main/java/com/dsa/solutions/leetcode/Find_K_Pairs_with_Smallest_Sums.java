package com.dsa.solutions.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
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
}
