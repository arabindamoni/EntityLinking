package entityLinking;

import java.io.IOException;
import java.util.ArrayList;

public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		Normalizer norm = new Normalizer("data/entitylist");
	    norm.normalize("data/input","data/output");			
	}

}
