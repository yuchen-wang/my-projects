import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        // parse and store input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer strTok = new StringTokenizer(br.readLine(), " ");
        int K = 0, L = 0, M = 0;
        while (strTok.hasMoreElements()) {
            if (K == 0) {
                K = Integer.parseInt(strTok.nextElement().toString());
            } else if (L == 0) {
                L = Integer.parseInt(strTok.nextElement().toString());
            } else {
                M = Integer.parseInt(strTok.nextElement().toString());
            }
        }
        String s = br.readLine();
        
        boolean[] distinctCheck = new boolean[26];
        Hashtable<String, Integer> count = new Hashtable<String, Integer>();
        int max = 0, substrCount, distinctCount;
        for (int i = 0; i < N - K + 1; i++) {
            String substr = s.substring(i, i + K);
            
            // make sure substring satisfy the distinct condition
            distinctCount = 0;
            for (int j = 0; j < 26; j++) {
                distinctCheck[j] = false;
            }
            for (int j = 0; j < substr.length(); j++) {
                int letterIndex = substr.charAt(j) - 97;
                if (!distinctCheck[letterIndex]) {
                    distinctCount++;
                }
                distinctCheck[letterIndex] = true;
            }
            if (distinctCount > M) {
                i += distinctCount - M + 1;
                continue;
            }
            
            // keep track of occurence count of substraings
            substrCount = count.containsKey(substr) ? count.get(substr) + 1 : 1;
            count.put(substr, substrCount);
            if (substrCount > max) {
                max = substrCount;
            }
        }
        
        System.out.println(max);
    }
}
