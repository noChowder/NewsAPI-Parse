package task_1;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleParser implements Parser {

	private File file;
	private Logger logger;
	private Article article;
	
	/**
	 * Class constructor.
	 * 
	 * @param file input file for parsing
	 * @param logger the client logger for logging errors
	 * @param Article object to map data
	 */
	public SimpleParser(File file, Logger logger, Article article) {
		this.file = file;
		this.logger = logger;
		this.article = article;
	}
	
	/**
	 * Gets article from parser.
	 * 
	 * @return article read after parsing
	 */
	public Article getArticle() {
		return this.article;
	}
	
	/**
	 * Parses simple JSON files
	 */
	@Override
	public void parse() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			article = mapper.readValue(file, Article.class);
			
			logger.setUseParentHandlers(false);
			Handler fileHandler = new FileHandler("./simpleParser.log");
			Formatter simpleFormatter = new SimpleFormatter();
			logger.addHandler(fileHandler);
			fileHandler.setFormatter(simpleFormatter);
			fileHandler.setLevel(Level.ALL);
			logger.setLevel(Level.ALL);
			
			String warning = null;
			String title = article.getTitle();
			String desc = article.getDescription();
			String pubAt = article.getPublishedAt();
			String url = article.getUrl();
				
			if(title == null || desc == null || pubAt == null || url == null) {
				warning = "Article missing info.\n";
				logger.warning(warning);
			}
			logger.removeHandler(fileHandler);
		} catch(IOException e) {
			logger.log(Level.SEVERE, "Error in Parser.java.", e);
		}
	}
	
	/**
	 * Displays simple article.
	 */
	@Override
	public void display() {
		String title = article.getTitle();
		String desc = article.getDescription();
		String pubAt = article.getPublishedAt();
		String url = article.getUrl();
		
		if(title != null && desc != null && pubAt != null && url != null) {
			System.out.println("Title: " + title + "\n" + "at: " + pubAt + "\n" + "URL: " + url + "\n" + desc + "\n");
		}
	}
	
	@Override
	public void visit(ParserVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o.getClass() != this.getClass()) {
			return false;
		}
		
		final SimpleParser other = (SimpleParser) o;
		if( (this.file == null) ? (other.file != null) : !this.file.equals(other.file) ) {
			return false;
		}
		if( (this.logger == null) ? (other.logger != null) : !this.logger.equals(other.logger) ) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + (this.file != null ? this.file.hashCode() : 0);
		hash = 53 * hash + (this.logger != null ? this.logger.hashCode() : 0);
		return hash;
	}
}
