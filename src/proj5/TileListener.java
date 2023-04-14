package proj5;

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
			selected.deselect();
			selected = null;
		} else if (selected == null) { // select a cell
			selected = clicked;
			clicked.select();
		} else { // swap cells
			controller.handleEvent(new MoveTile(selected.cell, clicked.cell));
			selected.deselect();
			selected = null;
		}
	}
	
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
}
