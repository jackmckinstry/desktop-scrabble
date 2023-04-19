package proj5;

public class Cell {
	public Tile tile;
	public int letterMultiplier,  wordMultiplier;
	public boolean placementFinalized;
	
	public Cell(int lm, int wm) {
		tile = null;
		letterMultiplier = lm;
		wordMultiplier = wm;
		placementFinalized = false;
	}
	
	public String letterString() {
		if (tile == null) {
			return " ";
		}
		
		return Character.toString(tile.letter);
	}
	public String valueString() {
		if (tile == null) {
			return " ";
		}
		
		return Integer.toString(tile.value);
	}
	public String multiplierString() {
		if (letterMultiplier == 1) {
			if (wordMultiplier == 1) {
				return " ";
			}
			return wordMultiplier + "x Word";
		}
		return letterMultiplier + "x Letter";
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
	
	public boolean hasTile() {
		return tile != null;
	}
}
