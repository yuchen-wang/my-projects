/*
 * Write a program to print inorder traversal of two trees.
 * Both values must be printed alternatively. 
 * E.g., if inorder traversal of tree 1 is 10, 15, 20 and tree 2 is 100, 150, 200,
 * then it should print 10, 100, 15, 150, 20, 200. 
 */

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct TreeNode TreeNode;
typedef struct StackNode StackNode;

struct TreeNode
{
    int n;
    TreeNode* left;
    TreeNode* right;
};

struct StackNode
{
    TreeNode* n;
    bool leftChildPushed;
    StackNode* next;
};

void printStack(StackNode* s, char* name)
{
    StackNode* temp = s;
    printf("%s Stack[%p]: ", name, temp);
    while (temp != NULL) {
        printf("[%d,%s,%d] ", temp->n->n, temp->leftChildPushed ? "yes" : "no", temp->next == NULL? -1 : temp->next->n->n);
        temp = temp->next;
    }
    printf("\n");
}

void traverTwoTrees(TreeNode* a, TreeNode* b)
{
    StackNode stackNodeA = {.n = a, .leftChildPushed = false, .next = NULL};
    StackNode* stackA = &stackNodeA;
    StackNode stackNodeB = {.n = b, .leftChildPushed = false, .next = NULL};
    StackNode* stackB = &stackNodeB;
    
    StackNode* stack = stackA;
    bool wasA = true;
    while (stackA != NULL || stackB != NULL) {
        while (stack != NULL) {
            if (stack->leftChildPushed == false && stack->n->left != NULL) {
                StackNode* stackNodeLeftChild = malloc(sizeof(StackNode));
                stackNodeLeftChild->n = stack->n->left;
                stackNodeLeftChild->leftChildPushed = false;
                stackNodeLeftChild->next = stack;
                stack->leftChildPushed = true;
                stack = stackNodeLeftChild;
            } else {
                printf("%d\n", stack->n->n);
                if (stack->n->right != NULL) {
                    StackNode* stackNodeRightChild = malloc(sizeof(StackNode));
                    stackNodeRightChild->n = stack->n->right;
                    stackNodeRightChild->leftChildPushed = false;
                    stackNodeRightChild->next = stack->next;
                    stack = stackNodeRightChild;
                } else {
                    stack = stack->next;
                }
                break;
            }
        }
        if (wasA) {
            stackA = stack;
        } else {
            stackB = stack;
        }
        stack = wasA ? stackB : stackA;
        wasA = !wasA;
    }
}

int main(int argc, const char * argv[])
{ 
    TreeNode f = {.n = 200, .left = NULL, .right = NULL};
    TreeNode e = {.n = 100, .left = NULL, .right = NULL};
    TreeNode d = {.n = 150, .left = &e, .right = &f};
    TreeNode c = {.n = 20, .left = NULL, .right = NULL};
    TreeNode b = {.n = 10, .left = NULL, .right = NULL};
    TreeNode a = {.n = 15, .left = &b, .right = &c};

    traverTwoTrees(&a, &d);
    return 0;
}

