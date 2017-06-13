import java.util.Arrays;
import java.util.HashMap;

public class LT370 {
	

	public int sum(int[] nums, int target, int pos) {
		if(target == 0) return 1;
		if(pos >= nums.length) return 0;
		int val = nums[pos];
		if(val > target) return sum(nums, target, pos+1);
		
		return sum(nums, target - val, pos) + sum(nums, target, pos+1);
	}
	
	private HashMap<Integer, Integer> map;
	public int combinationSum4(int[] nums, int target) {
		//map =new HashMap<>();
		Arrays.sort(nums);
		return sum(nums, target);
		
	}
	
	public int sum(int[] nums, int target) {
		//if(map.containsKey(target)) return map.get(target);
		int counter = 0;
		for(int i=0; i< nums.length; ++i) {
			if(nums[i]>target) break;
			
			else if(nums[i]==target) {
				++counter;
				break;
			}
			
			else counter += sum(nums, target - nums[i]);
		}
		//map.put(target, counter);
		return counter;
    }

	public static void main(String[] args) {
		int[] nums = {4,2 ,1};
		LT370 lt = new LT370();
		System.out.println(lt.combinationSum4(nums, 32));
	}

}
