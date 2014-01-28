//
//  main.c
//  Longest Common Subsequence
//
//  Created by Yuchen Wang on 12-04-28.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void printLCS(char* seq1, size_t seq1Len, char* seq2, size_t seq2Len)
{
    size_t lcs[seq1Len + 1][seq2Len + 1];
    int i, j;
    
    printf("LCS: ");
    for (i = 0; i <= seq1Len; i++) {
        for (j = 0; j <= seq2Len; j++) {
            if (!i || !j) {
                lcs[i][j] = 0;
            } else if (seq1[i - 1] == seq2[j - 1]) {
                printf("%c", seq1[i - 1]);
                lcs[i][j] = 1 + lcs[i - 1][j - 1];
            } else {
                lcs[i][j] = lcs[i - 1][j] > lcs[i][j - 1] ? lcs[i - 1][j] : lcs[i][j - 1];
            }
        }
    }
    
    printf("\n");
    
}

int main(int argc, const char * argv[])
{
    char* seq1 = "abcdefgkn";
    char* seq2 = "cefhijkmn";
    printLCS(seq1, strlen(seq1), seq2, strlen(seq2));
    return 0;
}

