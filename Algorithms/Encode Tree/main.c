//
//  main.c
//  Encode Tree
//
//  Created by Yuchen Wang on 14-01-27.
//

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct ListNode ListNode;
typedef struct TreeNode TreeNode;
typedef struct StackNode StackNode;

struct ListNode
{
    TreeNode* n;
    ListNode* next;
};

struct TreeNode
{
    char c;
    ListNode* n;
};

struct StackNode
{
    ListNode* n;
    StackNode* next;
};

typedef struct
{
    StackNode* top;
    bool isEmpty;
} Stack;

void push(Stack* s, ListNode* n)
{
    StackNode* sn = malloc(sizeof(StackNode));
    sn->n = n;
    sn->next = s->top;
    s->top = sn;
    s->isEmpty = false;
}

ListNode* pop(Stack* s)
{
    if (s->top == NULL) {
        return NULL;
    }
    StackNode* out = s->top;
    s->top = out->next;
    if (s->top == NULL) {
        s->isEmpty = true;
    }
    return out->n;
}

ListNode* top(Stack* s)
{
    return s->top->n;
}

void encodeTree(TreeNode* root, size_t size)
{
    Stack s = {.top = NULL, .isEmpty = true};
    int i = 0;
    char* string = malloc(3 * size + 1);

    ListNode lr = {.n = root, .next = NULL};
    push(&s, &lr);
    string[i++] = root->c;
    string[i++] = '(';
    while (!(s.isEmpty)) {
        if (string[i-1] == '(' && top(&s)->n->n != NULL) {
            push(&s, top(&s)->n->n);
            string[i++] = top(&s)->n->c;
            string[i++] = '(';
        } else {
            ListNode* temp = pop(&s);
            string[i++] = ')';
            if (temp->next != NULL) {
                push(&s, temp->next);
                string[i++] = temp->next->n->c;
                string[i++] = '(';
            }
        }
    }
    printf("%s\n", string);
    free(string);
}

void printList(ListNode* list)
{
    if (list == NULL) {
        printf("empty list\n");
    } else {
        printf("list: %c", list->n->c);
        list = list->next;
        while (list != NULL) {
            printf(" -> %c", list->n->c);
            list = list->next;
        }
        printf("\n");
    }
}

TreeNode* decodeTree(char* string)
{
    int i;
    TreeNode* root = malloc(sizeof(TreeNode));
    root->c = string[0];
    root->n = NULL;
    ListNode* list = malloc(sizeof(ListNode));
    list->n = root;
    list->next = NULL;
    
    for (i = 1; i < strlen(string); i++) {
        if (string[i] == ')') {
            ListNode* temp = list;
            list = list->next;
            free(temp);
        } else if (string[i] != '(') {
            TreeNode* tn = malloc(sizeof(TreeNode));
            tn->c = string[i];
            tn->n = NULL;

            // for putting it in the children list
            ListNode* ln = malloc(sizeof(ListNode));
            ln->n = tn;
            ln->next = NULL;
            if (list->n->n == NULL) {
                list->n->n = ln;
            } else {
                ListNode* child = list->n->n;
                while (child->next != NULL) {
                    child = child->next;
                }
                child->next = ln;
            }

            // for putting it in the path list
            ListNode* pln = malloc(sizeof(ListNode));
            pln->n = tn;
            pln->next = list;
            list = pln;
        }
    }
    return root;
}

int main(int argc, const char * argv[])
{
    TreeNode a = {.c = 'a', .n = NULL};
    TreeNode b = {.c = 'b', .n = NULL};
    TreeNode c = {.c = 'c', .n = NULL};
    TreeNode d = {.c = 'd', .n = NULL};
    TreeNode e = {.c = 'e', .n = NULL};
    TreeNode f = {.c = 'f', .n = NULL};
    TreeNode g = {.c = 'g', .n = NULL};

    ListNode lb = {.n = &b, .next = NULL};
    ListNode lc = {.n = &c, .next = NULL};
    ListNode ld = {.n = &d, .next = NULL};
    ListNode le = {.n = &e, .next = NULL};
    ListNode lf = {.n = &f, .next = NULL};
    ListNode lg = {.n = &g, .next = NULL};

    
    lb.next = &lc;
    lc.next = &ld;
    le.next = &lf;
    
    a.n = &lb;
    b.n = &le;
    c.n = &lg;
    
    TreeNode* root = decodeTree("a(b(e()f())c(g())d())");
    encodeTree(root, 7);
    return 0;
}

