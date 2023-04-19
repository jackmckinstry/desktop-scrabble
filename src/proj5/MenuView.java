package proj5;

import java.awt.*;
import javax.swing.*;

public class MenuView extends View {
	private ScrabbleModel model;
	private JFrame frame;
	
	private ButtonListener buttonListener;
	
	public MenuView(Controller c, ScrabbleModel m) {
		super(c);
		model = m;
		frame = new JFrame();
		buttonListener = new ButtonListener(controller);
						
		// header panel with player name/turn number
		JPanel headerPanel = new JPanel();
		JLabel headerLabel = new JLabel("Select number of players:");
		headerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(headerLabel);
		
		// buttons for selecting number of players
		JPanel buttonPanel = new JPanel();
		JButton twoButton = new JButton("Two Players");
		JButton threeButton = new JButton("Three Players");
		JButton fourButton = new JButton("Four Players");
		twoButton.addActionListener(buttonListener);
		threeButton.addActionListener(buttonListener);
		fourButton.addActionListener(buttonListener);
		buttonPanel.add(twoButton);
		buttonPanel.add(threeButton);
		buttonPanel.add(fourButton);
		
		// render window
		frame.setLayout(new BorderLayout());
		frame.add(headerPanel, BorderLayout.PAGE_START);
		frame.add(buttonPanel, BorderLayout.CENTER);
		frame.setSize(1000,1000);
	}
	
	public void update() {
		if (!model.gameStarted()) {
			frame.setVisible(true);
		} else {
			frame.setVisible(false);
		}
	}
}
