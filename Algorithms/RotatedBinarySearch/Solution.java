/*
 * There is rotated sorted array.Write the program to find any element in that array 
 * Original Array A={1,2,3,5,6,7,8} 
 * Rotated Array B={5,6,7,8,1,2,3} 
 * Write the program to find any element in array B.
 */
import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    public static int RotatedBinarySearch(int[] array, int num) {
        // find the rotation point first
        int start = 0;
        int end = array.length - 1;
        int rotationPt = -1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (array[mid] > array[0] && array[mid + 1] < array[0]) {
                rotationPt = mid;
                break;
            } else if (array[mid] > array[0]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (rotationPt < 0) {
            rotationPt = start;
        }

        // now do regular binary search
        if (num >= array[0]) {
            start = 0;
            end = rotationPt;
        } else {
            start = rotationPt + 1;
            end = array.length - 1;
        }
        while (start < end) {
            int mid = (start + end) / 2;
            if (array[mid] == num) {
                return mid;
            } else if (array[mid] > num) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (array[start] == num) {
            return start;
        } else {
            return -1;
        }
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int[] array = {5,6,7,8,1,2};
        System.out.println(RotatedBinarySearch(array, 5));
        System.out.println(RotatedBinarySearch(array, 6));
        System.out.println(RotatedBinarySearch(array, 7));
        System.out.println(RotatedBinarySearch(array, 8));
        System.out.println(RotatedBinarySearch(array, 1));
        System.out.println(RotatedBinarySearch(array, 2));
    }
}
