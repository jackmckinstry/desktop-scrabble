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
	
	public void startGame(int num_players) {
		totalPlayers = num_players;
		
		for (int i = 1; i <= num_players; i++) {
			players.add(new Player("Player " + i));
		}

		distributeTiles();
		
		updateViews();
	}
	
	public void moveTile(Cell c1, Cell c2) {
		// swap tile between inventory and board (either may be null)
		Tile tempTile = c1.tile;
		c1.tile = c2.tile;
		c2.tile = tempTile;
		
		updateViews();
	}
	
	public void finishTurn() {
		// check all letters are connected to another letter
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				Cell c = getCell(x, y);
				if (c.tile != null) {
					boolean adjacent = false;
					// above
					if (y > 0) adjacent = getCell(x, y-1).tile != null;
					// left
					if (!adjacent && x > 0) adjacent = getCell(x-1, y).tile != null;
					// right
					if (!adjacent && x < 14) adjacent = getCell(x+1, y).tile != null;
					// below
					if (!adjacent && y < 14) adjacent = getCell(x, y+1).tile != null;
					
					if (!adjacent) return;
				}
			}
		}
		
		// after first turn, ensure the word attempting to be played is connected to another word that already exists on the board
		if (turnNumber > 1) {
			boolean adjacentToPlaced = false;
			for (int x = 0; x < 15; x++) {
				for (int y = 0; y < 15; y++) {
					if (adjacentToPlaced) break;
					Cell c = getCell(x, y);
					if (c.tile != null && !c.placementFinalized) {
						// at least one tile must be touching a placement finalized tile
						// above
						if (y > 0) adjacentToPlaced = getCell(x, y-1).placementFinalized;
						// left
						if (!adjacentToPlaced && x > 0) adjacentToPlaced = getCell(x-1, y).placementFinalized;
						// right
						if (!adjacentToPlaced && x < 14) adjacentToPlaced = getCell(x+1, y).placementFinalized;
						// below
						if (!adjacentToPlaced && y < 14) adjacentToPlaced = getCell(x, y+1).placementFinalized;
					}
				}
				if (adjacentToPlaced) break;
			}
			if (!adjacentToPlaced) return;
		}
		
		// TODO also need to ensure that each placed word is touching a new letter too, (in order to avoid playing multiple, separate words) might need to use recursion
		
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
