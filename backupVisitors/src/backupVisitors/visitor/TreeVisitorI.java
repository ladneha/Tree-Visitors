package backupVisitors.visitor;

import backupVisitors.util.Results;
import backupVisitors.util.TreeBuilder;

public interface TreeVisitorI {

	void visit(TreeBuilder tree);
	void visit(TreeBuilder tree, Results result);
}
