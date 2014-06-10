/*
 * Find the maximum sum subset in an array with negative integers.
 */ 

import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.lang.StringBuffer;

public class Solution {
    public static void SubsetProductMax(int[] array) {
        int[] storageP = new int[array.length];
        int[] storageN = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                int productP = storageP[i - 1] * array[i];
                int productN = storageN[i - 1] * array[i];
                storageP[i] = Math.max(Math.max(productP, productN), Math.max(array[i], 0));
                storageN[i] = Math.min(Math.min(productP, productN), Math.min(array[i], 0));
            }  else {
                storageP[i] = array[i] > 0 ? array[i] : 0;
                storageN[i] = array[i] < 0 ? array[i] : 0;
            }
        }

        int max = storageP[0];
        for (int i = 1; i < storageP.length; i++) {
            if (storageP[i] > max) {
                max = storageP[i];
            }
        }
        System.out.println(max);
    }

    public static void main(String args[] ) {
        int[] array = {2,3,4,-2,1,2,3};
        SubsetProductMax(array);
    }
}
