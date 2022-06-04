package task_1;

public class ParserConstructVisitor implements ParserVisitor {
	
	@Override
	public void visit(Parser parser) {
		parser.parse();
		parser.display();
	}
}
