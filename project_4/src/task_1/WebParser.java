package task_1;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WebParser implements Parser{
	
	private URL jsonUrl;
	private Logger logger;
	private News news;
	
	/**
	 * Class constructor.
	 * 
	 * @param URL input file for parsing
	 * @param logger the client logger for logging errors
	 * @param News object to map data
	 */
	public WebParser(URL jsonUrl, Logger logger, News news) {
		this.jsonUrl = jsonUrl;
		this.logger = logger;
		this.news= news;
	}
	
	/**
	 * Gets news from parser.
	 * 
	 * @return news read after parsing
	 */
	public News getNews() {
		return this.news;
	}
	
	/**
	 * Parses JSON from URL.
	 * Logs errors as warnings while parsing JSON URL.
	 */
	@Override
	public void parse() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			news = mapper.readValue(jsonUrl, News.class);
			
			logger.setUseParentHandlers(false);
			Handler fileHandler = new FileHandler("./webParser.log");
			Formatter simpleFormatter = new SimpleFormatter();
			logger.addHandler(fileHandler);
			fileHandler.setFormatter(simpleFormatter);
			fileHandler.setLevel(Level.ALL);
			logger.setLevel(Level.ALL);
			
			String warning = null;
			int index = 0;
			List<Article> articles = news.getArticles();
			for(Article article : articles) {
				String title = article.getTitle();
				String desc = article.getDescription();
				String pubAt = article.getPublishedAt();
				String url = article.getUrl();
				
				if(title == null || desc == null || pubAt == null || url == null) {
					index = articles.indexOf(article);
					warning = "Article at index " + index + " missing info.\n";
					logger.warning(warning);
				}
			}
			logger.removeHandler(fileHandler);
		} catch(IOException e) {
			logger.log(Level.SEVERE, "Error in Parser.java.", e);
		}
	}	
	
	/**
	 * Displays news articles.
	 * Skips articles containing null fields for
	 * 		title,
	 * 		description,
	 * 		published date-time,
	 * 		url.
	 */
	@Override
	public void display() {
		List<Article> articles = news.getArticles();
		
		for(Article article : articles) {
			String title = article.getTitle();
			String desc = article.getDescription();
			String pubAt = article.getPublishedAt();
			String url = article.getUrl();
			
			if(title != null && desc != null && pubAt != null && url != null) {
				System.out.println("Title: " + title + "\n" + "at: " + pubAt + "\n" + "URL: " + url + "\n" + desc + "\n");
			}
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
		
		final WebParser other = (WebParser) o;
		if( (this.jsonUrl == null) ? (other.jsonUrl != null) : !this.jsonUrl.equals(other.jsonUrl) ) {
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
		hash = 53 * hash + (this.jsonUrl != null ? this.jsonUrl.hashCode() : 0);
		hash = 53 * hash + (this.logger != null ? this.logger.hashCode() : 0);
		return hash;
	}
}
