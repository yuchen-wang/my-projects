/**
 * Implement a method which takes an integer array and returns an integer array (of equal size) in
 * which each element is the product of every number in the input array with the exception of the
 * number at that index.
 *
 * Example:
 *   [3, 1, 4, 2] => [8, 24, 6, 12]
 */
import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    public static int[] SelfExcludingProduct(int[] numbers) {
        int[] output = new int[numbers.length];
        int numOfZero = 0;
        int product = 1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                numOfZero++;
            } else {
                product *= numbers[i];
            }
        }
        if (numOfZero > 1) {
            for (int i = 0; i < numbers.length; i++) {
                output[i] = 0;
            }
        } else if (numOfZero == 1) {
            for (int i = 0; i < numbers.length; i++) {
                output[i] = numbers[i] == 0 ? product : 0;
            }
        } else {
            for (int i = 0; i < numbers.length; i++) {
                output[i] = product / numbers[i];
            }
        }
        return output;
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int[] numbers = {3,1,4,2};
        System.out.println(Arrays.toString(SelfExcludingProduct(numbers)));
    }
}
