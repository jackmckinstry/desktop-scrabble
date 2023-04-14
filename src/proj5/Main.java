package proj5;

public class Main {
	public static void main(String args[]) {
		Model m = new ScrabbleModel();
		Controller c = new ScrabbleController((ScrabbleModel) m);
		View v = new ScrabbleView(c, (ScrabbleModel) m);
		m.attatch(v);
	}
}
