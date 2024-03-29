package proj5;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MenuView extends View {
	private ScrabbleModel model;
	private JFrame frame;
	JTextField textField1, textField2, textField3, textField4;
	
	private ButtonListener buttonListener;
	
	public MenuView(Controller c, ScrabbleModel m) {
		super(c);
		model = m;
		frame = new JFrame();
		frame.setTitle("Scrabble");
		buttonListener = new ButtonListener(controller, null, model);
						
		// header panel with player name/turn number
		JPanel headerPanel = new JPanel();
		JLabel headerLabel1 = new JLabel("Scrabble\n");
		headerLabel1.setFont(new Font("Arial", Font.PLAIN, 48));
		headerLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(headerLabel1); 
		
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
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textField1 = new JTextField("Player 1 Name");
		textField2 = new JTextField("Player 2 Name");
		textField3 = new JTextField("Player 3 Name");
		textField4 = new JTextField("Player 4 Name");
		JLabel headerLabel2 = new JLabel("Enter names & select number of players:");
		headerLabel2.setFont(new Font("Arial", Font.PLAIN, 24));
		headerLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		textPanel.add(headerLabel2);
		textPanel.add(textField1);
		textPanel.add(textField2);
		textPanel.add(textField3);
		textPanel.add(textField4);
		
		frame.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		
		con.fill = GridBagConstraints.HORIZONTAL;
		con.weightx = 0.5;
		con.gridx = 1;
		con.gridy = 0;
		frame.add(headerPanel, con);
		
		con.fill = GridBagConstraints.HORIZONTAL;
		con.ipady = 40;      //make this component tall
		con.weightx = 0.0;
		con.gridwidth = 2;
		con.gridx = 1;
		con.gridy = 2;
		frame.add(textPanel, con);

		con.fill = GridBagConstraints.HORIZONTAL;
		con.ipady = 0; 
		con.weighty = 1.0;
		con.anchor = GridBagConstraints.PAGE_END;
		con.gridx = 1;
		con.gridwidth = 2;
		con.gridy = 2;       //third row
		frame.add(buttonPanel, con);
		
		// render window
		frame.setSize(1000,1000);
	}
	
	private ArrayList<String> getNames() {
		ArrayList<String> names = new ArrayList<String>();
		names.add(textField1.getText());
		names.add(textField2.getText());
		names.add(textField3.getText());
		names.add(textField4.getText());
		return names;
	}
	
	public void update() {
		if (!model.gameStarted()) {
			model.setNames(this.getNames());
			frame.setVisible(true);
		} else {
			frame.setVisible(false);
		}
	}
}
