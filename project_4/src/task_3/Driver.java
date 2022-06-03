package task_3;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import task_1.ParserChoice;
import task_1.ParserConstructVisitor;

public class Driver {
	
	 static Logger logger = Logger.getLogger(Driver.class.getName());
	
	public static void main(String[] args) {
		File file1 = new File("inputs/newsapi.txt");
		ParserChoice parserChoice = new ParserChoice("COMPLEX", file1, null, logger);
		parserChoice.accept(new ParserConstructVisitor());

		File file2 = new File("inputs/simple.txt");
		parserChoice = new ParserChoice("SIMPLE", file2, null, logger);
		parserChoice.accept(new ParserConstructVisitor());
		
		try {
			URL jsonUrl = new URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=3b37320732d540f68e32219efb37ee49");
			parserChoice = new ParserChoice("WEB", null, jsonUrl, logger);
			parserChoice.accept(new ParserConstructVisitor());
		} catch(IOException e) {
			logger.log(Level.SEVERE, "Error in Driver.java.", e);
		}
		
		parserChoice = new ParserChoice("badChoice", file1, null, logger);
		parserChoice.accept(new ParserConstructVisitor());
	}
}
