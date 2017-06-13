import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }

public class LT297 {
	
	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	if(root == null) return "";
    	Queue<TreeNode> Q = new LinkedList<>();
    	Q.add(root);
    	
    	StringBuilder sbd = new StringBuilder();
    	while(!Q.isEmpty()) {
    		TreeNode node = Q.poll();
    		if(node != null)
    		{
    			sbd.append(node.val);
    			Q.offer(node.left);
    			Q.offer(node.right);
    		}
    		else {
    			sbd.append("null");
    		}
    		if(!Q.isEmpty()) sbd.append(",");
    	}
    	return sbd.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null ||data.isEmpty()) return null;
        
        Queue<TreeNode> Q= new LinkedList<>();
        String[] strs = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(strs[0]));
        Q.offer(root);
        int pos = 0;
        
        while(!Q.isEmpty()) {
        	TreeNode node = Q.poll();
        	if(++pos<strs.length) {
        		String val = strs[pos];
        		if(val.equals("null")) {
        			node.left=null;
        		} else {
        			node.left = new TreeNode(Integer.valueOf(val));
        			Q.offer(node.left);
        		}
        	}
        	
        	if(++pos<strs.length) {
        		String val = strs[pos];
        		if(val.equals("null")) {
        			node.right = null;
        		} else {
        			node.right = new TreeNode(Integer.valueOf(val));
        			Q.offer(node.right);
        		}
        	}
        }
        return root;
        
    }
    
	public static void main(String[] args) {
		
	}

}
