import java.util.*;
public class Solution {
  public static int minStrokes(String stripes) {
    int [][] mn = new int[stripes.length()][stripes.length()];
    for (int i = 0; i < stripes.length(); i++) mn[i][0] = 1;
    for (int len = 1; len < stripes.length(); len++) {
      for (int start = 0; start < stripes.length() - len; start++) {
        if (stripes.charAt(start) != stripes.charAt(start + len)) {
          //not same color
          mn[start][len] = 1 + Math.min(mn[start][len-1], mn[start+1][len-1]);
        } else {
          if (len >= 2) mn[start][len] = 1 + mn[start+1][len-2];
          else mn[start][len] = 1;
        }
      }
      
      //shorten
      for (int start = 0; start < stripes.length() - len; start++) {
        for (int l2 = 0; l2 < len; l2++) {
          System.out.println("start:" + start + " len:" + len + " mn[start][len]:" + mn[start][len] + " l2:" + l2 + " mn[start][l2]:" + mn[start][l2] + " mn[start + l2][len - l2]:" + mn[start + l2][len - l2]);
          if (mn[start][len] > mn[start][l2] + mn[start + l2][len - l2] - 1) {
            mn[start][len] = mn[start][l2] + mn[start + l2][len - l2] - 1;
          }
        }
      }
    }

    for (int i = 0; i < stripes.length(); i++) {
      System.out.println(Arrays.toString(mn[i]));
    }
    return mn[0][stripes.length()-1];
  }

  public static void main(String[] args) {
    System.out.println(minStrokes("BABCB"));
  }
}