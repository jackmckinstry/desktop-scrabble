package proj5;

public class StartGame implements Command {
	private int num_players;
	
	public StartGame(int players) {
		this.num_players = players;
	}
	
	public void execute(ScrabbleModel m) {
		m.startGame(num_players);
	}
}