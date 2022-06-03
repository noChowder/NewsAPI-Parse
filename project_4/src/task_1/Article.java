package task_1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {
	private Source source;
	private String author;
	private String title;
	private String description;
	private String url;
	private String urlToImage;
	private String publishedAt;
	private String content;
	
	@JsonCreator
	private Article(@JsonProperty("source") Source source, @JsonProperty("author") String author, @JsonProperty("title") String title,
			@JsonProperty("description") String description, @JsonProperty("url") String url, @JsonProperty("urlToImage") String urlToImage,
			@JsonProperty("publishedAt") String publishedAt, @JsonProperty("content") String content) {
		this.source = source;
		this.author = author;
		this.title = title;
		this.description = description;
		this.url = url;
		this.urlToImage = urlToImage;
		this.publishedAt = publishedAt;
		this.content = content;
	}
	
	/**
	 * Gets Source object mapped from JSON file.
	 * 
	 * @return Source object
	 */
	public Source getSource() {
		return source;
	}
	
	/**
	 * Gets author of article.
	 * 
	 * @return author as String
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Gets title of article.
	 * 
	 * @return title as String
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets description of article.
	 * 
	 * @return description as String
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Gets url of article.
	 * 
	 * @return url as String
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Gets urlToImage of article.
	 * 
	 * @return urlToImage as String
	 */
	public String getUrlToImage() {
		return urlToImage;
	}
	
	/**
	 * Gets published date-time of article.
	 * 
	 * @return publishedAt as String
	 */
	public String getPublishedAt() {
		return publishedAt;
	}
	
	/**
	 * Gets content of article.
	 * 
	 * @return content as String
	 */
	public String getContent() {
		return content;
	}
}
