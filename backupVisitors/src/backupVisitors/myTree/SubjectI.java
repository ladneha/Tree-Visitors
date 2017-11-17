package backupVisitors.myTree;

public interface SubjectI {

	public void registerObserver(ObserverI o);

	public void unregisterObserver(ObserverI o);

	public void notifyObservers();
}
