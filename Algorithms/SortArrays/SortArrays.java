import java.util.*;

class MyElement implements Comparable<MyElement> {
    int value;
    int index;
    int arraysIndex;
    MyElement(int value, int index, int arraysIndex) {
        this.value = value;
        this.index = index;
        this.arraysIndex = arraysIndex;
    }
    
    public int compareTo(MyElement other) {
        return value == other.value ? 0 : (value < other.value ? -1 : 1);
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        sb.append(" ");
        sb.append(index);
        sb.append(" ");
        sb.append(arraysIndex);
        return sb.toString();
    }
}
public class SortArrays{
    static void sortArrays(ArrayList<int[]> arrays) {
        Queue<MyElement> q = new PriorityQueue<MyElement>(arrays.size());
        int size = 0;
        for (int i = 0; i < arrays.size(); i++) {
            if (arrays.get(i).length > 0) {
                q.add(new MyElement(arrays.get(i)[0], 0, i));
                size += arrays.get(i).length;
            }
        }
        System.out.println(q);
        
        int[] output = new int[size];
        int index = 0;
        MyElement top = null;
        while ((top = q.poll()) != null) {
            output[index++] = top.value;
            if (top.index < arrays.get(top.arraysIndex).length - 1) {
                q.add(new MyElement(arrays.get(top.arraysIndex)[top.index + 1], top.index + 1, top.arraysIndex));
            }
        }
        
        for (int i = 0; i < output.length; i++) {
            System.out.print(output[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String []args){
        int[] a = {1,7,9};
        int[] b = {2,10,17};
        int[] c = {-1, 1,2,4,7,11};
        int[] d = {};
        int[] e = {3,3,3};
        ArrayList<int[]> list = new ArrayList<int[]>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        sortArrays(list);
    }
}

