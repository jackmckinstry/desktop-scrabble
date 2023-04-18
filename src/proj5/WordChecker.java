package proj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordChecker {
    private static WordChecker instance = null;
    private ArrayList<String> ENGLISH_WORDS;

    private WordChecker() {
        // import words
    	File dictionary = new File("src/dictionary.txt");
        Scanner s = null;
		try { s = new Scanner(dictionary); }
		catch (FileNotFoundException e) { e.printStackTrace(); }
		
		ENGLISH_WORDS = new ArrayList<String>();
		
        while (s.hasNextLine()) {
          String word = s.nextLine();
          ENGLISH_WORDS.add(word);
        }
        
        s.close();
    }
  
    public static WordChecker getInstance() {
        if (instance == null) instance = new WordChecker();
  
        return instance;
    }
    
    boolean isValidWord(String s) {
    	return ENGLISH_WORDS.contains(s.toUpperCase());
    }
}
