import java.util.*;
public class LT494 {
    Map<String, Integer> records = new HashMap<>();
    int[] nums;
    int[] sums;
    
    private int maxWays(int pos, int value) {
        if(pos >= nums.length) return value == 0 ? 1 : 0 ;
        System.out.println(sums[pos] + "at " +pos +" >< "+value);
        if(sums[pos] < Math.abs(value)) return 0;
        String key = String.format("%d,%d", pos, value);
        if(records.containsKey(key)) {
        	return records.get(key);
        }
        int positive = maxWays(pos + 1, value - nums[pos]);
        int negative = maxWays(pos + 1, value + nums[pos]);
        records.put(key, positive + negative);
        return positive + negative;
    }
        
    public int findTargetSumWays(int[] nums, int S) {
        Arrays.sort(nums);
        this.nums = nums;
        this.sums = new int[nums.length];
        for(int i=nums.length-1; i>=0;--i) {
            sums[i]=nums[i];
            if(i+1<nums.length) sums[i]+=sums[i+1];
        }
        return maxWays(0, S);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arrays = {2,107,109,113,127,131,137,3,2,3,5,7,11,13,17,19,23,29,47,53};
		int value = 2;
		LT494 lt = new LT494();
		System.out.println(lt.findTargetSumWays(arrays, value));
	}

}
