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
	
	@Override
	public void parse() {
		parser.parse();
	}
	
	@Override
	public void display() {
		parser.display();
	}
}
