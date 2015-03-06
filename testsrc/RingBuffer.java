import java.lang.RuntimeException;
import java.lang.InterruptedException;
import java.lang.IllegalArgumentException;
import java.util.Random;

public class RingBuffer {
    public double[] a;
    private int first;
    private int last;
    private int size;
    private final int capacity;

    /**
    * Creates a new buffer with given capacity
    */
    public RingBuffer(int capacity_) throws IllegalArgumentException {
        if (capacity_ < 1) {
            throw new IllegalArgumentException("You cannot create a RingBuffer of size less than 1.");
        }
        a = new double[capacity_];
        first = 0;
        last = 0;
        size = 0;
        capacity = capacity_;  
    }    

    /**
    * Returns the number of items currently in the buffer
    */
    public int size() {
        return size;
    }

    /**
    * Returns true if the buffer is empty
    */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
    * Returns true if the buffer is at full capacity
    */
    public boolean isFull() {
        return (size == capacity);
    }

    /**
    * Add item x to the end
    */
    public void enqueue(double x) throws RuntimeException {
        if (isFull()) throw new RuntimeException("You cannot enqueue: " + x + " onto a full RingBuffer.");
        else {
            a[last%capacity] = x;
            last = (last+1)%capacity;
            size++;
        }
    }

    /**
    * Delete and return item from the front
    */
    public double dequeue() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("You cannot dequeue from an empty RingBuffer.");
        } else {
            double frontElement = a[first];
            size--;
            first = (first+1)%capacity;
            return frontElement;
        }
    }

    /**
    * Return (but do not delete) item from the front
    */
    public double peek() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("You cannot peek at an empty RingBuffer.");
        } else {
            return a[first];
        }
    }

    /**
    * Test client, tests all methods
    */
    public static void main(String[] args) {
        final int CAPACITY = 100;
        RingBuffer test = new RingBuffer(CAPACITY);
        System.out.println("Size should start as zero: " + test.size());
        for (int i = 0; i < CAPACITY; i++) {
            System.out.println("Enqueing: " + i);
            test.enqueue((double) i);
        }
        while (!test.isEmpty()) {
            try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    System.out.println(ie);
                }
            System.out.println("Dequeuing: " + test.dequeue());
            // monte carlo requeue
            double monte_first = new Random().nextDouble();
            double monte_second = new Random().nextDouble();
            if (monte_first > monte_second) {
                double toAdd = (double) (int) (new Random().nextDouble() * 100);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    System.out.println(ie);
                }
                System.out.println("Enqueing: " + toAdd);
                test.enqueue(toAdd);
            } else {
                System.out.println("Peeking: " + test.peek());
            }
        }
    }
}
