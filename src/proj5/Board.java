package proj5;

public class Board {
	public Cell cellArray[][];
	
	public Board() {
		cellArray = new Cell[15][15];

		// TODO: move to separate factory class? (also add multipliers)
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				cellArray[x][y] = new Cell(1,1);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		return cellArray[x][y].tile;
	}
	public void placeTile(Tile t, int x, int y) {
		cellArray[x][y].tile = t;
	}
}
