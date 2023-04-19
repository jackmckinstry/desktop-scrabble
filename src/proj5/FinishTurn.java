package proj5;

public class FinishTurn implements Command {
	public FinishTurn() {
		
	}
	
	public void execute(ScrabbleModel m) {
		// all empty cells are valid
		boolean valid[][] = new boolean[15][15];
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				valid[x][y] = !m.getCell(x, y).hasTile();
			}
		}
		
		// recursively mark cells connected to center tile as valid
		markValid(m, valid, 7, 7);
		
		// return if not all cells are valid
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				if (!valid[x][y]) {
					return;
				}
			}
		}
		
		// TODO make sure all newly placed letters are in one row/column
		
		// TODO validate word
		// WordChecker.getInstance().isValidWord(null);
			
		// update score
		int points = 0;
		int wordMultiplier = 1;
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				Cell c = m.getCell(x, y);
				if (c.hasTile() && !c.placementFinalized) {
					points += c.tile.value;
					wordMultiplier *= c.wordMultiplier;
					
					c.placementFinalized = true;
				}
			}
		}
		m.getCurrentPlayer().addPoints(points*wordMultiplier);
		
		m.distributeTiles();
		
		// check for end of game
		boolean empty = true;
		for (Cell c : m.getCurrentPlayer().tileInventory) {
			if (c.hasTile()) {
				empty = false;
				break;
			}
		}
		
		if (empty) {
			m.endGame();
		} else {
			m.nextTurn();
		}	
	}
	
	private void markValid(ScrabbleModel m, boolean[][] valid, int x, int y) {
		if (x < 0 || x > 14 || y < 0 || y > 14) {
			// not in bounds
			return;
		}
		
		if (valid[x][y]) {
			// already marked valid
			return;
		}
		
		if (m.getCell(x, y).hasTile()) {
			// mark valid and recursively check adjacent cells
			valid[x][y] = true;
			markValid(m, valid, x-1, y);
			markValid(m, valid, x+1, y);
			markValid(m, valid, x, y-1);
			markValid(m, valid, x, y+1);
		}
	}
}