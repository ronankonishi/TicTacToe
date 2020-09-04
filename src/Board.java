import java.util.ArrayList;
import java.util.List;

public class Board implements BoardSubject {
	
	private List<BoardObserver> observers;
	private BoardObject winner;
	private boolean playing;
	
	private BoardObject[][] board;
	private int size;
	private int tileCount;
	private int length;
	BoardObject bo1, bo2;
		
	public Board(BoardObject bo1, BoardObject bo2) {
		this.bo1 = bo1;
		this.bo2 = bo2;
		this.tileCount = 0;
		this.size = 9;
		this.length = (int) Math.sqrt(size);
		observers = new ArrayList<BoardObserver>();
		playing = true;
	}
	
	public BoardObject getWinner() {
		return winner;
	}
	
	public void createBoard() {
		board = new BoardObject[this.length][this.length];
	}
	
	public boolean tie() {
		if (tileCount == size) {
			return true;
		}
		return false;
	}
	
	public void set(BoardObject bo, Coordinate cord) {
		tileCount++;
		board[cord.getX()][cord.getY()] = bo;
		
		notifyObservers();
	}
	
	public void checkForWinner() {
		if (tie()) {
			playing = false;
			return;
		}
		
		BoardObject firstValue = null;
		boolean isSame = true;
		
		// vertical and horizontal checks
		for (int h = 0; h < 2; h++) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (j == 0) {
						if (h == 0) {
							firstValue = board[i][j];
						} else {
							firstValue = board[j][i];
						}
						if (firstValue == null) {
							isSame = false;
							break;
						}
					} else if (h == 0 && firstValue != board[i][j]) {
						isSame = false;
						break;
					}  else if (h == 1 && firstValue != board[j][i]) {
						isSame = false;
						break;
					}
	
				}
				if (isSame) {
					playing = false;
					if (bo1 == firstValue) {
						winner = bo1;
						return;
					}
					winner = bo2;
					return;
				}
				
				isSame = true;
			}
		}
		
		// diagonal checks
		for (int h = 0; h < 2; h++) {
			for (int i = 0; i < board.length; i++) {
				if (i == 0) {
					if (h == 0) {
						firstValue = board[i][i];
					} else {
						firstValue = board[i][board.length - 1 - i];
					}
					if (firstValue == null) {
						isSame = false;
						break;
					}
				} else {
					if (h == 0) {
						if (firstValue != board[i][i]) {
							isSame = false;
							break;
						}
					} else {
						if (firstValue != board[i][board.length - 1 - i]) {
							isSame = false;
							break;
						}
					}
				}
			}
			if (isSame) {
				playing = false;
				if (bo1 == firstValue) {
					winner = bo1;
					return;
				}
				winner = bo2;
				return;
			}
			
			isSame = true;
		}
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
	@Override
	public void registerObserver(BoardObserver observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers() {
		for(BoardObserver observer : observers) {
			observer.boardUpdate();
		}
	}

	@Override
	public void removeObserver(BoardObserver observer) {
		observers.remove(observer);
	}
}
