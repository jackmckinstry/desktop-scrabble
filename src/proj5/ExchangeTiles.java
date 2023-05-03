package proj5;

import java.util.ArrayList;

public class ExchangeTiles implements Command {
	private ArrayList<Cell> toExchange;
	
	public ExchangeTiles(ArrayList<Cell> t) {
		toExchange = t;
	}
	
	public void execute(ScrabbleModel m) {
		// ensure no tiles have been placed on this turn
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				Cell c = m.getCell(x, y);
				if (c.hasTile() && !c.placementFinalized) {
					System.out.println("Cannot exchange tiles with tiles placed.");
					return;
				}
			}
		}
		
		// return and get new tiles
		for (Cell c : toExchange) {
			m.returnTile(c);
		}
		
		m.distributeTiles();
		m.nextTurn();
	}
}