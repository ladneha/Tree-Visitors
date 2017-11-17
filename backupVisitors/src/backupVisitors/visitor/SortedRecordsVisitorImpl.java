package backupVisitors.visitor;

import backupVisitors.myTree.Node;
import backupVisitors.util.Results;
import backupVisitors.util.TreeBuilder;

public class SortedRecordsVisitorImpl implements TreeVisitorI {

	@Override
	public void visit(TreeBuilder tree, Results result) {
		// TODO Auto-generated method stub
		check(tree.root.getObservers().get(1), result);

	}

	public void check(Node root, Results result) {
		if (root != null) {
			check(root.right, result);
			// System.out.print( root.bnumber + ":"+ root.getCourses());
			// result.storeNewResults( root.bnumber + ":"+ root.getCourses());
			result.writeToFile(root.bnumber + ":" + root.getCourses());
			check(root.left, result);
		} else {
			// System.out.println("List is Empty");
		}
	}

	@Override
	public void visit(TreeBuilder tree) {
		// TODO Auto-generated method stub

	}
}
