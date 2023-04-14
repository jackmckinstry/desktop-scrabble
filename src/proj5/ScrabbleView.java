package proj5;

import javax.swing.*;
import java.awt.*;

public class ScrabbleView extends View {
	private ScrabbleModel model;
	private JFrame f;
	
	private CellPanel board[][], inventory[];
	private TileListener tileListener;
	
	public ScrabbleView(Controller c, ScrabbleModel m) {
		super(c);
		model = m;
		f = new JFrame();
		
		tileListener = new TileListener(controller);
		
		// create 15x15 grid of cells
		JPanel boardPanel = new JPanel(new GridLayout(15, 15, 2, 2));
		board = new CellPanel[15][15];
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				board[x][y] = new CellPanel(model.getCell(x,y), tileListener);
				boardPanel.add(board[x][y]);
			}
		}
		
		// create 7 inventory slots
		JPanel inventoryPanel = new JPanel(new GridLayout(1, 7, 2, 2));
		inventory = new CellPanel[7];
		for (int i = 0; i < 7; i++) {
			inventory[i] = new CellPanel(model.getCurrentPlayerSlot(i), tileListener);
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
}
