package task_1;

public abstract class ParserDecorator implements Parser {

	private ComplexParser parser;
	
	ParserDecorator(ComplexParser parser){
		this.parser = parser;
	}
	
	/**
	 * Gets news from parser.
	 * 
	 * @return news read after parsing
	 */
	public News getNews() {
		return parser.getNews();
	}
	
	/**
	 * Parses data
	 * 
	 */
	@Override
	public void parse() {
		parser.parse();
	}
	
	/**
	 * Displays parsed data
	 * 
	 */
	@Override
	public void display() {
		parser.display();
	}
}
