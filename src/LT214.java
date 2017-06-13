import java.util.HashMap;

public class LT214 {

	public String s;
	//public HashMap<String, Boolean> memo = new HashMap<>();
	
	public boolean palindrom(int i, int j) {
		if(i>=j) return true;
		if(s.charAt(i)!=s.charAt(j)) return false;
		//Boolean ret = memo.get(i+ "|" + j);
		//if(ret!=null) return ret;
			
		boolean ret = palindrom(i+1, j-1);
		//memo.put(i+"|"+j, ret);
		return ret;
	}
	
	public String shortestPalindrome(String s) {
		StringBuilder sbd = new StringBuilder();
		this.s = s;
		for(int i=s.length()-1; i>=0; --i) {
			if(!palindrom(0, i)) {
				sbd.append(s.charAt(i));
			} else {
				sbd.append(s);
				break;
			}
		}
		return sbd.toString();
        
    }

	public static void main(String[] args) {
		LT214 solution = new LT214();
		StringBuilder sbd = new StringBuilder();
		for(int i=0;i<50000;++i) 
			sbd.append("a");
		System.out.println(solution.shortestPalindrome(sbd.toString()));
	}
}
