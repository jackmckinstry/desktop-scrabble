package proj5;

import javax.swing.*;
import java.awt.*;

public class ScrabbleView extends View {
	private ScrabbleModel model;
	private JFrame frame;
	private TileListener tileListener;
	
	private CellPanel board[][], inventory[];
	
	public ScrabbleView(Controller c, ScrabbleModel m) {
		super(c);
		model = m;
		frame = new JFrame();
		tileListener = new TileListener(controller);
		
		// 15x15 grid of cells
		JPanel boardPanel = new JPanel(new GridLayout(15, 15, 2, 2));
		board = new CellPanel[15][15];
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				board[x][y] = new CellPanel(model.getCell(x,y), tileListener);
				boardPanel.add(board[x][y]);
			}
		}
		
		// 7 inventory slots
		JPanel inventoryPanel = new JPanel(new GridLayout(1, 7, 2, 2));
		inventory = new CellPanel[7];
		for (int i = 0; i < 7; i++) {
			inventory[i] = new CellPanel(model.getCurrentPlayerSlot(i), tileListener);
			inventoryPanel.add(inventory[i]);
		}
		
		// header panel with player name/turn number
		JPanel headerPanel = new JPanel();
		JLabel headerLabel = new JLabel(model.getTurnString());
		headerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(headerLabel);
		
		
		// render the main window
		frame.setLayout(new BorderLayout());
		frame.add(headerPanel, BorderLayout.PAGE_START);
		frame.add(boardPanel, BorderLayout.CENTER);
		frame.add(inventoryPanel, BorderLayout.PAGE_END);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
	
	public void update() {
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				board[x][y].updateTile();
			}
		}
		for (int i = 0; i < 7; i++) {
			inventory[i].cell = model.getCurrentPlayerSlot(i);
			inventory[i].updateTile();
		}
	}
}
