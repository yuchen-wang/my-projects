/**
 * Given two words as Strings, determine if they are isomorphic. Two words are called isomorphic 
 * if the letters in one word can be remapped to get the second word. Remapping a letter means replacing all 
 * occurrences of it with another letter while the ordering of the letters remains unchanged. No two letters 
 * may map to the same letter, but a letter may map to itself. 
 * Example: 
 * given "foo", "app"; returns true 
 * we can map 'f' -> 'a' and 'o' -> 'p' 
 * given "bar", "foo"; returns false 
 * we can't map both 'a' and 'r' to 'o' 
 * given "turtle", "tletur"; returns true 
 * we can map 't' -> 't', 'u' -> 'l', 'r' -> 'e', 'l' -> 'u', 'e' -'r' 
 * given "ab", "ca"; returns true 
 * we can map 'a' -> 'c', 'b'
 */
import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    public static boolean Isomorphic(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s1Len != s2Len) {
            return false;
        }
        char[] s1Storage = new char[26];
        char[] s2Storage = new char[26];
        for (int i = 0; i < 26; i++) {
            s1Storage[i] = 'a' - 1;
            s2Storage[i] = 'a' - 1;
        }
        for (int i = 0; i < s1Len; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (s1Storage[c1 - 'a'] < 'a') {
                s1Storage[c1 - 'a'] = c2;
            } else if (s1Storage[c1 - 'a'] != c2) {
                return false;
            }
            if (s2Storage[c2 - 'a'] < 'a') {
                s2Storage[c2 - 'a'] = c1;
            } else if (s2Storage[c2 - 'a'] != c1) {
                return false;
            }
        }
        return true;
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        String s1 = "foo";
        String s2 = "app";
        String s3 = "foo";
        String s4 = "bar";
        String s5 = "turtle";
        String s6 = "tletur";
        System.out.println(Isomorphic(s1, s2));
        System.out.println(Isomorphic(s3, s4));
        System.out.println(Isomorphic(s5, s6));
    }
}
