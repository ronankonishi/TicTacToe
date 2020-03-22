public interface BoardSubject {
	
	public void registerObserver(BoardObserver observer);
	public void notifyObservers();
	public void removeObserver(BoardObserver observer);
}
