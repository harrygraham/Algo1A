
package uk.ac.cam.hg402.algorithms.tick1;

public interface MaxCharHeapInterface {
       // get the maximum value (or exception if empty)	
       public char getMax() throws EmptyHeapException;
       // insert a new value into the heap (or exception if full)
       public void insert(char i);
       // Get the number of items in the heap
       public int getLength();
}