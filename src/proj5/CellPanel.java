package proj5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CellPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public Cell cell;
	public boolean selected, inInventory;
	private JLabel valueLabel, letterLabel, multiplierLabel;
	
	CellPanel(Cell c, MouseListener listener, boolean inv) {
		cell = c;
		selected = false;
		inInventory = inv;
		
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
		if (selected) { 						// selected = gray
			setBackground(Color.LIGHT_GRAY);
		} else if (cell.placementFinalized) {	// placed tile = orange
			setBackground(Color.ORANGE);
		} else if (cell.hasTile()) {			// unplaced tile = yellow
			setBackground(Color.YELLOW);
		} else if (cell.wordMultiplier == 3) {	// empty cell with 3x word multiplier = red
			setBackground(Color.RED);
		} else if (cell.wordMultiplier == 2) {	// empty cell with 2x word multiplier = pink
			setBackground(Color.PINK);
		} else if (cell.letterMultiplier == 3) {// empty cell with 3x letter multiplier = blue
			setBackground(Color.BLUE);
		} else if (cell.letterMultiplier == 2) {// empty cell with 2x letter multiplier = cyan
			setBackground(Color.CYAN);
		} else {								// empty cell with no multiplier = white
			setBackground(Color.WHITE);
		}
	}
}
