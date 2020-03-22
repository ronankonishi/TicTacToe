
public interface DisplaySubject {
	public void registerObserver(DisplayObserver observer);
	public void notifyObservers();
	public void removeObserver(DisplayObserver observer);
}
