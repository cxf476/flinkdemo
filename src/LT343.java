
public class LT343 {

	public int memo[];
	
	public int max(int n) {
		if(memo[n]>0) return memo[n];
		if(n<=1) return 1;
		int val = 0;
		for(int i=1;i<=n/2;++i) {
			int tmp = Math.max(max(i), i) * Math.max(max(n-i), n-i);
			if(tmp>val) val =tmp;
        }
		memo[n] = val;
		return val;
	}
	
	public int integerBreak(int n) {
        memo = new int[n+1];
        memo[1] = 1;
        memo[2] = 1;
        int x = 1,y=1;
        for(int i=3;i<=n;++i) {
        	int left =Math.max(memo[x+1], x+1) * Math.max(memo[y], y);
        	int right = Math.max(memo[x], x) * Math.max(memo[y+1], y+1);
        	
        	if(left>right) ++x;
        	else	++y;
        	memo[i]=Math.max(left, right);
        }
        for(int k=1;k<=58;++k)
        {
        	System.out.print(memo[k]+",");
        }
        System.out.println();
       return memo[n];
    }

	public static void main(String[] args) {
		LT343 lt = new LT343();
		System.out.println(lt.integerBreak(58));
	}

}
