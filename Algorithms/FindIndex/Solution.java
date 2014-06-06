/*
 * Given a sorted array and a number i, return the start and end index of the number i in the array.
 */
import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    public static void FindIndex(int[] array, int i) {
        int startIndex = BinarySearch(array, i, 0, array.length - 1, true);
        if (startIndex != -1) {
            int endIndex = BinarySearch(array, i, 0, array.length - 1, false);
            System.out.println(startIndex + " " + endIndex);
        } else {
            System.out.println("-1 -1");
        }

    }

    public static int BinarySearch(int[] array, int i, int begin, int end, boolean startIndex) {
        if (begin > end) {
            return -1;
        }
        if (begin == end) {
            return array[begin] == i ? begin : -1;
        }
        int mid = (begin + end) / 2;
        if (array[mid] == i) {
            if (startIndex ? (mid == 0 || array[mid - 1] != i) : (mid == array.length - 1 || array[mid + 1] != i)) {
                return mid;
            } else if (startIndex) {
                return BinarySearch(array, i, begin, mid - 1, startIndex);
            } else {
                return BinarySearch(array, i, mid + 1, end, startIndex);
            }
        } else if (array[mid] < i) {
            return BinarySearch(array, i, mid + 1, end, startIndex);
        } else {
            return BinarySearch(array, i, begin, mid - 1, startIndex);
        }
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int[] array = {3,3,3,3,3,8,10};
        FindIndex(array, 3);
        FindIndex(array, 5);
        FindIndex(array, 8);
        FindIndex(array, 10);
        FindIndex(array, 0);
        FindIndex(array, 2);
    }
}
