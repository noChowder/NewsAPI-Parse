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
	void testFiltering() {
		setUp();
		ParserChoice parserChoice = new ParserChoice("COMPLEX", file1, null, logger, "CNN");
		parserChoice.accept(new ParserConstructVisitor());
		System.out.flush();
		tearDown();
		
		StringBuilder builder = new StringBuilder();
		builder.append("Title: The latest on the coronavirus pandemic and vaccines: Live updates - CNN\n");
		builder.append("at: 2021-03-24T22:32:00Z\n");
		builder.append("URL: https://www.cnn.com/world/live-news/coronavirus-pandemic-vaccine-updates-03-24-21/index.html\n");
		builder.append("The coronavirus pandemic has brought countries to a standstill. Meanwhile, vaccinations have already started in some countries as cases continue to rise. Follow here for the latest.\n");
		builder.append(System.getProperty("line.separator"));
		builder.append("Title: People line the streets of Boulder to honor slain police Officer Eric Talley - CNN\n");
		builder.append("at: 2021-03-24T22:20:12Z\n");
		builder.append("URL: https://www.cnn.com/2021/03/24/us/officer-talley-procession/index.html\n");
		builder.append("Boulder, Colorado, continued to mourn fallen Officer Eric Talley on Wednesday.\n");
		String expected = builder.toString().trim();
		
		assertEquals(expected, outputStreamCaptor.toString().trim());
	}
	
	@Test
	void testFiltering2() {
		setUp();
		ParserChoice parserChoice = new ParserChoice("COMPLEX", file1, null, logger, null);
		parserChoice.accept(new ParserConstructVisitor());
		tearDown();
		outputStreamCaptor.reset();
	}
	
	@Test
	void testBadParser() {
		setUp();
		ParserChoice parserChoice = new ParserChoice("bad", file1, null, logger, "CNN");
		parserChoice.accept(new ParserConstructVisitor());
		System.out.flush();
		tearDown();
		
		StringBuilder builder = new StringBuilder();
		builder.append("User parser choice: \"bad\".");
		builder.append(System.getProperty("line.separator"));
		builder.append("Please select a parser from this list: [COMPLEX, SIMPLE, WEB].");
		String expected = builder.toString().trim();
		
		assertEquals(expected, outputStreamCaptor.toString().trim());
	}
}
