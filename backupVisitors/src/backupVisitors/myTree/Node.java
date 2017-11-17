package backupVisitors.myTree;

import java.util.ArrayList;

public class Node implements SubjectI, ObserverI, Cloneable {

	public int bnumber;
	public String course;
	public ArrayList<String> courses = new ArrayList<String>();
	public Node left = null;
	public Node right = null;
	public ArrayList<Node> observers;
    /***
     * This is constructor for the class
     * @param bnumberIn
     * @param courseIn
     */
	public Node(int bnumberIn, String courseIn) {
		observers = new ArrayList<Node>();
		bnumber = bnumberIn;
		course = courseIn;
		getCourses().add(courseIn);
	}
    /***
     * getter for bnumber
     * @return bnumber
     */
	public int getBnumber() {
		return bnumber;
	}
    /***
     * setter for bnumber
     * @param none
     */
	public void setBnumber(int bnumber) {
		this.bnumber = bnumber;
	}
    /***
     * getter for right child
     * @return node
     */
	public Node getRight() {
		return right;
	}
    /***
     * setter for right node
     * @param none
     */
	public void setRight(Node right) {
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node clone() {
		return new Node(bnumber, course);
	}

	@Override
	public void registerObserver(ObserverI o) {
		// TODO Auto-generated method stub
		observers.add((Node) o);

	}

	@Override
	public void unregisterObserver(ObserverI o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		int i;
		for (i = 0; i < observers.size(); i++) {
			ObserverI observer = (ObserverI) observers.get(i);
			observer.update(this.bnumber, this.courses, this.left, this.right,
					this.observers);
		}
	}

	public ArrayList<String> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}

	public ArrayList<Node> getObservers() {
		return observers;
	}

	public void setListners(ArrayList<Node> observers) {
		this.observers = observers;
	}

	@Override
	public void update(int bnumberIn, ArrayList<String> coursesIn, Node leftIn,
			Node rightIn, ArrayList<Node> observersIn) {
		// TODO Auto-generated method stub
		this.bnumber = bnumberIn;
		this.courses = coursesIn;
		this.left = leftIn;
		this.right = rightIn;
		this.observers = observersIn;
	}

}
