import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    public static void Permutation(char[] letters) {
        PermutationRecurse(letters, 0);
    }

    public static void PermutationRecurse(char[] letters, int index) {
        int length = letters.length;
        if (length - index == 1) {
            System.out.println(Arrays.toString(letters));
        } else {
            for (int i = index; i < length; i++) {
                Swap(letters, index, i);
                PermutationRecurse(letters, index + 1);
                Swap(letters, index, i);
            }
        }
    }

    public static void Swap(char[] letters, int a, int b) {
        char temp = letters[a];
        letters[a] = letters[b];
        letters[b] = temp;
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        char[] letters = {'a','b','c'};
        Permutation(letters);
    }
}
