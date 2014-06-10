/*
 * Find the maximum sum subset in an array with negative integers.
 */ 

import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.lang.StringBuffer;

public class Solution {
    public static void SubsetSumMax(int[] array) {
        int[] storage = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (i > 0 && storage[i - 1] > 0) {
                storage[i] = array[i] + storage[i - 1];
            } else {
                storage[i] = array[i];
            }
        }

        int max = storage[0];
        for (int i = 1; i < storage.length; i++) {
            if (storage[i] > max) {
                max = storage[i];
            }
        }
        System.out.println(max);
    }

    public static void main(String args[] ) {
        int[] array = {1,-3,6,2,-7,8,-8,-6,4,3};
        SubsetSumMax(array);
    }
}
