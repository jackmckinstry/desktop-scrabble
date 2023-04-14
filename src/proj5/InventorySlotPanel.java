package proj5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InventorySlotPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	InventorySlot slot;
	int id;
	
	JLabel valueLabel;
	JLabel letterLabel;
	
	InventorySlotPanel(InventorySlot s, MouseListener listener, int id) {
		slot = s;
		this.id = id;
		
		setBackground(Color.WHITE);
		addMouseListener(listener);
		setLayout(new GridLayout(3,1));
			
		// Display tile value at top
		valueLabel = new JLabel(slot.valueString());
		valueLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(valueLabel);
		
		// Display tile letter in middle
		letterLabel = new JLabel(slot.letterString());
		letterLabel.setFont(new Font("Arial", Font.BOLD, 20));
		letterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(letterLabel);
	}
	
	public void updateTile() {
		valueLabel.setText(slot.valueString());
		letterLabel.setText(slot.letterString());
	}
}