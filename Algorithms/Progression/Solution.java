import java.io.*;
import java.util.*;
import java.lang.Integer;
public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        String buff = br.readLine();
        StringTokenizer strTok = new StringTokenizer(buff, " ");
        int num = 0, lastNum = 0, lastNum2 = 0, diff = 0, out = 0;
        boolean first = true, second = false;
        while (strTok.hasMoreElements()) {
            num = Integer.parseInt(strTok.nextElement().toString());
            if (first) {
                first = false;
                second = true;
            } else if (second) {
                second = false;
                diff = num - lastNum;
            } else if (num - lastNum != diff) {
                out = lastNum + diff;
                if (strTok.hasMoreElements()) {
                    int temp_diff = num - lastNum;
                    int temp_num = Integer.parseInt(strTok.nextElement().toString());
                    if (temp_num - num == temp_diff) {
                        out = lastNum2 + temp_diff;
                    }
                }
                break;
            }
            lastNum2 = lastNum;
            lastNum = num;
        }
        
        System.out.println(out);
    }
}
