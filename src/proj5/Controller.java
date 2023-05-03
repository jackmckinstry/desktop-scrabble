package proj5;

/*
 * OO Pattern: MVC
 * The Model-View-Controller pattern is implemented with abstractions of each 
 * and concrete ScrabbleModel, ScrabbleView (and other views), and ScrabbleController classes completing their respective behaviors.
 */
public abstract class Controller {
	public Controller() {
		
	}
	
	public abstract void handleEvent(Command c);
}
