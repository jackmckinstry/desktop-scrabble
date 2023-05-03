package proj5;

import java.util.ArrayList;

public class ScrabbleModel extends Model {
	private Board board;
	private TileBag bag;
	private ArrayList<Player> players;
	private ArrayList<String> names;
	private int currentPlayer, turnNumber;
	private boolean gameStarted, gameEnded;
	
	public ScrabbleModel() {
		board = new Board();
		bag = new TileBag();
		
		players = new ArrayList<Player>();
		currentPlayer = 0;
		turnNumber = 1;
		
		gameStarted = false;
		gameEnded = false;
	}
	
	public void returnTile(Cell c) {
		bag.returnTile(c.tile);
		c.tile = null;
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
	}
	
	public Cell getCell(int x, int y) {
		return board.cellArray[x][y];
	}
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}
	
	public void newPlayer(String name) {
		players.add(new Player(name));
	}
	
	public void setNames(ArrayList<String> nameList) {
		this.names = new ArrayList<String>();
		for (String n : nameList ) {
			names.add(n);
		}
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public String getTurnString() {
		return "Turn " + turnNumber + " - " + getCurrentPlayer().name + " - Score: " + players.get(currentPlayer).getTotalPoints();
	}
	
	public void nextTurn() {
		turnNumber++;
		currentPlayer = (currentPlayer + 1) % players.size();
		
		updateViews();
	}
	
	public void moveTile(Cell c1, Cell c2) {
		// swap tile between inventory and board (either may be null)
		Tile tempTile = c1.tile;
		c1.tile = c2.tile;
		c2.tile = tempTile;
		
		updateViews();
	}
	
	public void startGame(int num_players) {
		updateViews();
		for (int i = 1; i <= num_players; i++) {
			newPlayer(names.get(i-1));	
		}
		
		distributeTiles();
		
		gameStarted = true;
		updateViews();
	}
	public boolean gameStarted() {
		return gameStarted;
	}
	
	public void endGame() {
		gameEnded = true;
		updateViews();
	}
	public boolean gameEnded() {
		return gameEnded;
	}
}
