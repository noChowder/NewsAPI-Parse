package task_1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class News {
	private String status;
	private int totalResults;
	private List<Article> articles;
	
	@JsonCreator
	private News(@JsonProperty("status") String status, @JsonProperty("totalResults") int totalResults, @JsonProperty("articles") List<Article> articles) {
		this.status = status;
		this.totalResults = totalResults;
		this.articles = articles;
	}
	
	/**
	 * Gets status of News object.
	 * 
	 * @return status as String
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Gets total number of articles in News object.
	 * 
	 * @return total number of articles
	 */
	public int getTotalResults() {
		return totalResults;
	}
	
	/**
	 * Gets list of articles mapped from JSON file.
	 * 
	 * @return list of articles
	 */
	public List<Article> getArticles() {
		return articles;
	}
}
