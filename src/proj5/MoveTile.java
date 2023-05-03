package proj5;

public class MoveTile implements Command {
	private Cell c1, c2;
	
	public MoveTile(Cell c1, Cell c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public void execute(ScrabbleModel m) {
		if (!c1.placementFinalized && !c2.placementFinalized) {
			
			m.moveTile(c1, c2);
		}
	}
}