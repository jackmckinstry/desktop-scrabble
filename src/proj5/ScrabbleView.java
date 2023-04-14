package proj5;

import javax.swing.*;
import java.awt.*;

public class ScrabbleView extends View {
	private ScrabbleModel model;
	private JFrame f;
	
	private CellPanel board[][];
	private InventorySlotPanel inventory[];
	private TileListener cellListener, inventoryListener;
	
	public ScrabbleView(Controller c, ScrabbleModel m) {
		super(c);
		model = m;
		f = new JFrame();
		
		cellListener = new TileListener(this);
		inventoryListener = new TileListener(this);
		
		// create 15x15 grid of cells
		JPanel boardPanel = new JPanel(new GridLayout(15, 15, 2, 2));
		board = new CellPanel[15][15];
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				board[x][y] = new CellPanel(model.getCell(x,y), cellListener, x, y);
				boardPanel.add(board[x][y]);
			}
		}
		
		// create 7 inventory slots
		JPanel inventoryPanel = new JPanel(new GridLayout(1, 7, 2, 2));
		inventory = new InventorySlotPanel[7];
		for (int i = 0; i < 7; i++) {
			inventory[i] = new InventorySlotPanel(model.getCurrentPlayerSlot(i), inventoryListener, i);
			inventoryPanel.add(inventory[i]);
		}
		
		
		// render the main window
		f.setLayout(new BorderLayout());
		f.add(boardPanel, BorderLayout.CENTER);
		f.add(inventoryPanel, BorderLayout.PAGE_END);
		f.setSize(1000, 1000);
		f.setVisible(true);
	}
	
	public void update() {
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				board[x][y].updateTile();
			}
		}
		for (int i = 0; i < 7; i++) {
			inventory[i].updateTile();
		}
	}
	
	public void tileSelected() {
		// if a tile on the board and in the player's inventory are selected, send a command to swap them, then deselect them
		if (cellListener.hasSelected() && inventoryListener.hasSelected()) {
			CellPanel cellSelected = (CellPanel) cellListener.selected;
			InventorySlotPanel inventorySelected = (InventorySlotPanel) inventoryListener.selected;
			controller.handleEvent(new PlaceTile(cellSelected.x, cellSelected.y, inventorySelected.id));
			
			cellListener.deselect();
			inventoryListener.deselect();
		}
	}
}
