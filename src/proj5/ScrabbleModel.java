package proj5;

import java.util.ArrayList;

public class ScrabbleModel extends Model {
	Board board;
	ArrayList<Player> players;
	int currentPlayer;
	
	public ScrabbleModel() {
		board = new Board();
		
		players = new ArrayList<Player>();
		currentPlayer = 0;
		
		// for testing
		board.placeTile(new Tile('A',1), 0, 0);
		board.placeTile(new Tile('B',3), 1, 2);
		players.add(new Player("P1"));
		players.get(0).tileInventory[0].tile = new Tile('C',3);
	}
	
	
	public Cell getCell(int x, int y) {
		return board.cellArray[x][y];
	}
	
	public InventorySlot getCurrentPlayerSlot(int i) {
		return players.get(currentPlayer).tileInventory[i];
	}
	
	public void swapTiles(Cell selectedCell, InventorySlot selectedSlot) {
		// swap tile between inventory and board (one may be null)
		Tile tempTile = selectedCell.tile;
		selectedCell.tile = selectedSlot.tile;
		selectedSlot.tile = tempTile;
		
		updateViews();
	}
}
