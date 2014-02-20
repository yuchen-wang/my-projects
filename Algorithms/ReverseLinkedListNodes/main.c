/*
 * Write a routine to reverse every k nodes in a given linked list without using additonal memory. 
 * input : 1,2,3,4,5,6 k:3 
 * output : 3,2,1,6,5,4
 */

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct ListNode ListNode;

struct ListNode
{
    int n;
    ListNode* next;
};

void reverseLinkedListNodes(ListNode* n, int k)
{
    ListNode* head = NULL;
    ListNode* current = n;
    ListNode* temp = NULL;
    ListNode* tail = NULL;
    ListNode* tempTail = NULL;

    int i;
    while (current != NULL) {
        tempTail = current;
        for (i = 0; i < k; i++) {
            temp = current->next;
            current->next = head;
            head = current;
            current = temp;
            if (current == NULL) {
                break;
            }
        }
        if (tail != NULL) {
            tail->next = head;
        }
        tail = tempTail;
        head = NULL;
    }
}

void printList(ListNode* list)
{
    if (list == NULL) {
        printf("empty list\n");
    } else {
        printf("list: %d", list->n);
        list = list->next;
        while (list != NULL) {
            printf(" -> %d", list->n);
            list = list->next;
        }
        printf("\n");
    }
}

int main(int argc, const char * argv[])
{
    ListNode f = {.n = 6, .next = NULL};
    ListNode e = {.n = 5, .next = &f};
    ListNode d = {.n = 4, .next = &e};
    ListNode c = {.n = 3, .next = &d};
    ListNode b = {.n = 2, .next = &c};
    ListNode a = {.n = 1, .next = &b};

    reverseLinkedListNodes(&a, 3);
    printList(&c);
    return 0;
}

