/**
 * Find a subsequence of a given sequence in which the subsequence's elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible.
 */
import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    public static void LIS(int[] numbers) {
        int[] storage = new int[numbers.length];
        int storageLen = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (i == 0) {
                storage[i] = numbers[i];
                storageLen++;
            } else if (numbers[i] > storage[storageLen - 1]) {
                storage[storageLen] = numbers[i];
                storageLen++;
            } else {
                int hi = storageLen - 1;
                int lo = 0;
                while (hi > lo) {
                    int mid = (hi + lo) / 2;
                    if (storage[mid] > numbers[i]) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }
                if (storage[lo] > numbers[i]) {
                    storage[lo] = numbers[i];
                } else {
                    storage[lo + 1] = numbers[i];
                }
            }
        }

        int[] output = new int[storageLen];
        int j = storageLen - 1;
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (j == storageLen - 1) {
                if (numbers[i] == storage[j]) {
                    output[j] = numbers[i];
                    j--;
                }
            } else if (numbers[i] >= storage[j] && numbers[i] <= output[j + 1]) {
                output[j] = numbers[i];
                j--;
            }
            if (j < 0) {
                break;
            }
        }
        System.out.println(Arrays.toString(output));
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int[] numbers = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        LIS(numbers);
    }
}
