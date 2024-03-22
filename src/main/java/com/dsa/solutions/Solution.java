package com.dsa.solutions;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    //4. Median of Two Sorted Arrays
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int mer = (m+n)/2;
        int mod = (m+n)%2;

        if(mer==0){
            if(m>0)
                return nums1[0];
            else return nums2[0];
        }

        if(m==0 || n==0){
            if(m==0 && n==0)
                return 0;
            else if(m>0){
                if(mod>0)
                    return nums1[mer];
                else return ((double) nums1[mer] + (double) nums1[mer-1]) /2;
            }
            else {
                if(mod>0)
                    return nums2[mer];
                else return ((double) nums2[mer] + (double) nums2[mer-1]) /2;
            }
        }

        if(m==1 && n==1){
            return ((double) nums1[0] + (double) nums2[0]) /2;
        }
        else if(m==1){
            int pos = getTargetPosition(nums2, nums1[0]);
            if(mod>0){
                if(pos==mer)
                    return nums1[0];
                else if (pos<mer) {
                    return nums2[mer-1];
                }
                else return nums2[mer];
            }
            else {
                if(pos==mer)
                    return ((double) nums1[0] + (double) nums2[pos-1]) /2;
                else if (pos<mer) {
                    if(mer-1==pos)
                        return ((double) nums2[mer-1] + (double) nums1[0]) /2;
                    return ((double) nums2[mer-1] + (double) nums2[mer-2]) /2;
                }
                else return ((double) nums2[mer] + (double) nums2[mer-1]) /2;
            }
        } else if (n==1) {
            int pos = getTargetPosition(nums1, nums2[0]);
            if(mod>0){
                if(pos==mer)
                    return nums2[0];
                else if (pos<mer) {
                    return nums1[mer-1];
                }
                else return nums1[mer];
            }
            else {
                if(pos==mer)
                    return ((double) nums2[0] + (double) nums1[pos-1]) /2;
                else if (pos<mer) {
                    if(mer-1==pos)
                        return ((double) nums1[mer-1] + (double) nums2[0]) /2;
                    return ((double) nums1[mer-1] + (double) nums1[mer-2]) /2;
                }
                else return ((double) nums1[mer] + (double) nums1[mer-1]) /2;
            }
        }

        int l1 = -1;
        int r1 = -1;
        int l2 = -1;
        int r2 = -1;

        int d1 = 0;
        int d2 = 0;
        int d3 = 0;
        //check edge case
        if(nums1[m-1] <= nums2[0]){
            if(mod>0){
                if(mer<m)
                    return nums1[mer];
                else return nums2[mer-m];
            }
            else {
                if(mer<m)
                    return ((double) nums1[mer] + (double) nums1[mer-1]) /2;
                else if (mer == m) {
                    return ((double) nums2[mer-m] + (double) nums1[m-1])/2;
                } else return ((double) nums2[mer-m] + (double) nums2[mer-m-1])/2;
            }
        }
        else if(nums2[n-1] <= nums1[0]){
            if(mod>0){
                if(mer<n)
                    return nums2[mer];
                else return nums1[mer-n];
            }else{
                if(mer<n)
                    return ((double) nums2[mer] + (double) nums2[mer-1])/2;
                else if (mer==n) {
                    return ((double) nums1[mer-n] + (double) nums2[n-1])/2;
                } else return ((double) nums1[mer-n] + (double) nums1[mer-n-1])/2;
            }
        }

        //check left edges
        if(nums1[0] < nums2[0]){
            l2 = 0;
            //find l1
            l1 = getEdgePosition(nums1, nums2[l2], false);
            //meridian in left
            if(mer<l1) {
                if(mod>0)
                    return nums1[mer];
                else return ((double) nums1[mer] + (double) nums1[mer-1]) /2;
            }
        }else {
            l1 = 0;
            //find l2
            l2 = getEdgePosition(nums2, nums1[l1], false);
            //meridian in left
            if(mer<l2){
                if(mod>0)
                    return nums2[mer];
                else return ((double) nums2[mer] + (double) nums2[mer-1]) /2;
            }
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
            if(d2>0 && d1+d2<mer){
                int diff = mer-d1-d2;
                if(mod>0)
                    return nums2[r2+diff];
                else return ((double) nums2[r2+diff] + (double) nums2[r2+diff-1]) /2;
            }
        }else {
            r2 = n - 1;
            //find r1
            r1 = getEdgePosition(nums1, nums2[r2], true);

            d2 = (r1-l1) + (r2-l2);
            //meridian in right
            if(d2>0 && d1+d2<mer){
                int diff = mer-d1-d2;
                if(mod>0)
                    return nums1[r1+diff];
                else return ((double) nums1[r1+diff] + (double) nums1[r1+diff-1]) /2;
            }
        }

        //meridian in intersection
        List<Integer> list = findValueInMergedArr(nums1, l1, r1, nums2, l2, r2, position);
        if(mod>0)
            return list.get(0);
        else return ((double) list.get(0)+(double) list.get(1))/2;
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

    private int getTargetPosition(int[] nums, int target){
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;

        while(l<=r){
            mid = l + (r-l)/2;
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

        return l;
    }


    //find value in merged two arrays by position
    public  List<Integer> findValueInMergedArr(int[] arr1, int l1, int r1, int[] arr2, int l2, int r2, int position){
        int m = r1 - l1;
        int n = r2 - l2;
        int mid = (m+n)/2;

        int ptr1 = l1;
        int ptr2 = l2;
        int i = -1;

        List<Integer> list = new ArrayList<>();

        if(position<=mid){
            while(ptr1<=r1 || ptr2<=r2){
                i++;
                if(ptr1>r1){
                    if(i==position || i==position-1)
                        list.add(arr2[ptr2]);
                    ptr2++;
                    continue;
                }

                if(ptr2>r2){
                    if(i==position || i==position-1)
                        list.add(arr1[ptr1]);
                    ptr1++;
                    continue;
                }

                if(arr1[ptr1] == arr2[ptr2]) {
                    if(i==position || i+1==position || i==position-1)
                        list.add(arr1[ptr1]);
                    i++;
                    ptr1++;
                    ptr2++;
                } else if(arr1[ptr1] < arr2[ptr2]) {
                    if(i==position || i==position-1)
                        list.add(arr1[ptr1]);
                    ptr1++;
                }
                else {
                    if(i==position || i==position-1)
                        list.add(arr2[ptr2]);
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
                    if(i==position || i==position-1)
                        list.add(arr2[ptr2]);
                    ptr2--;
                    continue;
                }

                if(ptr2<0){
                    if(i==position || i==position-1)
                        list.add(arr1[ptr1]);
                    ptr1--;
                    continue;
                }

                if(arr1[ptr1] == arr2[ptr2]) {
                    if(i==position || i-1==position || i==position-1)
                        list.add(arr1[ptr1]);
                    i--;
                    ptr1--;
                    ptr2--;
                } else if(arr1[ptr1] < arr2[ptr2]) {
                    if(i==position || i==position-1)
                        list.add(arr2[ptr2]);
                    ptr2--;
                }
                else {
                    if(i==position || i==position-1)
                        list.add(arr1[ptr1]);
                    ptr1--;
                }
            }
        }

        return list;
    }
}


