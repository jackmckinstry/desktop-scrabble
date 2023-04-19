package proj5;

public class Main {
	public static void main(String args[]) {
		// create model and controller
		Model m = new ScrabbleModel();
		Controller c = new ScrabbleController((ScrabbleModel) m);
		
		// create and attatch views to model
		View menu = new MenuView(c, (ScrabbleModel) m);
		View game = new GameView(c, (ScrabbleModel) m);
		View leaderboard = new LeaderboardView(c, (ScrabbleModel) m);
		m.attatch(menu);
		m.attatch(game);
		m.attatch(leaderboard);
		
		// open menu window to start game
		menu.update();
	}
}
