package task_1;

import java.util.List;

public class FilterParser extends ParserDecorator {
	
	private String filter;
	private News news;

	/**
	 * Constructor for wrapping ComplexParser.
	 * 
	 * @param parser ComplexParser being decorated
	 * @param filter Source name to grab data
	 * @param news Internal data to be filtered by display()
	 */
	public FilterParser(ComplexParser parser, String filter, News news) {
		super(parser);
		this.filter = filter;
		this.news = news;
	}
	
	/**
	 * Parses data and updates this.news.
	 * 
	 */
	@Override
	public void parse() {
		super.parse();
		news = super.getNews();
	}
	
	/**
	 * Displays filtered data.
	 * 
	 */
	@Override
	public void display() {
		List<Article> articles = news.getArticles();
		
		for(Article article : articles) {
			if(article.getSource().getName().equalsIgnoreCase(filter)) {
				String title = article.getTitle();
				String desc = article.getDescription();
				String pubAt = article.getPublishedAt();
				String url = article.getUrl();
				
				if(title != null && desc != null && pubAt != null && url != null) {
					System.out.println("Title: " + title + "\n" + "at: " + pubAt + "\n" + "URL: " + url + "\n" + desc + "\n");
				}
			}
		}
	}
}
