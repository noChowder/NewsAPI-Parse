package task_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import task_1.ComplexParser;
import task_1.Parser;
import task_1.ParserChoice;
import task_1.ParserConstructVisitor;
import task_1.SimpleParser;
import task_1.WebParser;

public class TestCases {
	
	Logger logger = Logger.getLogger(TestCases.class.getName());
	
	File file1 = new File("inputs/newsapi.txt");
	File file2 = new File("inputs/simple.txt");
	
	private final PrintStream standardOut = System.out; 
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before 
	void setUp() { 
	    System.setOut(new PrintStream(outputStreamCaptor)); 
	} 
	 
	@After 
	void tearDown() { 
	    System.setOut(standardOut); 
	}
	
	@Test
	void testParserChoice() {
		ParserChoice complex = new ParserChoice(file1, null, logger);
		Parser parser = complex.chooseParser("file", "newsapi");
		Parser expected = new ComplexParser(file1, logger);
		assertEquals(parser, expected);
		
		ParserChoice simple = new ParserChoice(file2, null, logger);
		parser = simple.chooseParser("file", "simple");
		expected = new SimpleParser(file2, logger);
		assertEquals(parser, expected);
	}
	
	@Test
	void testWebParserChoice() {
		try {
			URL url = new URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=3b37320732d540f68e32219efb37ee49");
			ParserChoice web = new ParserChoice(null, url, logger);
			Parser parser = web.chooseParser("url", null);
			Parser expected = new WebParser(url, logger);
			
			assertEquals(parser, expected);
		} catch(IOException e) {
			logger.log(Level.SEVERE, "Error in TestCases.java. (testWebParserChoice)", e);
		}
	}
	
	@Test
	void testSimpleParserVisitor() {
		ParserChoice simple = new ParserChoice(file2, null, logger);
		Parser parser = simple.chooseParser("file", "simple");
		
		setUp();
		parser.accept(new ParserConstructVisitor());
		System.out.flush();
		tearDown();
		
		StringBuilder builder = new StringBuilder();
		builder.append("Title: Assignment #2");
		builder.append("\n");
		builder.append("at: 2021-04-16 09:53:23.709229");
		builder.append("\n");
		builder.append("URL: https://canvas.calpoly.edu/courses/55411/assignments/274503");
		builder.append("\n");
		builder.append("Extend Assignment #1 to support multiple sources and to introduce source processor.");
		String expected = builder.toString().trim();
		
		assertEquals(expected, outputStreamCaptor.toString().trim());
	}
	
	@Test
	void badSimpleParserVisitor() {
		ParserChoice simple = new ParserChoice(file1, null, logger);
		Parser parser = simple.chooseParser("non", "simple");
		assertEquals(null, parser);
		
		Exception exception = assertThrows(NullPointerException.class, () -> simple.chooseParser(null, "simple"));
		assertEquals(NullPointerException.class, exception.getClass());
	}
}
