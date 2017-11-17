package backupVisitors.visitor;

import backupVisitors.myTree.Node;
import backupVisitors.util.Results;
import backupVisitors.util.TreeBuilder;

public class FullTimeStatusVisitorImpl implements TreeVisitorI {

	// Results result;
	@Override
	public void visit(TreeBuilder tree) {
		// TODO Auto-generated method stub
		check(tree.root);
	}

	public void check(Node root) {
		if (root != null) {
			
			if (root.courses.size() < 3) {
				root.courses.add("S");
				//System.out.print(root.bnumber + ":" + root.getCourses());
				
			}
			check(root.right);
		} else {
			// System.out.println("List is Empty");
		}
	}

	@Override
	public void visit(TreeBuilder tree, Results result) {
		// TODO Auto-generated method stub

	}

}
