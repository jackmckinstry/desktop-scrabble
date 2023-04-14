package proj5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CellPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Cell cell;
	
	JLabel valueLabel;
	JLabel letterLabel;
	JLabel multiplierLabel;
	
	CellPanel(Cell c, MouseListener listener) {
		cell = c;
		
		setBackground(Color.WHITE);
		addMouseListener(listener);
		setLayout(new GridLayout(3,1));
			
		// Display tile value at top
		valueLabel = new JLabel(cell.valueString());
		valueLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(valueLabel);
				
				
		// Display tile letter in middle
		letterLabel = new JLabel(cell.letterString());
		letterLabel.setFont(new Font("Arial", Font.BOLD, 20));
		letterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(letterLabel);
		
		// Display multiplier text at bottom
		multiplierLabel = new JLabel(cell.multiplierString());
		multiplierLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		multiplierLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(multiplierLabel);
	}
	
	public void updateTile() {
		valueLabel.setText(cell.valueString());
		letterLabel.setText(cell.letterString());
		multiplierLabel.setText(cell.multiplierString());
	}
}
