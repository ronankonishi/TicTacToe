import java.util.Scanner;

public class Game {
	
	private Player p1, p2;
	private Board board;
	private Scanner scan;

	public Game (Scanner scan, String p1Name, char p1Symbol, String p2Name, char p2Symbol) {
		this.scan = scan;
		p1 = new Player(p1Name, p1Symbol);
		p2 = new Player(p2Name, p2Symbol);
		board = new Board(p1, p2, 9);
		
		this.play();
	}
	
	private void play() {
		int[] output;
		boolean p1turn = true;
		System.out.println("This is the game board, you must enter a coordinate such that (0,0) is the top left position");
		while(!board.isWinner() && !board.isFull()) {
			board.display();
			if (p1turn) {
				System.out.println(p1.getName() + ", your turn.");
				output = this.parseInput(scan.nextLine());
				board.set(p1, output);
				p1turn = false;
			} else {
				System.out.println(p2.getName() + ", your turn.");
				output = this.parseInput(scan.nextLine());
				board.set(p2, output);
				p1turn = true;
			}
			board.setWinner();
		}
		
		if (board.isWinner()) {
			System.out.println("The winner is " + board.printWinner());
		} else {
			System.out.println("Tie game");
		}
		
		
	}

	private int[] parseInput(String input) {
		int[] output = new int[2];
		output[0] = input.charAt(1) - '0';
		output[1] = input.charAt(3) - '0';
		return output;
	}
	
}
