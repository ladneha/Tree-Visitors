package backupVisitors.util;

import backupVisitors.myTree.Node;
import backupVisitors.visitor.FullTimeStatusVisitorImpl;
import backupVisitors.visitor.IdenticalRecordsVisitorImpl;
import backupVisitors.visitor.SortedRecordsVisitorImpl;
import backupVisitors.visitor.StatsVisitorImpl;

public class TreeBuilder {

	public Node root = null;

	public TreeBuilder() {
		this.root = null;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void insert(int bnumberIn, String courseIn) {

		if (root == null) {
			Node newNode = new Node(bnumberIn, courseIn);
			root = newNode;
			Node backUpNode1 = newNode.clone();
			Node backUpNode2 = newNode.clone();
			root.registerObserver(backUpNode1);
			root.registerObserver(backUpNode2);

			return;
		}
		Node current = root;
		Node parent = null;
		while (true) {
			parent = current;
			if (bnumberIn == current.bnumber) {
				int temp = current.getCourses().contains(courseIn) ? 1 : 2;
				if (temp == 1) {
					return;
				} else {
					current.getCourses().add(courseIn);
					current.notifyObservers();
					return;
				}

			}
			if (bnumberIn < current.bnumber) {
				current = current.left;
				if (current == null) {
					Node newNode = new Node(bnumberIn, courseIn);
					parent.left = newNode;
					parent.notifyObservers();
					Node backUpNode1 = newNode.clone();
					Node backUpNode2 = newNode.clone();
					parent.left.registerObserver(backUpNode1);
					parent.left.registerObserver(backUpNode2);
					return;
				}
			} else {
				current = current.right;
				if (current == null) {
					Node newNode = new Node(bnumberIn, courseIn);
					parent.right = newNode;
					parent.notifyObservers();
					Node backUpNode1 = newNode.clone();
					Node backUpNode2 = newNode.clone();
					parent.right.registerObserver(backUpNode1);
					parent.right.registerObserver(backUpNode2);
					return;
				}
			}
		}
	}

	public void display(Node root, Results result) {
		if (root != null) {
			display(root.left, result);
			System.out.print(root.bnumber + ":" + root.getCourses());
			result.storeNewResults(root.bnumber + ":" + root.getCourses());
			display(root.right, result);
		} else {
			// System.out.println("List is Empty");
		}
	}

	public boolean find(int id) {
		Node current = root;
		while (current != null) {
			if (current.bnumber == id) {
				return true;
			} else if (current.bnumber > id) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return false;
	}

	public void delete(int id, String courseIn) {
		@SuppressWarnings("unused")
		Node parent = root;
		Node current = root;
		// System.out.println("root:"+ root.bnumber);
		@SuppressWarnings("unused")
		boolean isLeftChild = false;
		while (current.bnumber != id) {
			parent = current;
			if (current.bnumber > id) {
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
			if (current == null) {
				return;
			}
		}
		// check if the current node contains the course that you want to delete
		if (current.getCourses().contains(courseIn)) {
			current.getCourses().remove(current.getCourses().indexOf(courseIn));
			current.notifyObservers();
		}
		// After deleting the course check if the arraylist of course is empty
		/*
		 * if(current.courses.isEmpty()) { //if i am here that means we have
		 * found the node //Case 1: if node to be deleted has no children
		 * if(current.left==null && current.right==null){ if(current==root){
		 * for(int i = 0; i<root.observers.size(); i++){
		 * current.observers.remove(i); }
		 * 
		 * root = null; } if(isLeftChild ==true){ parent.left = null;
		 * parent.notifyObservers(); }else{ parent.right = null;
		 * parent.notifyObservers(); } } //Case 2 : if node to be deleted has
		 * only one child else if(current.right==null){ if(current==root){
		 * for(int i = 0; i<root.observers.size(); i++){
		 * current.observers.remove(i); } root = current.left; current = null;
		 * // check }else if(isLeftChild){ parent.left = current.left;
		 * parent.notifyObservers(); }else{ parent.right = current.left;
		 * parent.notifyObservers(); } } else if(current.left==null){
		 * if(current==root){ for(int i = 0; i<root.observers.size(); i++){
		 * current.observers.remove(i); } root = current.right; current = null;
		 * }else if(isLeftChild){ parent.left = current.right;
		 * parent.notifyObservers(); }else{ parent.right = current.right;
		 * parent.notifyObservers(); } }else if(current.left!=null &&
		 * current.right!=null){
		 * 
		 * //now we have found the minimum element in the right sub tree Node
		 * successor = getSuccessor(current); if(current==root){ for(int i = 0;
		 * i<root.observers.size(); i++){ current.observers.remove(i); }
		 * successor.left = current.left; root = successor;
		 * root.notifyObservers(); }else if(isLeftChild){ parent.left =
		 * successor; parent.notifyObservers(); }else{ parent.right = successor;
		 * parent.notifyObservers(); } successor.left = current.left;
		 * 
		 * } }
		 */// end if
		return;
	}

	public Node getSuccessor(Node deleleNode) {
		Node successsor = null;
		Node successsorParent = null;
		Node current = deleleNode.right;
		while (current != null) {
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		// check if successor has the right child, it cannot have left child for
		// sure
		// if it does have the right child, add it to the left of
		// successorParent.
		// successsorParent
		if (successsor != deleleNode.right) {
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}

	public void accept(FullTimeStatusVisitorImpl visitor) {
		visitor.visit(this);
	}

	public void accept(StatsVisitorImpl visitor, Results result) {
		visitor.visit(this, result);
	}

	public void accept(SortedRecordsVisitorImpl visitor, Results result) {
		visitor.visit(this, result);
	}

	public void accept(IdenticalRecordsVisitorImpl visitor, Results result) {
		visitor.visit(this, result);
	}
}
