import java.util.Comparator;
import java.util.PriorityQueue;

class MyComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer o1, Integer o2) {
		return o2 - o1;
	}
}
public class LT378 {

	public int kthSmallest(int[][] matrix, int k) {
		PriorityQueue<Integer> Q = new PriorityQueue<>(new MyComparator());
		for(int i=0;i<matrix.length;++i)
			for(int j=0;j<matrix[0].length;++j) {
				Q.offer(matrix[i][j]);
				if(Q.size()>k) Q.poll();
			}
		return Q.peek();
    }

	public static void main(String[] args) {
		
	}

}
