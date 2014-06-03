import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    public static void WordWrap(String str, int l) {
        String[] words = str.split(" ");
        int n = words.length;
        int[] m = new int[n];
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            int lCopy = l;
            int[] mTemp = new int[i + 1];
            for (int k = i; k >= 0; k--) {
                lCopy -= lCopy == l ? words[k].length() : words[k].length() + 1;
                if (lCopy < 0) {
                    mTemp[k] = -1;
                    break;
                }
                mTemp[k] = (k == 0 ? 0 : m[k - 1]) + (int)(Math.pow(lCopy, 3));
            }
            int min = -1;
            for (int k = i; k >= 0; k--) {
                if (mTemp[k] < 0) {
                    break;
                }
                if (min < 0 || mTemp[k] < min) {
                    min = mTemp[k];
                    s[i] = k;
                }
            }
            m[i] = min;
        }
        System.out.println(Arrays.toString(m));
        System.out.println(Arrays.toString(s));
        System.out.println(Arrays.toString(words));
        int nCopy = n - 1;
        StringBuffer sb = new StringBuffer();
        while (nCopy >= 0) {
            int start = s[nCopy];
            sb.insert(0, "\n");
            for (int k = nCopy; k > start; k--) {
                sb.insert(0, words[k]);
                sb.insert(0, " ");
            }
            sb.insert(0, words[start]);
            nCopy = s[nCopy] - 1;
        }
        System.out.print(sb.toString());
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        String s = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        WordWrap(s, 25);
    }
}
