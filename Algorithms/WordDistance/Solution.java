/*
 * This class will be given a list of words (such as might be tokenized
 * from a paragraph of text), and will provide a method that takes two
 * words and returns the shortest distance (in words) between those two
 * words in the provided text. 
 * Example:
 *   WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the", "quick", "brown", "fox", "quick"));
 *   assert(finder.distance("fox","the") == 3);
 *   assert(finder.distance("quick", "fox") == 1);
 */ 

import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.lang.StringBuffer;

public class Solution {
    public static int WordDistance(String[] words, String s1, String s2) {
        int s1Index = -1;
        int s2Index = -1;
        int minDistance = -1;
        for (int i = 0; i < words.length; i++) {
            if (s1.equals(words[i])) {
                if (s2Index >= 0) {
                    if (minDistance < 0 || i - s2Index < minDistance) {
                        minDistance = i - s2Index;
                    }
                }
                s1Index = i;
            } else if (s2.equals(words[i])) {
                if (s1Index >= 0) {
                    if (minDistance < 0 || i - s1Index < minDistance) {
                        minDistance = i - s1Index;
                    }
                }
                s2Index = i;
            }
        }
        return minDistance;
    }

    public static void main(String args[] ) {
    	String[] words = {"the", "quick", "brown", "fox", "quick"};
        System.out.println(WordDistance(words, "fox", "the"));
        System.out.println(WordDistance(words, "quick", "fox"));
    }
}
