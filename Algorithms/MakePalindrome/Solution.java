/*
 * Shopkeeper want sells in the packs of 20, 9 and 6.
 * Given an n, you need to find whether its possible to buy the items or not.
 * For example n=21, you can buy 2 packs of 6 and one pack of 9.
 */ 

import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.lang.StringBuffer;

public class Solution {
    public static void makePalindrome(String s) {
        int[] count = new int[26];
        for (int i = 0; i < 26; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 97]++;
        }
        StringBuffer sb = new StringBuffer();
        boolean seenOdd = false;
        int insertIndex = 0;
        char oddChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                if (seenOdd) {
                    System.out.println("N/A");
                    return;
                } else {
                    count[i]--;
                    oddChar = (char)(i + 97);
                }
            }
            for (int k = 0; k < count[i]; k++) {
                sb.insert(insertIndex, (char)(i + 97));
            }
            insertIndex += count[i] / 2;
        }
        if (oddChar != 0) {
            sb.insert(insertIndex, oddChar);
        }
        System.out.println(sb.toString());
    }

    public static void main(String args[] ) {
        makePalindrome("aab");
    }
}
