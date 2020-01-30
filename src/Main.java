import java.util.Scanner;

public class Main {
	
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Tic Tac Toe. Would you like to play against your friend? (type y for yes)");
		String response = scan.nextLine();
		
		if (!response.equals("y")) {
			System.exit(0);
		}
		
		System.out.println("What is your name?");
		String name1 = scan.nextLine();
		System.out.println("Nice to meet you " + name1 + ".");
		System.out.println("What symbol would you like to select? (type x or o)");
		String symbol1 = scan.nextLine();
		
		String symbol2 = "";
		if (symbol1.equals("x")) {
			symbol2 = "o";
		} else if (symbol1.equals("o")) {
			symbol2 = "x";
		} else {
			System.exit(0);
		}
		
		System.out.println("What is your friend's name?");
		String name2 = scan.nextLine();
		

		
		if (response.equals("y")) {
			new Game(scan, name1, symbol1.charAt(0), name2, symbol2.charAt(0));
		}
	}
	
	
	
}
