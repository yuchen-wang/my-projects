/**
 * Given an integer array, find the element that appears more than half times.
 */
import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    public static int FindMoreThanHalf(int[] array) {
        int number = -1;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (count == 0) {
                number = array[i];
            }
            count = array[i] == number ? count + 1 : count - 1;
        }
        if (count > 0) {
            count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == number) {
                    count++;
                }
            }
        }
        return count > (array.length / 2) ? number : -1;
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int[] array = {2,2,2,2,3,3,2,2,2,3,4,4,3};
        System.out.println(FindMoreThanHalf(array));
    }
}
