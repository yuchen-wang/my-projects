import java.util.*;

public class Solution {
  public static final int MAXN = 6;
  public static final int MAX = 16;
   
  static void cnk(int[][] nk) {
    nk[0][0]=1;
    for(int k = 1; k <= MAXN; k++) {
      nk[0][k]=0;
    }
    for(int n =1; n <= MAXN; n++) {
      nk[n][0]=1;
      for(int k = 1; k <= MAXN; k++) {
        nk[n][k] = nk[n-1][k-1]+nk[n-1][k];
      }
    }
  }
   
  static void calc(int[][] T, int[] v) {
    T[0][0] = 1;
    for(int x =1; x <= MAX; x++) {
      T[0][x]=0;
    }
    int n = v.length;
    for(int ile = 1; ile <= n; ile++) {
      int a = v[ile-1];
      for(int x = 0; x <= MAX; x++) {
        T[ile][x] = T[ile-1][x];
        if(x>=a) {
          T[ile][x] +=T[ile-1][x-a];
        }
      }
    }
  }

  // MAIN
  static int howMany(int[] v) {
    int[][] B = new int[MAXN+1][MAX+1]; // [n pocz][sum]
    int[][] F = new int[MAXN+1][MAX+1];
    int[][] nk = new int[MAXN+1][MAXN+1];
    int n = v.length;
    cnk(nk);
    System.out.println("nk:");
    for (int i = 0; i < nk.length; i++) {
      System.out.println(Arrays.toString(nk[i]));
    }
    System.out.println("");
    Arrays.sort(v);
    for(int i =0; i < v.length/2; i++){
       int temp = v[i];
       v[i] = v[v.length-1 - i];
       v[v.length-1 - i] = temp;
    };
    calc(F, v);
    System.out.println("v:\n" + Arrays.toString(v) + "\nf:");
    for (int i = 0; i < F.length; i++) {
      System.out.println(Arrays.toString(F[i]));
    }
    System.out.println("");
    Arrays.sort(v);
    calc(B, v);
    System.out.println("v:\n" + Arrays.toString(v) + "\nb:");
    for (int i = 0; i < B.length; i++) {
      System.out.println(Arrays.toString(B[i]));
    }
    int res = 0;
    int done=0;
    while(done<n) {
      int p=done;
      while(p<n && v[p]==v[done]) {
        ++p;
      }
      int c=p-done;
      for(int u = 1; u <= c; u++) {
        int uu = u * v[done];
        for(int x = uu; x <= MAX; x++) {
          res += B[done][x-uu] * F[n-done-u][x] * nk[c][u];
          System.out.println("done=" + done + " x=" + x + " uu=" + uu + " u=" + u + " c=" + c);
          System.out.println("Adding B[done][x-uu]=" + B[done][x-uu] + " * F[n-done-u][x]=" + F[n-done-u][x] + " * nk[c][u]=" + nk[c][u]);
        }
        System.out.println("");
      }
      System.out.println("\n");
      done=p;
    }
    return res;
  }

  public static void main(String[] args) {
    int[] values = {2,2,2,2,3,5};
    System.out.println(howMany(values));
  }
};