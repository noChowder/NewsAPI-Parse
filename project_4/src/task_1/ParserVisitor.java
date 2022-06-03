package task_1;

public interface ParserVisitor {

	void visit(ComplexParser parser);
	void visit(SimpleParser parser);
	void visit(WebParser parser);
}
