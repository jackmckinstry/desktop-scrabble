package proj5;

import java.util.ArrayList;

public class ScrabbleModel extends Model {
	private Board board;
	private TileBag bag;
	private ArrayList<Player> players;
	private int currentPlayer, turnNumber, totalPlayers;
	
	public ScrabbleModel() {
		board = new Board();
		bag = new TileBag();
		
		players = new ArrayList<Player>();
		currentPlayer = 0;
		turnNumber = 1;
		totalPlayers = 2; // TODO
		
		// for testing
		players.add(new Player("Player 1"));
		players.add(new Player("Player 2"));
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
		return players.get(currentPlayer).name + " - Turn " + turnNumber + " - Score: " + players.get(currentPlayer).getTotalPoints();
	}
	
	public void moveTile(Cell c1, Cell c2) {
		// swap tile between inventory and board (either may be null)
		Tile tempTile = c1.tile;
		c1.tile = c2.tile;
		c2.tile = tempTile;
		
		updateViews();
	}
	
	public void finishTurn() {
		// check alignment of letters are connected to another word after first turn
		if (turnNumber > 1) {
			for (int x = 0; x < 15; x++) {
				for (int y = 0; y < 15; y++) {
					Cell c = getCell(x, y);
					if (c.tile != null) {
						boolean adjacent = false;
						// above
						if (y > 0) {
							adjacent = getCell(x, y-1).tile != null;
						}
						// left
						if (!adjacent && x > 0) {
							adjacent = getCell(x-1, y).tile != null;
						}
						// right
						if (!adjacent && x < 14) {
							adjacent = getCell(x+1, y).tile != null;
						}
						// below
						if (!adjacent && y < 14) {
							adjacent = getCell(x, y+1).tile != null;
						}
						
						if (!adjacent) return;
					}
				}
			}
		}
		
		// TODO validate word
		// WordChecker.getInstance().isValidWord(null);
			
		// update score
		int points = 0;
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				Cell c = getCell(x, y);
				if (c.tile != null && !c.placementFinalized) {
					points += c.tileValue() * c.letterMultiplier;
					// TODO account for word multipliers
				}
				// TODO this score calculation will be changed based on how word validation is implemented (if it is made into a single string)
			}
		}
		players.get(currentPlayer).addPoints(points);
		
		// finalize placement of all tiles on the board
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				Cell c = getCell(x, y);
				if (c.tile != null) c.placementFinalized = true;
			}
		}
		
		if (bag.isEmpty()) {
			// TODO end game, go to view of final scores
		}
		
		// repopulate currentPlayer's inventory from tile bag
		distributeTiles();
		
		// cycle to next player
		currentPlayer++;
		if (currentPlayer == totalPlayers) currentPlayer = 0;
		turnNumber++;
		
		updateViews();
	}
}
