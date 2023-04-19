package proj5;

import java.util.ArrayList;

public class ScrabbleModel extends Model {
	private Board board;
	private TileBag bag;
	private ArrayList<Player> players;
	private int currentPlayer, turnNumber;
	private GameState state;
	
	public ScrabbleModel() {
		board = new Board();
		bag = new TileBag();
		
		players = new ArrayList<Player>();
		currentPlayer = 0;
		turnNumber = 1;
		
		state = GameState.MENU;
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
		
		updateViews();
	}
	
	public Cell getCell(int x, int y) {
		return board.cellArray[x][y];
	}
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}
	
	public String getTurnString() {
		return "Turn " + turnNumber + " - " + getCurrentPlayer().name + " - Score: " + players.get(currentPlayer).getTotalPoints();
	}
	
	public void nextTurn() {
		turnNumber++;
		currentPlayer = (currentPlayer + 1) % players.size();
		
		updateViews();
	}
	
	public void newPlayer(String name) {
		players.add(new Player(name));
	}
	
	public void moveTile(Cell c1, Cell c2) {
		// swap tile between inventory and board (either may be null)
		Tile tempTile = c1.tile;
		c1.tile = c2.tile;
		c2.tile = tempTile;
		
		updateViews();
	}
	
	public void exchangeTiles() {
		updateViews(); // TODO for testing; get rid of this & implement exchange tiles functionality
	}
	
	public void setState(GameState s) {
		state = s;
		updateViews();
	}
	public GameState getState() {
		return state;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	
}
