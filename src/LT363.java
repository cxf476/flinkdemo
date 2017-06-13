import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class LT363 {

	public static void main(String[] args) {
		int [][] a = {{1,2},{3,4}};
		int[][]b = a.clone();
		System.out.println(a[0][0]);
		b[0][0] = 100;
		System.out.println(a[0][0]);
		System.out.println(b[0][0]);
	}

}
