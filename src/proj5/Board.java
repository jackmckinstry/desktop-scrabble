package proj5;

public class Board {
	public Cell cellArray[][];
	
	public Board() {
		cellArray = SimpleBoardFactory.createEmptyBoard();
	}
	
	public Tile getTile(int x, int y) {
		return cellArray[x][y].tile;
	}
	public void placeTile(Tile t, int x, int y) {
		cellArray[x][y].tile = t;
	}
}
