package proj5;

public class Main {
	public static void main(String args[]) {
		Model m = new ScrabbleModel();
		Controller c = new ScrabbleController((ScrabbleModel) m);
		View mV = new MenuView(c, (ScrabbleModel) m);
		View sV = new ScrabbleView(c, (ScrabbleModel) m);
		View lV = new LeaderboardView(c, (ScrabbleModel) m);
		m.attatch(mV);
		m.attatch(sV);
		m.attatch(lV);
	}
}
