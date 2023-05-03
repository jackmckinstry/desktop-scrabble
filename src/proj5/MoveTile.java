package proj5;

public class MoveTile implements Command {
	private CellPanel c1, c2;
	
	public MoveTile(CellPanel c1, CellPanel c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public void execute(ScrabbleModel m) {
		if (!c1.cell.placementFinalized && !c2.cell.placementFinalized) {
			
			m.moveTile(c1.cell, c2.cell);
		}
	}
}