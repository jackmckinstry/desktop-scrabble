package proj5;

public class PlaceTile implements Command {
	int x, y, i;
	
	public PlaceTile(int x, int y, int i) {
		this.x = x;
		this.y = y;
		this.i = i;
	}
	
	public void execute(ScrabbleModel model) {
		Cell selectedCell = model.getCell(x, y);
		InventorySlot selectedSlot = model.getCurrentPlayerSlot(i);
		
		if (!selectedCell.placementFinalized) {
			model.swapTiles(selectedCell, selectedSlot);
		}
	}
}