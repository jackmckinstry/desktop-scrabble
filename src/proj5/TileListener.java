package proj5;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TileListener implements MouseListener {
	private Controller controller;
	private ArrayList<CellPanel> inventorySelected;
	private CellPanel boardSelected;
	
	public TileListener(Controller c) {
		controller = c;
		inventorySelected = new ArrayList<CellPanel>();
		boardSelected = null;
	}
	
	public void mouseClicked(MouseEvent event) {
		CellPanel clicked = (CellPanel) event.getSource();
		
		if (clicked.inInventory) {
			// cell is in inventory
			if (inventorySelected.contains(clicked)) { // deselect cell
				clicked.deselect();
				inventorySelected.remove(clicked);
			} else if (boardSelected != null) { // swap cell to board
				controller.handleEvent(new MoveTile(boardSelected.cell, clicked.cell));
				boardSelected.deselect();
				boardSelected = null;
			} else { // select cell
				clicked.select();
				inventorySelected.add(clicked);
			}
		} else {
			// cell is on board
			if (boardSelected == clicked) { // deselect cell
				clicked.deselect();
				boardSelected = null;
			} else if (boardSelected != null) { // swap cell within board
				controller.handleEvent(new MoveTile(boardSelected.cell, clicked.cell));
				boardSelected.deselect();
				boardSelected = null;
			} else if (!inventorySelected.isEmpty()) { // swap cell to inventory
				// choose the most recently selected cell
				CellPanel swap = inventorySelected.get(inventorySelected.size() - 1);
				controller.handleEvent(new MoveTile(swap.cell, clicked.cell));
				swap.deselect();
				inventorySelected.remove(swap);
			} else { // select cell
				clicked.select();
				boardSelected = clicked;
			}
		}
	}
	
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
	
	public ArrayList<Cell> getSelectedCells() {
		// return cells corresponding to the selected panels
		ArrayList<Cell> selected = new ArrayList<Cell>();
		for (CellPanel panel : inventorySelected) {
			selected.add(panel.cell);
		}
		deselectAll();
		return selected;
	}
	public void deselectAll() {
		for (CellPanel panel : inventorySelected) {
			panel.deselect();
		}
		inventorySelected.clear();
		if (boardSelected != null) {
			boardSelected.deselect();
			boardSelected = null;
		}
	}
}
