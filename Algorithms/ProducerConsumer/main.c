/**
 * Producer and consumer problem
 */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

typedef struct Node {
    int value;
    int id;
    struct Node* next;
} Node;

pthread_mutex_t myMutex;
pthread_cond_t producerCond;
pthread_cond_t consumerCond;
Node* head;
int size;

void* produce(void* argument)
{
    int* threadArgs = (int*)argument;
    int tid = threadArgs[0];
    Node* myHead = head;
    for(;;) {
        int i = 0;
        pthread_mutex_lock(&myMutex);
        while (i < size) {
            if (!myHead->value) {
                break;
            }
            i++;
            myHead = myHead->next;
        }
        if (i == size) {
            printf("producer %d is waiting...\n", tid);
            pthread_cond_wait(&producerCond, &myMutex);
            pthread_mutex_unlock(&myMutex);
        } else {
            printf("producer %d produced item %d...\n", tid, myHead->id);
            myHead->value = myHead->id;
            myHead = myHead->next;
            pthread_cond_signal(&consumerCond);
            pthread_mutex_unlock(&myMutex);
        }
    }
    return NULL;
}

void* consume(void* argument)
{
    int* threadArgs = (int*)argument;
    int tid = threadArgs[0];
    Node* myHead = head;
    for(;;) {
        int i = 0;
        pthread_mutex_lock(&myMutex);
        while (i < size) {
            if (myHead->value) {
                break;
            }
            i++;
            myHead = myHead->next;
        }
        if (i == size) {
            printf("consumer %d is waiting...\n", tid);
            pthread_cond_wait(&consumerCond, &myMutex);
            pthread_mutex_unlock(&myMutex);
        } else {
            printf("consumer %d consumed item %d...\n", tid, myHead->id);
            myHead->value = 0;
            myHead = myHead->next;
            pthread_cond_signal(&producerCond);
            pthread_mutex_unlock(&myMutex);
        }
    }
    return NULL;
}

int main (int argc, const char * argv[])
{
    pthread_mutex_init(&myMutex, NULL);
    pthread_cond_init(&producerCond, NULL);
    pthread_cond_init(&consumerCond, NULL);

    Node n10 = {0, 10, NULL};
    Node n9 = {0, 9, &n10};
    Node n8 = {0, 8, &n9};
    Node n7 = {0, 7, &n8};
    Node n6 = {0, 6, &n7};
    Node n5 = {0, 5, &n6};
    Node n4 = {0, 4, &n5};
    Node n3 = {0, 3, &n4};
    Node n2 = {0, 2, &n3};
    Node n1 = {0, 1, &n2};
    n10.next = &n1;
    head = &n1;
    size = 10;

    pthread_t pThreads[5];
    pthread_t cThreads[5];
    int pArgs[5][1];
    int cArgs[5][1];
    int i;
    
    for (i = 0; i < 5; i++) {
        pArgs[i][0] = i;
        cArgs[i][0] = i;
        pthread_create(&(pThreads[i]), NULL, produce, (void*)(pArgs[i]));
        pthread_create(&(cThreads[i]), NULL, consume, (void*)(cArgs[i]));
    }

    for (i = 0; i < 5; i++) {
        pthread_join(pThreads[i], NULL);
    }
    for (i = 0; i < 5; i++) {
        pthread_join(cThreads[i], NULL);
    }
    return 0;
}

