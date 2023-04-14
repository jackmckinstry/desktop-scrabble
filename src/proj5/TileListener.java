package proj5;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JPanel;

public class TileListener implements MouseListener {
	private ScrabbleView callback;
	public JPanel selected;
	
	public TileListener(ScrabbleView cb) {
		callback = cb;
		selected = null;
	}
	
	public void mouseClicked(MouseEvent event) {
		JPanel clicked = (JPanel) event.getSource();
		
		if (selected == clicked) {
			// deselect a cell
			deselect();
		} else {
			// deselect a cell (if applicable) and select a new cell
			if (selected != null) {
				deselect();
			}
			selected = clicked;
			clicked.setBackground(Color.CYAN);
			
			callback.tileSelected();
		}
	}
	
	public void deselect() {
		selected.setBackground(Color.WHITE);
		selected = null;
	}
	
	public boolean hasSelected() {
		return selected != null;
	}
	
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
}
