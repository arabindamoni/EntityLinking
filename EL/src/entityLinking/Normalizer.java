package entityLinking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
	
	public void normalize(String file,String outfile) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));			
		String line =null;
		StringBuilder content = new StringBuilder(6000);
		while((line=br.readLine())!=null){
			content.append(line);
		}
		br.close();	
		
		FileWriter fout = new FileWriter(outfile);
		
		String sents[] = content.toString()
				.split("\n|((?<!\\d)\\.(?!\\d))");
		Pattern pat = Pattern.compile("[\\w]+");
		for(String sentence:sents){
		Matcher mat =pat.matcher(sentence);
		ArrayList<String> tokens = new ArrayList<String>();
		ArrayList<String> entity = new ArrayList<String>();
		while(mat.find()){
			tokens.add(mat.group().toLowerCase());			
		}		
		for(int i=0;i<tokens.size();i++){
			entity.add(tokens.get(i));			
			if(trie.matchPrefix(entity)==0){								
				do{
					entity.remove(entity.size()-1);
					i--;
				}while(!trie.contains(entity) && !entity.isEmpty());
				if(!entity.isEmpty()){
					String entitystr = changeEntityForm(entity);
System.out.println(entitystr);
					fout.write(" [");
					while(!entity.isEmpty()){
						fout.write(" "+entity.remove(0));						
					}
					fout.write("| "+entitystr+"]");
					i--;
				}
				else{
					fout.write(" "+tokens.get(i+1));
				}							
				entity = new ArrayList<>();				
				i++;
			}
				
		}
		fout.write(".");
		}
		fout.close();								
	}		

}
