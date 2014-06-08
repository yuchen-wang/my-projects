/*
 * Find subsets in array whose sum equal to a given number.
 * Example: S={1,3,5,2,7} and k=10, answer is {1,2,7},{3,5,2},{3,7}.
 */ 

import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.lang.StringBuffer;

public class Solution {
    public static void PrintSubset(int[] array, int k, int i, int[][] storage, LinkedList<Integer> subset) {
        if (k == 0) {
            System.out.println(Arrays.toString(subset.toArray()));
            return;
        }
        for (int j = i + 1; j < array.length + 1; j++) {
            if (storage[k][j] == 1) {
                subset.add(array[j - 1]);
                PrintSubset(array, k - array[j - 1], j, storage, subset);
                subset.removeLast();
            }
        }
    }
    public static void SubsetSum(int[] array, int k) {
        int length = array.length;
        int[][] storage = new int[k + 1][length + 1];
        for (int i = 1; i < k + 1; i++) {
            storage[i][0] = 0;
        }

        for (int i = 1; i < k + 1; i++) {
            for (int j = 1; j < length + 1; j++) {
                int number = array[j - 1];
                if (i - number == 0 || (i > number && storage[i - number][0] > j)) {
                    storage[i][j] = 1;
                    storage[i][0] = j;
                } else {
                    storage[i][j] = 0;
                }
            }
        }

        LinkedList<Integer> subset = new LinkedList<Integer>();
        PrintSubset(array, k, 0, storage, subset);
    }

    public static void main(String args[] ) {
        int[] array = {1,3,5,2,7,10,8,6,4};
        SubsetSum(array, 15);
    }
}
