package proj5;

public class ExchangeTiles implements Command {
	public ExchangeTiles() {
		
	}
	
	public void execute(ScrabbleModel m) {
		// TODO: allow selecting multiple tiles; return selected tiles to bag
				
		m.distributeTiles();
		m.nextTurn();
		
		m.endGame(); // TODO remove, for testing until exchange tile functionality added
	}
}