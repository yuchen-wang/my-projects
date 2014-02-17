/*
 * Shopkeeper want sells in the packs of 20, 9 and 6.
 * Given an n, you need to find whether its possible to buy the items or not.
 * For example n=21, you can buy 2 packs of 6 and one pack of 9.
 */ 

import java.io.*;
import java.util.*;
import java.lang.Integer;
public class Solution {
    public static boolean isPossible(int n) {
        if (n < 6) { return false; }
        if (n == 20 || n == 9 || n == 6) {
            return true;
        }
        return isPossible(n - 20) || isPossible(n - 9) || isPossible(n - 6);
    }

    public static boolean isPossibleDP(int n) {
        boolean[] dp = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (i + 1 == 20 || i + 1 == 9 || i + 1 == 6) {
                dp[i] = true;
            } else if (i + 1 - 20 >= 0 && dp[i + 1 - 20]) {
                dp[i] = true;
            } else if (i + 1 - 9 >= 0 && dp[i + 1 - 9]) {
                dp[i] = true;
            } else if (i + 1 - 6 >= 0 && dp[i + 1 - 6]) {
                dp[i] = true;
            } else {
                dp[i] = false;
            }
        }
        return dp[n - 1];
    }

    public static void main(String args[] ) {
        System.out.println(isPossibleDP(47));
        System.out.println(isPossibleDP(7));
    }
}
