/**
 * Find the longest subsequence common to the given sequences.
 */
import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    public static void LCS(String str, String str2) {
        int len = str.length();
        int len2 = str2.length();
        int[][] storage = new int[len + 1][len2 + 1];
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    storage[i][j] = 0;
                } else if (str.charAt(i - 1) == str2.charAt(j - 1)) {
                    storage[i][j] = 1 + storage[i - 1][j - 1];
                } else {
                    storage[i][j] = Math.max(storage[i - 1][j], storage[i][j - 1]);
                }
            }
        }

        int i = len;
        int j = len2;
        StringBuffer sb = new StringBuffer();
        while (storage[i][j] > 0) {
            if (str.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.insert(0, str.charAt(i - 1));
                i--;
                j--;
            } else if (storage[i - 1][j] > storage[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println(sb.toString());
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        String str = "57169";
        String str2 = "75691";
        LCS(str, str2);
    }
}
