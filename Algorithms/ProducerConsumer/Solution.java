import java.io.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.lang.Integer;

public class Solution {
    private int[] _buffer;
    private int _count;
    private int _index;
    private Lock _lock;
    private Condition _notEmptyCond;
    private Condition _notFullCond;

    public Solution(int size) {
        _buffer = new int[size];
        _count = 0;
        _index = 0;
        _lock = new ReentrantLock();
        _notEmptyCond = _lock.newCondition();
        _notFullCond = _lock.newCondition();

        for (int i = 0; i < _buffer.length; i++) {
            _buffer[i] = -1;
        }
    }

    public void printBuffer() {
        for (int i = 0; i < _buffer.length; i++) {
            System.out.print(_buffer[i] + " ");
        }
        System.out.println("\n");
    }

    public void produce(int id) throws InterruptedException {
        _lock.lock();
        try {
            if (_count == _buffer.length) {
                _notFullCond.await();
            } else {
                while (_buffer[_index] >= 0) {
                    _index++;
                    _index = _index % _buffer.length;
                }
                _buffer[_index] = id;
                printBuffer();
                _count++;
                _notEmptyCond.signal();
            }
        } finally {
            _lock.unlock();
        }
    }

    public void consume(int id) throws InterruptedException {
        _lock.lock();
        try {
            if (_count == 0) {
                _notEmptyCond.await();
            } else {
                while (_buffer[_index] < 0) {
                    _index++;
                    _index = _index % _buffer.length;
                }
                _buffer[_index] = -1;
                printBuffer();
                _count--;
                _notFullCond.signal();
            }
        } finally {
            _lock.unlock();
        }
    }

    class ProducerThread extends Thread {
        private int _id;
        ProducerThread(int id) {
            _id = id;
        }

        public void run() {
            try{
                for (;;) {
                    produce(_id);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    class ConsumerThread extends Thread {
        private int _id;
        ConsumerThread(int id) {
            _id = id;
        }

        public void run() {
            try{
                for (;;) {
                    consume(_id);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Solution s = new Solution(10);
        ConsumerThread[] c = new ConsumerThread[5];
        ProducerThread[] p = new ProducerThread[5];
        for (int i = 0; i < 5; i++) {
            c[i] = s.new ConsumerThread(i);
            c[i].start();
            p[i] = s.new ProducerThread(i + 5);
            p[i].start();
        }
    }
}
