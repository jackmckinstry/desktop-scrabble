package proj5;

import java.awt.Color;
import java.awt.event.*;

public class TileListener implements MouseListener {
	private Controller controller;
	private CellPanel selected;
	
	public TileListener(Controller c) {
		controller = c;
		selected = null;
	}
	
	public void mouseClicked(MouseEvent event) {
		CellPanel clicked = (CellPanel) event.getSource();
		
		if (selected == clicked) { // deselect a cell
			deselect();
		} else if (selected == null) { // select a cell
			selected = clicked;
			clicked.setBackground(Color.CYAN);
		} else { // swap cells
			controller.handleEvent(new MoveTile(selected.cell, clicked.cell));
			deselect();
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
