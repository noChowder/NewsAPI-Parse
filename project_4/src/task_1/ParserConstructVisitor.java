package task_1;

public class ParserConstructVisitor implements ParserVisitor {
	
	@Override
	public void visit(ComplexParser parser) {
		parser.parse();
		parser.display();
	}
	
	@Override
	public void visit(SimpleParser parser) {
		parser.parse();
		parser.display();
	}
	
	@Override
	public void visit(WebParser parser) {
		parser.parse();
		parser.display();
	}
	
	@Override
	public void visit(Parser parser) {
		parser.parse();
		parser.display();
	}
}
