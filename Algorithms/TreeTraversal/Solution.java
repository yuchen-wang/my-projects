/**
 * Implement DFS for binary tree
 */
import java.io.*;
import java.util.*;
import java.lang.Integer;

public class Solution {
    private static class Node {
        public int _value;
        public Node _left;
        public Node _right;
        public Node(int value) {
            _value = value;
            _left = null;
            _right = null;
        }
    }

    public static void PostorderTraversal(Node root) {
        Deque<Node> stack = new ArrayDeque<Node>();
        stack.addFirst(root);
        Node lastPopped = null;
        while (stack.isEmpty() == false) {
            Node top = stack.peekFirst();
            if ((top._left == null && top._right == null) || top._left == lastPopped || top._right == lastPopped) {
                lastPopped = stack.removeFirst();
                System.out.print((char)(lastPopped._value + 'a' - 1) + " ");
            } else {
                if (top._right != null) {
                    stack.addFirst(top._right);
                }
                if (top._left != null) {
                    stack.addFirst(top._left);
                }
            }
        }
        System.out.print('\n');
    }

    public static void InorderTraversal(Node root) {
        Deque<Node> stack = new ArrayDeque<Node>();
        Node node = root;
        do {
            if (node != null) {
                stack.addFirst(node);
                node = node._left;
            } else {
                node = stack.removeFirst();
                System.out.print((char)(node._value + 'a' - 1) + " ");
                node = node._right;
            }
        } while (node != null || stack.isEmpty() == false);
        System.out.print('\n');
    }

    public static void PreorderTraversal(Node root) {
        Deque<Node> stack = new ArrayDeque<Node>();
        stack.addFirst(root);
        Node node;
        while (stack.isEmpty() == false) {
            node = stack.removeFirst();
            System.out.print((char)(node._value + 'a' - 1) + " ");
            if (node != null) {
                if (node._right != null) {
                    stack.addFirst(node._right);
                }
                if (node._left != null) {
                    stack.addFirst(node._left);
                }
            }
        }
        System.out.print('\n');
    }
    
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Solution s = new Solution();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        
        n6._left = n2;
        n6._right = n7;
        n2._left = n1;
        n2._right = n4;
        n4._left = n3;
        n4._right = n5;
        n7._right = n9;
        n9._left = n8;

        PreorderTraversal(n6);
        InorderTraversal(n6);
        PostorderTraversal(n6);
    }
}
