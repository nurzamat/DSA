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
}
