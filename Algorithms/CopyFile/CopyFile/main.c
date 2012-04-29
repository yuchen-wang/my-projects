//
//  main.c
//  CopyFile
//
//  Created by Yuchen Wang on 12-04-28.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#include "main.h"
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

pthread_mutex_t myMutex;
pthread_cond_t myCond;
Node* src = NULL;
Node* srcTail = NULL;
Node* dst = NULL;
Node* dstTail = NULL;

void* work(void* argument)
{
    int* threadArgs = (int*)argument;
    printf("Copying file from PC%d to PC%d\n", threadArgs[0], threadArgs[1]);
    pthread_mutex_lock(&myMutex);
    Node* node1 = malloc(sizeof(Node));
    node1->id = threadArgs[0];
    Node* node2 = malloc(sizeof(Node));
    node2->id = threadArgs[1];
    
    node1->next = node2;
    node2->next = NULL;
    if (NULL != srcTail) {
        srcTail->next = node1;
    }
    srcTail = node2;
    if (NULL == src) {
        src = node1;
    }
    
    pthread_cond_signal(&myCond);
    pthread_mutex_unlock(&myMutex);
    return NULL;
}


int main (int argc, const char * argv[])
{
    pthread_mutex_init(&myMutex, NULL);
    pthread_cond_init(&myCond, NULL);
    pthread_t threads[100];
    int threadArgs[100][2];
    int i;
    Node* tmp;
    
    // Init source list
    src = malloc(sizeof(Node));
    src->id = 0;
    src->next = NULL;
    srcTail = src;
    
    // Init destination list
    dst = malloc(sizeof(Node));
    dst->id = 1;
    dst->next = NULL;
    
    tmp = dst;
    for (i = 2; i < 10; i++) {
        tmp->next = malloc(sizeof(Node));
        tmp->next->id = i;
        tmp->next->next = NULL;
        tmp = tmp->next;
    }
    dstTail = tmp;
    
    // Create threads
    pthread_mutex_lock(&myMutex);
    i = 0;
    while (NULL != dst) {
        tmp = src;
        printf("Source: ");
        while (tmp != NULL) {
            printf("%d ", tmp->id);
            tmp = tmp->next;
        }
        printf("\n");
        
        tmp = dst;
        printf("Destination: ");
        while (tmp != NULL) {
            printf("%d ", tmp->id);
            tmp = tmp->next;
        }
        printf("\n");
        
        while (NULL != src && NULL != dst) {            
            // Update source list
            tmp = src;
            if (srcTail == src) {
                src = NULL;
                srcTail = NULL;
            } else {
                src = src->next;
            }
            threadArgs[i][0] = tmp->id;
            free(tmp);
            
            // Update destination list
            tmp = dst;
            if (dstTail == dst) {
                dst = NULL;
                dstTail = NULL;
            } else {
                dst = dst->next;
            }
            threadArgs[i][1] = tmp->id;
            free(tmp);
            
            // Copy!
            pthread_create(&(threads[i]), NULL, work, (void*)(threadArgs[i]));
            i++;
        }
        pthread_cond_wait(&myCond, &myMutex);
    }
    pthread_mutex_unlock(&myMutex);
    
    for (i = 0; i < 100; i++) {
        pthread_join(threads[i], NULL);
    }
    return 0;
}

