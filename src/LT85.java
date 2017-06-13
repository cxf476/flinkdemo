import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LT85 {
	
	private char[][] matrix;

	private boolean hasValue(int row, int col) {
		return matrix[row][col] == '1';
	}
	public int maximalRectangle(char[][] matrix) {
		this.matrix = matrix;
		int rows = matrix.length;
		int cols = matrix[0].length;
		if(rows==0 || cols ==0)  return 0;
		
		int max=0;
		for(int i=0; i< rows; ++i) {
			int[] count = new int[cols];
			for(int l=i;l<rows;++l) {
				for(int j=0;j<cols;++j) {
					if(!hasValue(l,j)) count[j] =-1;
					else if(count[j]>=0)				++count[j];
				}
				
				int cur = 0;
				for(int j=0;j<cols;++j) {
					if(count[j]>0) {
						cur+=count[j];
						max = Math.max(max, cur);
					}
					else {
						cur =0;
					}
				}
			}
		}
		
		return max;
    }

	public static void main(String[] args) {
		char[][] matrix= {{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','1','0','1','1'}, {'1', '0', '0', '1','0'}};
		LT85 lt = new LT85();
		System.out.println(lt.maximalRectangle(matrix));
	}

}
