public class Game implements BoardObserver, DisplayObserver {
	
	private Player p1, p2;
	private Board board;
	private Display display;
	private Player playerTurn;
	
	public Game (String p1Name, char p1Symbol, String p2Name, char p2Symbol) {
		p1 = new Player(p1Name, p1Symbol);
		p2 = new Player(p2Name, p2Symbol);	
		
		playerTurn = p1;
		
		board = new Board(p1, p2);
		display = new Display();
				
		board.registerObserver(this);
		display.registerObserver(this);
	}

	public void start() {
		display.init();
		display.loadHome();
	}
	
	@Override
	public void displayUpdate() {
		if (display.getStatus() == DisplayStatus.STARTBUTTONPRESSED) {
			updateImage();
			display.loadGame();
			board.createBoard();
		} else if (display.getStatus() == DisplayStatus.GAMEBUTTONPRESSED) {
			board.set(playerTurn, display.getButtonPressed().getCoordinate());
			board.checkForWinner();
			if (board.isPlaying()) {
				if (playerTurn == p1) {
					playerTurn = p2;
				} else {
					playerTurn = p1;
				}
				updateImage();
			} else {
				if (board.getWinner() == p1) {
//					display.setWinner("p1");
				} else if (board.getWinner() == p2) {
//					display.setWinner("p2");
				} else {
//					display.setWinner(null);
				}
				display.isPlaying(false);
				display.gameUpdate();
			}
		}
	}

	private void updateImage() {
		if (playerTurn == p1) {
			display.updateImagePath("/img/x.png");
		} else {
			display.updateImagePath("/img/o.png");
		}
	}
	
	@Override
	public void boardUpdate() {
		
		
	}
}