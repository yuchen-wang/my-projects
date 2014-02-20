/*
 * Flip a section of the array to get the maximum number of 1s
 */

import java.io.*;
import java.util.*;
import java.lang.Integer;
public class FlipArray {
    public static void main(String args[] ) throws Exception {
        int[] array = {1,0,0,0,1,0,0,0,1,1,1,1,1,0,0,1,0,0};
        int len = array.length;  
        int[][] results = new int[len][3];
        int resultIndex = 0;
        int leftZeroCount = 0, rightZeroCount = 0, oneCount = 0;
        boolean processingZeros = false;
        int i = 0;
        for (i = 0; i < len; i++) {
            if (array[i] == 0) {
                processingZeros = true;
                rightZeroCount++;
            } else {
                if (processingZeros) {
                    processingZeros = false;
                    if (leftZeroCount >= oneCount && rightZeroCount >= oneCount) {
                        // save indices
                        // L
                        results[resultIndex][0] = i - rightZeroCount - oneCount - leftZeroCount;
                        // R
                        results[resultIndex][1] = i - 1;
                        // diff
                        results[resultIndex][2] = rightZeroCount + leftZeroCount - oneCount;
                        resultIndex++;
                    } else if (rightZeroCount > 0) {
                        results[resultIndex][0] = i - rightZeroCount;
                        results[resultIndex][1] = i - 1;
                        results[resultIndex][2] = rightZeroCount;
                        resultIndex++;
                    }
                    leftZeroCount = rightZeroCount;
                    rightZeroCount = 0;
                    oneCount = 0;
                }
                oneCount++;
            }
        }
        
        // special case for when array ends with 0s
        if (leftZeroCount >= oneCount && rightZeroCount >= oneCount) {
            // save indices
            // L
            results[resultIndex][0] = i - rightZeroCount - oneCount - leftZeroCount;
            // R
            results[resultIndex][1] = i - 1;
            // diff
            results[resultIndex][2] = rightZeroCount + leftZeroCount - oneCount;
            resultIndex++;
        } else if (rightZeroCount > 0) {
            results[resultIndex][0] = i - rightZeroCount;
            results[resultIndex][1] = i - 1;
            results[resultIndex][2] = rightZeroCount;
            resultIndex++;
        }

        for (i = 0; i < resultIndex; i++) {
            System.out.println(results[i][0] + " " + results[i][1] + ":" + results[i][2]);
        }
        
        int max = 0, maxL = -1, maxR = -1, tempL = -1;
        int diff = 0;
        for (i = 0; i < resultIndex; i++) {
            if (i > 0 && results[i][0] < results[i - 1][1]) {
                diff += results[i][2] + results[i][0] - results[i - 1][1] - 1;
            } else {
                diff = results[i][2];
                tempL = results[i][0];
            }
            if (diff > max) {
                max = diff;
                maxL = tempL;
                maxR = results[i][1];
            }
        }
        
        System.out.println(maxL + " " + maxR + ":" + max);

        int output = 0;
        for (i = 0; i < len; i++) {
            if (i >= maxL && i <= maxR) {
                if (array[i] == 0) {
                    output++;
                }
            } else if (array[i] == 1) {
                output++;
            }
        }
        
        System.out.println(output);
    }
}
