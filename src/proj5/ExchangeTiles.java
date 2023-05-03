package proj5;

public class ExchangeTiles implements Command {
	public ExchangeTiles() {
		
	}
	
	public void execute(ScrabbleModel m) {
		m.exchangeTiles();
		
		m.distributeTiles();
		m.nextTurn();
	}
}