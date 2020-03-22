
public class Player implements BoardObject {
	
	private String name;
	private char symbol;
	private boolean winner;
	
	public Player (String name, char symbol) {
		this.name = name;
		this.symbol = symbol;
		this.winner = false;
	}
	
	public String getName() {
		return name;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public void setWinner(boolean status) {
		winner = status;
	}
}
