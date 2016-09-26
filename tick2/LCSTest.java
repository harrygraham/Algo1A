package uk.ac.cam.hg402.algorithms.tick2;

public class LCSTest {
	public static void main(String[] args){
		LCSBottomUp lcstest = new LCSBottomUp("ABBA", "CACA");
		int length = lcstest.getLCSLength();
		for (int[] i : lcstest.getLCSLengthTable()) {
			for (int j : i) {
				System.out.print(j + "\t");
			}
			System.out.println();
		}
		
		System.out.println(length);
		System.out.println(lcstest.getLCSString());
	}
}
