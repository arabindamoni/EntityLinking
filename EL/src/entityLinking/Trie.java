package entityLinking;

import java.util.ArrayList;
import java.util.HashMap;

public class Trie {
	private class Node{
		boolean valid =false;
		HashMap<String,Node> next = new HashMap<String,Node>();		
	}
	
	Node root = new Node();
	
	public void add(ArrayList<String> str){
		Node node = root;
		for(String token: str){
			if(!node.next.containsKey(token)){				
				node.next.put(token,new Node());				
			}			
			node = node.next.get(token);							
		}
		node.valid = true;		
	}
	
	public boolean contains(ArrayList<String> str){
		Node node = root;
		for(String token: str){			
			if(!node.next.containsKey(token)){
				return false;
			}			
			node = node.next.get(token);			
		}	
		return node.valid;
	}
	
	public int matchPrefix(ArrayList<String> str){
		Node node = root;
		int depth = 0;
		for(String token: str){
			if(!node.next.containsKey(token)){				
				break;
			}
			else{
				node = node.next.get(token);
				depth++;				
			}
		}
		return (str.size()>depth)?0:depth;
	}
	
}
