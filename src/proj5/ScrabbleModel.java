package proj5;

import java.util.ArrayList;

public class ScrabbleModel extends Model {
	private Board board;
	private TileBag bag;
	private ArrayList<Player> players;
	private int currentPlayer, turnNumber;
	
	public ScrabbleModel() {
		board = new Board();
		bag = new TileBag();
		
		players = new ArrayList<Player>();
		currentPlayer = 0;
		turnNumber = 1;
		
		// for testing
		players.add(new Player("Player 1"));
		distributeTiles();
	}
	
	public void distributeTiles() {
		// fill up all tiles in all player inventories
		for (Player p : players) {
			for (Cell c : p.tileInventory) {
				if (c.tile == null) {
					c.tile = bag.pickTile();
				} 
			}
		}
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
