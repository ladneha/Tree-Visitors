package backupVisitors.myTree;

import java.util.ArrayList;

public interface ObserverI {

	// public void update();

	public void update(int bnumber, ArrayList<String> courses, Node left,
			Node right, ArrayList<Node> observers);
}
