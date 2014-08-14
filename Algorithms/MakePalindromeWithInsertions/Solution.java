/*
 * Given a string, insert least number of characters to make it a palindrome.
 */ 

import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.lang.StringBuffer;

public class Solution {
    public static void makePalindromeWithInsertions(String s) {
        int len = s.length();
        int[][] storage = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i; j++) {
                storage[i + j][j] = 0;
            }
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < len - j; i++) {
                if (s.charAt(i) == s.charAt(j + i)) {
                    storage[i][j + i] = storage[i + 1][j + i - 1];
                } else {
                    storage[i][j + i] = 1 + Math.min(storage[i][j + i - 1], storage[i + 1][j + i]);
                }
            }
        }

        int i = 0, j = len - 1;
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        while (i < j) {
            if (s.charAt(i) == s.charAt(j) && storage[i][j] == storage[i + 1][j - 1]) {
                sb1.append(s.charAt(i));
                sb2.insert(0, s.charAt(i));
                i++;
                j--;
            } else if (storage[i][j] == 1 + storage[i + 1][j]) {
                sb1.append(s.charAt(i));
                sb2.insert(0, s.charAt(i));
                i++;
            } else {
                sb1.append(s.charAt(j));
                sb2.insert(0, s.charAt(j));
                j--;
            }
        }

        System.out.println(sb1.toString() + (i == j ? s.charAt(i) : "") + sb2.toString());
    }

    public static void main(String args[] ) {
        makePalindromeWithInsertions("ababc");
        makePalindromeWithInsertions("abb");
        makePalindromeWithInsertions("AANKKNA");
    }
}
