package proj5;

public class Cell {
	public Tile tile;
	private int letterMultiplier;
	private int wordMultiplier;
	public boolean placementFinalized;
	
	public Cell(int lm, int wm) {
		tile = null;
		letterMultiplier = lm;
		wordMultiplier = wm;
		placementFinalized = false;
	}
	
	public String tileLetter() {
		if (tile == null) {
			return "";
		}
		
		return Character.toString(tile.letter);
	}
	public int tileValue() {
		if (tile == null) {
			return 0;
		}
		
		if (placementFinalized) {
			return tile.value; // letter multiplier already used
		} else {
			return tile.value * letterMultiplier;
		}
	}
	public int wordMultiplier() {
		if (placementFinalized) {
			return 1; // word multiplier already used
		} else {
			return wordMultiplier;
		}
	}
	
}
