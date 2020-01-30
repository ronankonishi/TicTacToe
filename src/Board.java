
public class Board {
	
	private Player p1, p2;
	private char[][] board;
	private boolean isWinner = false;
	private int size = 9;
	private int count = 0;
	private int length;
	
	public Board(Player p1, Player p2, int size) {
		this.p1 = p1;
		this.p2 = p2;
		this.size = size;
		this.length = (int) Math.sqrt(size);
		board = new char[this.length][this.length];
	}
	
	public void set(Player player, int[] coordinate) {
		count++;
		board[coordinate[0]][coordinate[1]] = player.getSymbol();
	}

	public boolean isWinner() {
		return (p1.isWinner() || p2.isWinner());
	}

	public boolean isFull() {
		return count == size;
	}

	public void display() {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				System.out.print(board[j][i]);
				if (j != length - 1) {
					System.out.print("|");
				}
			}
			System.out.println("");
		}
	}

	public Player printWinner() {
		if(p1.isWinner()) {
			return p1;
		} else if (p2.isWinner()){
			return p2;
		}
		
		return null;
	}
	
	public void setWinner() {
		char firstValue = '\0';
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
						if (firstValue == '\0') {
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
					if (p1.getSymbol() == firstValue) {
						p1.setWinner(true);
						return;
					} else {
						p2.setWinner(true);
						return;
					}
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
					if (firstValue == '\0') {
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
				if (p1.getSymbol() == firstValue) {
					p1.setWinner(true);
					return;
				} else {
					p2.setWinner(true);
					return;
				}
			}
		}
	}
}
