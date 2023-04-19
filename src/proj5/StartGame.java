package proj5;

public class StartGame implements Command {
	private int num_players;
	
	public StartGame(int players) {
		this.num_players = players;
	}
	
	public void execute(ScrabbleModel m) {
		for (int i = 1; i <= num_players; i++) {
			m.newPlayer("Player " + i);	
		}
		
		m.distributeTiles();
		m.startGame();
	}
}