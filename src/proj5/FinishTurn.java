package proj5;

public class FinishTurn implements Command {
	public FinishTurn() {
		
	}
	
	public void execute(ScrabbleModel m) {
		m.finishTurn();
	}
}