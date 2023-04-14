package proj5;

public class MoveTile implements Command {
	Cell c1, c2;
	
	public MoveTile(Cell c1, Cell c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public void execute(ScrabbleModel model) {
		if (!c1.placementFinalized && !c2.placementFinalized) {
			model.moveTile(c1, c2);
		}
	}
}