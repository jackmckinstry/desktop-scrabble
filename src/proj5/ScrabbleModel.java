package proj5;

import java.util.ArrayList;

public class ScrabbleModel extends Model {
	Board board;
	ArrayList<Player> players;
	int currentPlayer;
	int turnNumber;
	
	public ScrabbleModel() {
		board = new Board();
		
		players = new ArrayList<Player>();
		currentPlayer = 0;
		turnNumber = 1;
		
		// for testing
		board.placeTile(new Tile('A',1), 0, 0);
		board.placeTile(new Tile('B',3), 1, 2);
		players.add(new Player("Player 1"));
		players.get(0).tileInventory[0].tile = new Tile('C',3);
	}
	
	
	public Cell getCell(int x, int y) {
		return board.cellArray[x][y];
	}
	
	public Cell getCurrentPlayerSlot(int i) {
		return players.get(currentPlayer).tileInventory[i];
	}
	
	public String getTurnString() {
		return players.get(currentPlayer).name + " - Turn " + turnNumber;
	}
	
	public void moveTile(Cell c1, Cell c2) {
		// swap tile between inventory and board (either may be null)
		Tile tempTile = c1.tile;
		c1.tile = c2.tile;
		c2.tile = tempTile;
		
		updateViews();
	}
}
