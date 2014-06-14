import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    public static int FindSmallestDifference(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;
        int diff = -1;
        while (i < arr1.length && j < arr2.length) {
            if (diff < 0 || Math.abs(arr1[i] - arr2[j]) < diff) {
                diff = Math.abs(arr1[i] - arr2[j]);
            }
            if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return diff;
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int[] arr1 = {0,5,10};
        int[] arr2 = {3,6,8};
        System.out.println(FindSmallestDifference(arr1, arr2));
    }
}
