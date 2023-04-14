package proj5;

public class ScrabbleController extends Controller {
	private ScrabbleModel model;
	
	public ScrabbleController(ScrabbleModel m) {
		model = m;
	}
	
	public void handleEvent(Command c) {
		c.execute(model);
		
	}
}
