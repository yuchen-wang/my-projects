/*
 * Given a set of intervals like 5-10, 5-10, 8-12, 9-15.
 * Find the ith smallest number in these intervals.
 */ 

import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.lang.StringBuffer;

public class Solution {
    class Interval {
        public int start;
        public int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    class IntervalPoint implements Comparable<IntervalPoint> {
    	public int data;
    	public boolean start;
    	public IntervalPoint(int data, boolean start) {
    		this.data = data;
    		this.start = start;
    	}
    	
    	@Override
        public int compareTo(IntervalPoint other){
            if (data == other.data) {
            	if (start == other.start) {
            		return 0;
            	}
            	return start ? -1 : 1;
            }
            return data < other.data ? -1 : 1;
        }
    	
    	public String toString() {
    		return "(" + data + "," + start + ")";
    	}
    }

    int findIthInIntervals(Interval[] intervals, int i) {
    	IntervalPoint[] pts = new IntervalPoint[intervals.length * 2];
        for (int j = 0; j < intervals.length; j++) {
            pts[j * 2] = this.new IntervalPoint(intervals[j].start, true);
            pts[j * 2 + 1] = this.new IntervalPoint(intervals[j].end, false);
        }
        Arrays.sort(pts);
        
        int multiplier = 0;
        int previous = 0;
        int k = i - 1;
        for (int j = 0; j < pts.length; j++) {
        	if (j > 0) {
        		if (previous != pts[j].data) {
	        		int diff = multiplier * (pts[j].data - previous);
	        		if ((k - diff) <= 0) {
	        			return previous + k / multiplier;
	        		}
	        		k -= diff;
        		} 
        		if (pts[j].start == false) {
        			k--;
        			if (k == 0) {
        				return pts[j].data;
        			}
        		}
        	}
        	multiplier = pts[j].start ? multiplier + 1 : multiplier - 1;
        	previous = pts[j].data;
        }
        return 0;
    }
    
    public static void main(String args[] ) {
    	Solution s = new Solution();
        Solution.Interval[] intervals = new Interval[5];
        intervals[0] = s.new Interval(3,5);
        intervals[1] = s.new Interval(5,10);
        intervals[2] = s.new Interval(5,10);
        intervals[3] = s.new Interval(8,12);
        intervals[4] = s.new Interval(9,15);
        for (int i = 1; i < 28; i++) {
        	System.out.println(s.findIthInIntervals(intervals, i));
        }
    }
}
