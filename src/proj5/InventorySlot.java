package proj5;

public class InventorySlot {
	public Tile tile;
	
	public InventorySlot() {
		tile = null;
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
}
