package uk.ac.cam.hg402.algorithms.tick2;


import uk.ac.cam.rkh23.Algorithms.Tick2.*;

public class LCSBottomUp extends LCSFinder{

	public LCSBottomUp(String s1, String s2) {
		super(s1, s2);
		if(s1.length() ==0 || s2.length() == 0){
			this.mTable = null;
		}else{
			this.mTable = new int[s1.length()][s2.length()];
		}
	}

	@Override
	public int getLCSLength() {
		if(mTable == null){
			return 0;
		}else{
			for (int i = 0; i < mString1.length(); i++) {
				for (int j = 0; j < mString2.length(); j++) {
					if (mString1.charAt(i) == mString2.charAt(j)){
						mTable[i][j] = getTableElement(i-1, j-1) + 1;
					}
					else {
						mTable[i][j] = Math.max(getTableElement(i-1, j), getTableElement(i, j-1));
					}
				}
			}
			return mTable[mString1.length() -1][mString2.length() -1];
		}
	}
	
	private int getTableElement(int i, int j) {
		if (i < 0 || j < 0) {
			return 0;
		}
		return mTable[i][j];
	}

	@Override
	public String getLCSString() {
		if(mTable == null){
			return "";
		}else{
			String lCString = "";
			for (int x = mString1.length() -1, y = mString2.length()-1;
					x >= 0 && y>= 0; ) {

				if(mString1.charAt(x) == mString2.charAt(y)){
					lCString =  mString1.charAt(x) + lCString;
					x--;
					y--;
				}else{
					if (getTableElement(x, y) == getTableElement(x-1, y)){
						x--;}
					else if (getTableElement(x, y) == getTableElement(x, y-1)){
						y--;}

//					if(x-1 < 0 || y-1 <0){
//						x--;
//						y--;
//					}else{
//						if (mTable[x][y] == mTable[x-1][y]){
//							x--;}
//						else if (mTable[x][y] == mTable[x][y-1]){
//							y--;}
//					}
				}

			}
			return lCString;
		}
	}

	
}
