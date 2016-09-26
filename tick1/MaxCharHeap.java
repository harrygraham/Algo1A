package uk.ac.cam.hg402.algorithms.tick1;


import uk.ac.cam.rkh23.Algorithms.Tick1.MaxCharHeapInterface;
import uk.ac.cam.rkh23.Algorithms.Tick1.EmptyHeapException;

public class MaxCharHeap implements MaxCharHeapInterface{
	
	private char[] chararray;
	private int size;
	public MaxCharHeap(String s){
		
		String str = s.toLowerCase();
		chararray = str.toCharArray();
		size = chararray.length;
		for(int i = (size - 2) /2; i>=0 ; i--){
			heapify(chararray, chararray.length -1, i);
		}
	}

	@Override
	public char getMax() throws EmptyHeapException {
		if (size == 0) {
			throw new EmptyHeapException();
		}
		char max = chararray[0];
		chararray[0] = chararray[size -1];
		size--;
		heapify(chararray, size, 0);
		
		return max;
	}

	@Override
	public void insert(char i) {
		char[] newchararray = new char[++size];
		for (int j = 0; j < size-1; j++) {
			newchararray[j] = chararray[j];
		}
		newchararray[size-1] = Character.toLowerCase(i);
		
		chararray = newchararray;
		
		int current = size-1;
		while(chararray[current] > chararray[parent(current)]){
			swap(chararray, current, parent(current));
			current = parent(current);
		}
	}

	@Override
	public int getLength() {
		return size;
	}
	
	private int parent(int pos){
		return (pos - 1) / 2; 
	}
	private int left(int pos){
		return 2*pos + 1;
	}
	private int right (int pos){
		return 2*pos + 2;
	}
	private void swap(char[] a, int pos1, int pos2){
		char temp = a[pos1];
		a[pos1] = a[pos2];
		a[pos2] = temp;
	}
	
	private char[] heapify(char[] a, int iEnd, int iRoot){
		int j = iRoot;
		if (left(iRoot) <= iEnd && a[left(iRoot)] > a[iRoot]){
			j = left(iRoot);
		}
		if (right(iRoot) <= iEnd && a[right(iRoot)] > a[j]) {
			j = right(iRoot);
		}
		if (j == iRoot) {
			return a;
		}
		swap(a, iRoot, j);
		heapify(a, iEnd, j);
		return a;
	}
}
