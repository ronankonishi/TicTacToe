import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class GameButton {
	private JButton button;
	private String imgPath;
	private boolean set;
	private Coordinate cord;
	
	private URL url;
	private File file;
	private BufferedImage img;
	
	private Display display;
	private boolean disabled;
	
	public GameButton(Display display, Coordinate cord) {
		button = new JButton();
		this.cord = cord;
		this.display = display;
		init();
	}
	
	public void setButtonPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public JButton getJButton() {
		return button;
	}
	
	public void init() {
		button.setFocusPainted(false);

		button.setBackground(Color.WHITE);
		button.setBorder(new LineBorder(Color.WHITE, 1));
		
		
		MouseAdapter adapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				if (!set && !disabled) {
					try {
						url = getClass().getResource(imgPath);
						file = new File(url.getPath());
						img = ImageIO.read(file);
						button.setIcon(new ImageIcon(img));
						button.setPressedIcon(new ImageIcon(img));
					} catch (IOException e) {
						System.out.println("Error: Could not read in img file");
						System.exit(-1);
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent evt) {
				if (!set && !disabled) {
					button.setIcon(null);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (!set && !disabled) {
					set = true;
					notifyObserver();
				}
			}
		};
		button.addMouseListener(adapter);
	}
	
	private void notifyObserver() {
		display.buttonUpdate(this);
	}
	
	public Coordinate getCoordinate() {
		return cord;
	}
	
	public void disable() {
		disabled = true;
	}
	
}