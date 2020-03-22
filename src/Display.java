import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Display implements DisplaySubject {
	
	private List<DisplayObserver> observers;
		
	GameButton[] ticTacToeButtons;
	
	private JFrame frame;
	private JPanel panel, textPanel;
	private JButton startButton;
	private JLabel[] welcomeText;
	private DisplayStatus displayStatus;
	private GameButton buttonPressed;
	
	private String frameName = "Tic Tac Toe Game";
	private int frameWidth = 600;
	private int frameHeight = 600;
	
	private String imgPath;
	
	private BoardObject winner;
	private boolean playing;
	
	public Display() {
		observers = new ArrayList<DisplayObserver>();
		ticTacToeButtons = new GameButton[9];
		for (int i = 0; i < ticTacToeButtons.length; i++) {
			ticTacToeButtons[i] = new GameButton(this, new Coordinate(i%3, (int) i/3));
		}
		playing = true;
	}
	
	public void setWinner(BoardObject winner) {
		this.winner = winner;
	}
	
	public void isPlaying(boolean status) {
		playing = status;
	}
	
	public void updateImagePath(String imgPath) {
		this.imgPath = imgPath;
		for (GameButton button : ticTacToeButtons) {
			button.setButtonPath(imgPath);
		}
	}
	
	public void init() {
		frame = new JFrame(frameName);
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setSize(frameWidth, frameHeight);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
	}
	
	public void loadHome() {
		panel = new JPanel();
		textPanel = new JPanel();
		startButton = new JButton("Start");
		welcomeText = new JLabel[3];
		welcomeText[0] = new JLabel("Welcome");
		welcomeText[1] = new JLabel("To");
		welcomeText[2] = new JLabel("Tic Tac Toe!");
		
		panel.setLayout(new FlowLayout());
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		
		startButton.setBackground(new Color(230, 75, 60));
		startButton.setForeground(new Color(255, 255, 255));
		startButton.setFocusPainted(false);
		startButton.setPreferredSize(new Dimension(70, 40));
		startButton.setBorder(new LineBorder(Color.BLACK, 1));

		
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				clean();
				displayStatus = DisplayStatus.STARTBUTTONPRESSED;
				notifyObservers();
				displayStatus = null;
			}
		});
		
		panel.add(startButton);
		for (JLabel text : welcomeText) {
			text.setAlignmentX(Component.CENTER_ALIGNMENT);
			text.setFont(new Font("Helvetica", Font.BOLD, 48));
			textPanel.add(text);
			text.setForeground(new Color(230, 75, 60));
		}
		
		frame.add(panel, BorderLayout.PAGE_END);
		frame.add(textPanel, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	public DisplayStatus getStatus() {
		return displayStatus;
	}
	
	private void clean() {
		frame.getContentPane().removeAll();
		frame.revalidate();
		frame.repaint();
	}
	
	public void loadGame() {		
		JPanel gamePanel = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.drawLine(140, 160, 440, 160);
				g2d.drawLine(140, 260, 440, 260);
				g2d.drawLine(240, 60, 240, 360);
				g2d.drawLine(340, 60, 340, 360);
				
			}
		};
		
		
		frame.add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(new GridLayout(3, 3, 10, 10));
		gamePanel.setBorder(new EmptyBorder(60, 147, 204, 152));
				
		for (GameButton button : ticTacToeButtons) {
			button.setButtonPath(imgPath);
			gamePanel.add(button.getJButton());
		}
		
		frame.setVisible(true);
	}

	@Override
	public void registerObserver(DisplayObserver observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers() {
		for(DisplayObserver observer : observers) {
			observer.displayUpdate();
		}
	}

	@Override
	public void removeObserver(DisplayObserver observer) {
		observers.remove(observer);
	}
	
	public void buttonUpdate(GameButton button) {
		displayStatus = DisplayStatus.GAMEBUTTONPRESSED;
		buttonPressed = button;
		notifyObservers();
	}
	
	public GameButton getButtonPressed() {
		return buttonPressed;
	}
	
	public void gameUpdate() {
		for(int i = 0; i < ticTacToeButtons.length; i++) {
			ticTacToeButtons[i].disable();
		}
	}
}