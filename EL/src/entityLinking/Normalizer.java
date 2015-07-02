package entityLinking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Normalizer{
	private Trie trie;
	public Normalizer(String file) throws IOException{
		trie = new Trie();
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		Pattern pat = Pattern.compile("[\\w]+");		
		String line =null;
		while((line=br.readLine())!=null){
			Matcher mat =pat.matcher(line);
			ArrayList<String> entity = new ArrayList<String>();			
			while(mat.find()){
				entity.add(mat.group());
			}						
			trie.add(entity);			
		}
		br.close();
	}		
	
	private String changeEntityForm(ArrayList<String> entity){
		String res="";
		res=entity.get(0).toLowerCase();
		for(int i=1;i<entity.size();i++){
			res+="_"+entity.get(i).toLowerCase();
		}
		return res;
	}
	
	public void normalize(String file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));			
		String line =null;
		StringBuilder content = new StringBuilder(6000);
		while((line=br.readLine())!=null){
			content.append(line);
		}
		br.close();	
		
		Pattern pat = Pattern.compile("[\\w]+");		
		Matcher mat =pat.matcher(content.toString());
		ArrayList<String> tokens = new ArrayList<String>();
		ArrayList<String> entity = new ArrayList<String>();
		while(mat.find()){
			tokens.add(mat.group().toLowerCase());			
		}		
		for(int i=0;i<tokens.size();i++){
			entity.add(tokens.get(i));
			//System.out.println(tokens.get(i)+" :  "+entity.size());
			if(trie.matchPrefix(entity)==0){				
				//System.out.println(entity.get(0)+" :  "+entity.size());
				do{
					entity.remove(entity.size()-1);
					i--;
				}while(!trie.contains(entity) && !entity.isEmpty());
				if(!entity.isEmpty()) System.out.println(changeEntityForm(entity));
				entity = new ArrayList<>();
				i++;
			}
				
		}
		
			
					
	}		

}
