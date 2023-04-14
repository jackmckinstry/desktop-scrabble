package proj5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CellPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	Cell cell;
	boolean selected = false;
	
	JLabel valueLabel;
	JLabel letterLabel;
	JLabel multiplierLabel;
	
	CellPanel(Cell c, MouseListener listener) {
		cell = c;
		selected = false;
		
		updateColor();
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
		
		updateColor();
	}
	
	public void select() {
		selected = true;
		updateColor();
	}
	public void deselect() {
		selected = false;
		updateColor();
	}
	
	public void updateColor() {
		// color depends on if cell is selected, has a tile, and word/letter multiplier
		if (selected) {
			setBackground(Color.LIGHT_GRAY);
		} else if (cell.tile != null) {
			setBackground(Color.ORANGE);
		} else if (cell.wordMultiplier == 3) {
			setBackground(Color.RED);
		} else if (cell.wordMultiplier == 2) {
			setBackground(Color.PINK);
		} else if (cell.letterMultiplier == 3) {
			setBackground(Color.BLUE);
		} else if (cell.letterMultiplier == 2) {
			setBackground(Color.CYAN);
		} else {
			setBackground(Color.WHITE);
		}
	}
}
