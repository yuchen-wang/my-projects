import java.util.*;

public class Solution {
  public static int quickSums(String s, int sum) {
    int len = s.length();
    int sumLen = String.valueOf(sum).length();
    int[][] storage = new int[len + 1][sum + 1];
    int[][] numbers = new int[len][len];
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        numbers[i][j] = (j >= i && j - i < 9) ? Integer.parseInt(s.substring(i, j + 1)) : -1;
      }
    }
    for (int i = 0; i < len + 1; i++) {
      for (int j = 0; j < sum + 1; j++) {
        storage[i][j] = -1;
      }
    }
    storage[0][0] = 0;
    for (int i = 1; i < len + 1; i++) {
      for (int j = 0; j < sum + 1; j++) {
        for (int k = i - 1; k >= 0; k--) {
          int subnumber = numbers[k][i - 1];
          if (subnumber < 0) {
            continue;
          }
          //System.out.println("subnumber=" + subnumber + " i=" + i + " j=" + j + " k=" + k);
          if (j - subnumber >= 0 && storage[k][j - subnumber] >= 0) {
            if (storage[i][j] == -1 || storage[k][j - subnumber] + 1 < storage[i][j]) {
              storage[i][j] = storage[k][j - subnumber] + (j - subnumber == 0 ? 0 : 1);
            }
          }
        }
      }
    }

    for (int i = 0; i < len + 1; i++) {
      //System.out.println(Arrays.toString(storage[i]));
    }

    return storage[len][sum];
  }

  public static void main(String[] args) {
    System.out.println(quickSums("56001", 12));
    System.out.println(quickSums("382834", 100));
    System.out.println(quickSums("9230560001", 71));
  }
};