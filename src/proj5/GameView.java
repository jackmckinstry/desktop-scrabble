package proj5;

import java.awt.*;
import javax.swing.*;

public class GameView extends View {
	private ScrabbleModel model;
	private JFrame frame;
	
	private TileListener tileListener;
	private ButtonListener buttonListener;
	
	private JLabel headerLabel;
	private CellPanel board[][], inventory[];
	
	public GameView(Controller c, ScrabbleModel m) {
		super(c);
		model = m;
		frame = new JFrame();
		frame.setTitle("Scrabble");
		tileListener = new TileListener(controller);
		buttonListener = new ButtonListener(controller, tileListener);
		
		// header panel with player name/turn number
		JPanel headerPanel = new JPanel();
		headerLabel = new JLabel();
		headerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(headerLabel);
				
		// 15x15 grid of cells
		JPanel boardPanel = new JPanel(new GridLayout(15, 15, 2, 2));
		board = new CellPanel[15][15];
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				board[x][y] = new CellPanel(model.getCell(x,y), tileListener, false);
				boardPanel.add(board[x][y]);
			}
		}
		
		// 7 inventory slots
		JPanel inventoryPanel = new JPanel(new GridLayout(1, 7, 2, 2));
		inventory = new CellPanel[7];
		for (int i = 0; i < 7; i++) {
			inventory[i] = new CellPanel(new Cell(1,1), tileListener, true);
			inventoryPanel.add(inventory[i]);
		}
		
		// buttons for finishing turn and exchanging letters
		JButton exchangeButton = new JButton("Exchange Tiles");
		JButton finishButton = new JButton("Place Tiles");
		exchangeButton.addActionListener(buttonListener);
		finishButton.addActionListener(buttonListener);
		inventoryPanel.add(exchangeButton);
		inventoryPanel.add(finishButton);
		
		// render the main window
		frame.setLayout(new BorderLayout());
		frame.add(headerPanel, BorderLayout.PAGE_START);
		frame.add(boardPanel, BorderLayout.CENTER);
		frame.add(inventoryPanel, BorderLayout.PAGE_END);
		frame.setSize(1000,1000);
	}
	
	public void update() {
		if (model.gameStarted() && !model.gameEnded()) {
			// update tiles on board
			for (int x = 0; x < 15; x++) {
				for (int y = 0; y < 15; y++) {
					board[x][y].updateTile();
				}
			}
			
			// update tiles in inventory
			for (int i = 0; i < 7; i++) {
				inventory[i].cell = model.getCurrentPlayer().tileInventory[i];
				inventory[i].updateTile();
			}
			
			// update player/turn text
			headerLabel.setText(model.getTurnString());
			
			frame.setVisible(true);
		} else {
			frame.setVisible(false);
		}
	}
}
