package com.gauts.exercises;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main app = new Main();
        app.sortedSquares(new int[]{-2});
        app.sortedSquares(new int[]{-2, 0});
        app.sortedSquares(new int[]{-2, 0, 1});
        app.sortedSquares(new int[]{-2, 0, 0, 1, 3, 10});
        app.sortedSquaresOptimal(new int[]{-2});
        app.sortedSquaresOptimal(new int[]{-2, 0});
        app.sortedSquaresOptimal(new int[]{-2, 0, 1});
        app.sortedSquaresOptimal(new int[]{-2, 0, 0, 1, 3, 10});
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
            if(!added)
                positiveSquareNums[positiveCount++] = negativeSquareNums[i];
        }
        System.out.println(Arrays.toString(positiveSquareNums));
    }

    public void sortedSquaresOptimal(int[] nums) {
        int[] squareNums = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        for (int k = nums.length -1; k >= 0; k--) {
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
}
