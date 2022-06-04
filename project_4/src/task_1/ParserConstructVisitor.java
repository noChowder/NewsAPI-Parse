package task_1;

public class ParserConstructVisitor implements ParserVisitor {

	/**
	 * Visits given parser.
	 * 
	 * @param parser Parser taken for visiting
	 */
	@Override
	public void visit(Parser parser) {
		parser.parse();
		parser.display();
	}
}
