import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomizedSet {

	private final HashMap<Integer, Integer> map;
	private final ArrayList<Integer> array;
    public RandomizedSet() {
        map = new HashMap<Integer, Integer>();
        array = new ArrayList<>();
    }
    
    public boolean insert(int val) {
    	if(!map.containsKey(val)){
    		array.add(val);
    		map.put(val, array.size()-1);
    		return true;
    	}
    	return false;
    }
    
    public boolean remove(int val) {
        if(map.containsKey(val)){
        	int pos = map.remove(val);
        	if(pos!=array.size()-1) {
	        	int tmp = array.get(array.size()-1);
	        	array.set(pos, tmp);
	        	map.put(tmp, pos);
        	}
        	array.remove(array.size()-1);
        	return true;
        }
        return false;
    }
    
    public int getRandom() {
    	//Optional<Integer> val = array.stream().findAny();
    	//return val.orElse(0);
    	if(array.size()==0) return 0;
    	Random rand  = new Random();
    	return array.get(rand.nextInt(array.size()));
    }

	
	
	public static void main(String[] args) {
		
		
	}

}
