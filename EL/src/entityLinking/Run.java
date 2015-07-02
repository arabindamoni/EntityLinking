package entityLinking;

import java.io.IOException;
import java.util.ArrayList;

public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		Normalizer norm = new Normalizer("data/entitylist");
	    norm.normalize("data/input");
		
	/*	Trie trie = new Trie(); 
		ArrayList<String> en = new ArrayList<>();
		en.add("tom"); 
		en.add("cruise");
		trie.add(en);
		en.add("tom");
		trie.add(en);
		System.out.println(trie.contains(en));
		*/
	}

}
