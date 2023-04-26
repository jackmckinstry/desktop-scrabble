package proj5;

public class FinishTurn implements Command {
	ScrabbleModel model;
	boolean[][] valid;
	int commonX, commonY; // used in recursive markValid algorithm

	public FinishTurn() {
		
	}
	
	public void execute(ScrabbleModel m) {
		model = m;
		
		// all empty cells are valid
		valid = new boolean[15][15];
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				valid[x][y] = !model.getCell(x, y).hasTile();
			}
		}
		
		// these will hold the (x,y) coordinates of the first newly-placed tile
		// either coordinate must be shared by all newly-placed tiles
		commonX = -1;
		commonY = -1;
		
		// recursively mark cells connected to center tile as valid
		markValid(7, 7);
		
		// return if not all cells are valid, or if a word is misspelled
		WordChecker wordCheck = WordChecker.getInstance();
		int points = 0;
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				// cell is not valid
				if (!valid[x][y]) {
					return;
				}
				
				// check validity of words
				if (model.getCell(x, y).hasTile()) { // has a tile
					if (x == 0 || !model.getCell(x-1, y).hasTile()) { // has no tile to the left
						if (x != 14 && model.getCell(x+1, y).hasTile()) { // has a tile to the right
							// spells a word horizontally
							int p = getPoints(x, y, 0, 0, true);
							points += p;
							if (p != 0 && !wordCheck.isValidWord(getWord(x, y, true))) {
								return;
							}
						}
					}
					
					if (y == 0 || !model.getCell(x, y-1).hasTile()) { // has no tile above
						if (y != 14 && model.getCell(x, y+1).hasTile()) { // has a tile below
							// spells a word horizontally
							int p = getPoints(x, y, 0, 0, false);
							points += p;
							if (p != 0 && !wordCheck.isValidWord(getWord(x, y, false))) {
								return;
							}
						}
					}
				}
			}
		}
		
		// if all tiles are valid, add the total points gained
		model.getCurrentPlayer().addPoints(points);
			
		// finalize placement of newly-placed tiles
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				Cell c = model.getCell(x, y);
				if (c.hasTile() && !c.placementFinalized) {
					c.placementFinalized = true;
				}
			}
		}
		
		// 50 point bonus for using all 7 tiles
		// TODO: ensure this doesn't happen when a player had <7 tiles to begin their turn
		if (model.getCurrentPlayer().inventoryEmpty()) {
			model.getCurrentPlayer().addPoints(50);
		}
		
		model.distributeTiles();
		
		// game ends when there are no more tiles
		if (model.getCurrentPlayer().inventoryEmpty()) {
			model.endGame();
		} else {
			model.nextTurn();
		}	
	}
	
	private void markValid(int x, int y) {
		if (x < 0 || x > 14 || y < 0 || y > 14) {
			// out of bounds
			return;
		}
		
		if (valid[x][y]) {
			// already marked valid
			return;
		}
		
		Cell c = model.getCell(x, y);
		if (c.hasTile()) {
			if (c.placementFinalized) {
				// previously-placed tiles are always valid
				valid[x][y] = true;
			} else {
				// newly-placed tiles are valid if they share the common row/column
				if (commonX == -1 && commonY == -1) {
					// set common (x,y) coordinates if they haven't been set already
					commonX = x;
					commonY = y;
					valid[x][y] = true;
				} else if (commonX == x) {
					// matches common x coordinate
					commonY = -1;
					valid[x][y] = true;
				} else if (commonY == y) {
					// matches common y coordinate
					commonX = -1;
					valid[x][y] = true;
				}
			}
			
			// recursively check adjacent cells
			markValid(x-1, y);
			markValid(x+1, y);
			markValid(x, y-1);
			markValid(x, y+1);
		}
	}
	
	private String getWord(int x, int y, boolean isHorizontal) {
		if (x > 14 || y > 14) {
			// out of bounds
			return "";
		}
		
		Cell c = model.getCell(x, y);
		if (!c.hasTile()) {
			// end of word
			return "";
		}
		
		// recursively check all remaining letters in word
		if (isHorizontal) {
			return c.tile.letter + getWord(x+1, y, isHorizontal);
		} else {
			return c.tile.letter + getWord(x, y+1, isHorizontal);
		}
	}
	
	private int getPoints(int x, int y, int points, int wordMultiplier, boolean isHorizontal) {
		if (x > 14 || y > 14) {
			// out of bounds
			return points * wordMultiplier;
		}
		
		Cell c = model.getCell(x, y);
		if (!c.hasTile()) {
			// end of word
			return points * wordMultiplier;
		}
		
		if (c.placementFinalized) {
			// previously-placed tile
			points += c.tile.value;
		} else {
			// newly-placed tile
			if (wordMultiplier == 0) {
				wordMultiplier = 1;
			}
			wordMultiplier *= c.wordMultiplier;
			points += c.tile.value * c.letterMultiplier;
		}
		
		// recursively check all remaining letters in word
		if (isHorizontal) {
			return getPoints(x+1, y, points, wordMultiplier, isHorizontal);
		} else {
			return getPoints(x, y+1, points, wordMultiplier, isHorizontal);
		}
	}
}