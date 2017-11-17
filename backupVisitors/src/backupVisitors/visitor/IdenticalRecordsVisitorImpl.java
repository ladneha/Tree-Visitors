package backupVisitors.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import backupVisitors.myTree.Node;
import backupVisitors.util.Results;
import backupVisitors.util.TreeBuilder;

public class IdenticalRecordsVisitorImpl implements TreeVisitorI {

	HashMap<Integer, ArrayList<?>> hm = new HashMap<Integer, ArrayList<?>>();
	ArrayList<Node> list = new ArrayList<Node>();
	int i, j;

	@Override
	public void visit(TreeBuilder tree, Results result) {
		// TODO Auto-generated method stub
		traverse(tree.root);
		for (i = 0; i < list.size(); i++) {
			result.writeToFile("Group " + (i + 1) + ":"+ list.get(i).courses);
			result.writeToFile("" + list.get(i).bnumber);
			for (j = i + 1; j < list.size(); j++) {
				if (listEqualsNoOrder(list.get(i).courses, list.get(j)
						.getCourses())) {
					result.writeToFile("" + list.get(j).bnumber);
					list.remove(j);
					j--;
				}
			}
		}
	}

	public void traverse(Node root) {
		if (root != null) {
			traverse(root.left);
			list.add(root);
			traverse(root.right);
		} else {
			// System.out.println("List is Empty");
		}
	}

	public static <T> boolean listEqualsNoOrder(List<T> l1, List<T> l2) {
		final Set<T> s1 = new HashSet<>(l1);
		final Set<T> s2 = new HashSet<>(l2);

		return s1.equals(s2);
	}

	@Override
	public void visit(TreeBuilder tree) {
		// TODO Auto-generated method stub

	}
}
