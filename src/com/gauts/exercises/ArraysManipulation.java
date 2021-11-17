package com.gauts.exercises;

import java.util.Arrays;

public class ArraysManipulation {

    public static void main(String[] args) {
        ArraysManipulation app = new ArraysManipulation();
//        app.sortedSquares(new int[]{-2});
//        app.sortedSquares(new int[]{-2, 0});
//        app.sortedSquares(new int[]{-2, 0, 1});
//        app.sortedSquares(new int[]{-2, 0, 0, 1, 3, 10});
//        app.sortedSquaresOptimal(new int[]{-2});
//        app.sortedSquaresOptimal(new int[]{-2, 0});
//        app.sortedSquaresOptimal(new int[]{-2, 0, 1});
//        app.sortedSquaresOptimal(new int[]{-2, 0, 0, 1, 3, 10});

//        app.duplicateZeros(new int[]{1});
//        app.duplicateZeros(new int[]{0,1});
//        app.duplicateZeros(new int[]{1,0,2,3,0,4,5,0});
//        app.duplicateZeros(new int[]{0,0,0});

//        app.merge(new int[]{2, 0, 0}, 1, new int[]{1, 3}, 2);
//        app.merge(new int[]{0}, 0, new int[]{2}, 1);
//        app.merge(new int[]{-2, 0, 4, 0, 0}, 3, new int[]{2, 3}, 2);
//        app.merge(new int[]{-24, -20, 0, 12, 73, 110, 112, 0, 0, 0, 0, 0, 0, 0, 0}, 7, new int[]{22, 28, 44, 55, 67, 74, 88, 100}, 8);

        System.out.println("k=" + app.removeElement(new int[]{3,2,2,3}, 3));
        System.out.println("k=" + app.removeElement(new int[]{0,1,2,2,3,0,4,2}, 2));
        System.out.println("k=" + app.removeElement(new int[]{3,3,3,3}, 3));
        System.out.println("k=" + app.removeElement(new int[]{}, 3));

        System.out.println("k=" + app.removeElementOptimal(new int[]{3,2,2,3}, 3));
        System.out.println("k=" + app.removeElementOptimal(new int[]{0,1,2,2,3,0,4,2}, 2));
        System.out.println("k=" + app.removeElementOptimal(new int[]{3,3,3,3}, 3));
        System.out.println("k=" + app.removeElementOptimal(new int[]{}, 3));

    }

    public void sortedSquares(int[] nums) {
        int[] positiveSquareNums = new int[nums.length];
        int[] negativeSquareNums = new int[nums.length];
        int negativeCount = 0;
        int positiveCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                negativeSquareNums[negativeCount++] = nums[i] * nums[i];
            } else {
                positiveSquareNums[positiveCount++] = nums[i] * nums[i];
            }
        }
        for (int i = 0; i < negativeCount; i++) {
            boolean added = false;
            for (int j = 0; j < positiveCount; j++) {
                if (negativeSquareNums[i] <= positiveSquareNums[j]) {
                    for (int k = positiveCount; k > j; k--) {
                        positiveSquareNums[k] = positiveSquareNums[k - 1];
                    }
                    positiveSquareNums[j] = negativeSquareNums[i];
                    positiveCount++;
                    added = true;
                    break;
                }
            }
            if (!added)
                positiveSquareNums[positiveCount++] = negativeSquareNums[i];
        }
        System.out.println(Arrays.toString(positiveSquareNums));
    }

    public void sortedSquaresOptimal(int[] nums) {
        int[] squareNums = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        for (int k = nums.length - 1; k >= 0; k--) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                squareNums[k] = nums[i] * nums[i];
                i++;
            } else {
                squareNums[k] = nums[j] * nums[j];
                j--;
            }
        }
        System.out.println(Arrays.toString(squareNums));
    }

    public void duplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                for (int j = arr.length - 1; j > i; j--) {
                    arr[j] = arr[j - 1];
                }
                if ((i + 1) < arr.length)
                    arr[++i] = 0;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int total = m + n;
        for (int i = 0; i < n; i++) {
            int val = nums2[i];
            boolean added = false;
            for (int j = 0; j < m; j++) {
                if (val < nums1[j]) {
                    int k = m;
                    while (k > j) {
                        nums1[k] = nums1[k - 1];
                        k--;
                    }
                    added = true;
                    nums1[j] = val;
                    if (m < total)
                        m++;
                    break;
                }
            }
            if(!added) {
                nums1[m] = val;
                if (m < total)
                    m++;
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The relative order of the elements may be changed.
     *
     * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
     *
     * Return k after placing the final result in the first k slots of nums.
     *
     * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int k = nums.length;
        k = findLast(nums, val, k);
        for(int i=0; i<k; i++){
            if(nums[i] == val){
                k = findLast(nums, val, k);
                if(k-1 > i){
                    nums[i] = nums[k-1];
                    nums[k-1] = val;
                }
            }
        }
        return k;
    }

    public int findLast(int[] nums, int val, int k){
        while(k>0 && nums[k-1] == val){
            k--;
        }
        return k;
    }

    public int removeElementOptimal(int[] nums, int val) {
        int resultantIndex = 0;
        for(int n : nums)
            if(n != val)
                nums[resultantIndex++] = n;
        return resultantIndex;
    }
}
