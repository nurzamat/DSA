package com.dsa.solutions;

import java.util.LinkedHashMap;
import java.util.Map;

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
        //special case
        if(m == 0){
            nums1[0] = nums2[0];
            return;
        }

        int i = n-1;
        int j = m-1;
        for(int k=m+n-1; k>0; k--){
            if(nums2[i] > nums1[j]){
                nums1[k] = nums2[i];
                i--;
            }
            else{
                nums1[k] = nums1[j];
                j--;
            }
        }
    }
}
