/*
 * Design multiple stacks in a single one big int array as efficient as possible
 */ 

import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.lang.StringBuffer;

public class Solution {
    private class Node {
        private int _value;
        private Node _next;
        public Node(int value) {
            _value = value;
        }
        public void setNext(Node next) {
            _next = next;
        }
        public Node getNext() {
            return _next;
        }
        public int getValue() {
            return _value;
        }
    }
    private int[] _array;
    private ArrayList<LinkedList<Integer>> _stackList;
    private LinkedList<Integer> _emptySpaceList;
    public Solution(int size) {
        _array = new int[size];
        for (int i = 0; i < size; i++) {
            _array[i] = -1;
        }
        _stackList = new ArrayList<LinkedList<Integer>>();
        _emptySpaceList = new LinkedList<Integer>();
        for (int i = 0; i < size; i++) {
            _emptySpaceList.add(i);
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Array: ");
        sb.append(Arrays.toString(_array));
        sb.append("\n");
        sb.append("Num of stacks: ");
        sb.append(size());
        sb.append("\n");
        for (int i = 0; i < size(); i++) {
            sb.append("Stack ");
            sb.append(i);
            sb.append(": ");
            Iterator<Integer> iter = _stackList.get(i).listIterator(0);
            while (iter.hasNext()) {
                sb.append(_array[iter.next()]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int size() {
        return _stackList.size();
    }

    public int createStack() {
        if (_emptySpaceList.size() == 0) {
            System.out.println("no more space");
            return -1;
        }
        LinkedList<Integer> newStack = new LinkedList<Integer>();
        Integer nextEmptySpace = _emptySpaceList.remove();
        newStack.add(nextEmptySpace);
        _stackList.add(newStack);
        return _stackList.size() - 1;
    }

    public void push(int id, int data) {
        if (id < 0 || id >= size()) {
            System.out.println("id does not exist");
            return;
        }
        if (_emptySpaceList.size() == 0) {
            System.out.println("no more space");
            return;
        }
        LinkedList<Integer> stack = _stackList.get(id);
        if (_array[stack.peek()] >= 0) {
            stack.addFirst(_emptySpaceList.remove());
        }
        _array[stack.peek()] = data;
    }

    public int pop(int id) {
        if (id < 0 || id >= size()) {
            System.out.println("id does not exist");
            return -1;
        }
        LinkedList<Integer> stack = _stackList.get(id);
        Integer outputIndex = stack.remove();
        _emptySpaceList.addFirst(outputIndex);
        int output = _array[outputIndex];
        _array[outputIndex] = -1;
        return output;
    }

    public int peek(int id) {
        if (id < 0 || id >= size()) {
            System.out.println("id does not exist");
            return -1;
        }
        LinkedList<Integer> stack = _stackList.get(id);
        return _array[stack.peek()];
    }
    
    public static void main(String args[] ) {
    	Solution s = new Solution(10);
        System.out.println(s.toString());
        int s0 = s.createStack();
        s.push(s0, 1);
        s.push(s0, 2);
        System.out.println(s.toString());
        int s1 = s.createStack();
        s.push(s1, 11);
        s.push(s1, 12);
        System.out.println(s.toString());
        s.pop(s0);
        System.out.println(s.toString());
        int s2 = s.createStack();
        s.push(s2, 21);
        System.out.println(s.toString());
        int s3 = s.createStack();
        int s4 = s.createStack();
        int s5 = s.createStack();
        int s6 = s.createStack();
        s.push(s3, 31);
        s.push(s4, 41);
        s.push(s5, 51);
        s.push(s6, 61);
        s.push(s4, 42);
        s.push(s4, 43);
        System.out.println(s.toString());
        s.push(s3, 32);
        s.pop(s4);
        System.out.println(s.toString());
        s.push(s3, 32);
        System.out.println(s.toString());
    }
}
