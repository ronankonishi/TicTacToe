
public class Player {
	
	private String name;
	private char symbol;
	private boolean isWinner = false;
	
	public Player (String name, char symbol) {
		this.name = name;
		this.symbol = symbol;
	}
	
	public String getName() {
		return name;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public boolean isWinner() {
		return isWinner;
	}
	
	public void setWinner(boolean status) {
		isWinner = status;
	}
}
