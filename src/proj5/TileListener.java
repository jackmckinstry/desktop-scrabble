package proj5;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TileListener implements MouseListener {
	private Controller controller;
	private Model model;
	private ArrayList<CellPanel> selectedCells;
	
	public TileListener(Controller c, Model m) {
		controller = c;
		model = m;
		selectedCells = new ArrayList<CellPanel>();
	}
	
	public void mouseClicked(MouseEvent event) {
		CellPanel clicked = (CellPanel) event.getSource();
		
		ArrayList<Cell> inventoryCells = new ArrayList<Cell>();
		for (Cell c : ((ScrabbleModel) model).getCurrentPlayer().tileInventory) {
			inventoryCells.add(c); 
		}
		
		// clicked cell is selected: deselect the cell
		if (selectedCells.contains(clicked)) {
			clicked.deselect();
			selectedCells.remove(clicked);
		} 
		// clicked cell is not selected, cell is in inventory: select the cell
		else if (inventoryCells.contains(clicked.cell)) {
			selectedCells.add(clicked);
			clicked.select();
		}
		// clicked cell not selected, cell is on board: swap	
		else { // swap cells
			if (selectedCells.isEmpty()) return;
			
			CellPanel c = selectedCells.get(0);
			clicked.deselect();
			c.deselect();
			controller.handleEvent(new MoveTile(c, clicked));
			selectedCells.remove(c);
		}
		((ScrabbleModel) model).setTilesToExchange(selectedCells);
	}
	
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
}
