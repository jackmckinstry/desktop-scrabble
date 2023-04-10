package proj5;

public class ScrabbleModel extends Model {
	Board board;
	
	public ScrabbleModel() {
		board = new Board();
		
		// for testing
		board.placeTile(new Tile('A',1), 0, 0);
		board.placeTile(new Tile('B',3), 1, 2);
	}
}
