//
//  main.c
//  Longest Increasing Subsequence
//
//  Created by Yuchen Wang on 12-04-29.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#include <stdio.h>

void printLIS(int* seq, size_t seqLen)
{
    int lis[seqLen];
    int i, j, lisTemp;
    
    for (i = 0; i < seqLen; i++) {
        if (!i) {
            lis[i] = 1;
        } else {
            lisTemp = 0;
            for (j = 0; j < i; j++) {
                if (seq[j] <= seq[i] && lisTemp < lis[j]) {
                    lisTemp = lis[j];
                }
            }
            lis[j] = 1 + lisTemp;
        }
    }
    
    // print the sequence
    int lisIndex = -1;
    for (i = 0; i < seqLen; i++) {
        if (0 > lisIndex || lis[lisIndex] < lis[i]) {
            lisIndex = i;
        }
    }
    
    i = lisIndex;
    j = 1;
    int result[lisIndex + 1];
    result[0] = seq[lisIndex];
    while (0 <= --i) {
        if (1 == lis[lisIndex] - lis[i] && seq[i] <= seq[lisIndex]) {
            lisIndex = i;
            result[j++] = seq[i];
        }
    }
    
    printf("LIS: ");
    while (0 <= --j) {
        printf("%d ", result[j]);
    }
    printf("\n");
}

int main(int argc, const char * argv[])
{

    int seq[10] = {2,6,3,5,8,7,10,15,11,12};
    printLIS(seq, 10);
    return 0;
}

