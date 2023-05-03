package proj5;

/*
 * OO Pattern: Command
 * The Command pattern is implemented for the StartGame, FinishTurn, MoveTile, and ExchangeTiles commands 
 * with the ScrabbleController class functioning as the invoker 
 * and the ScrabbleModel class functioning as the receiver.
 */
public interface Command {
	public void execute(ScrabbleModel m);
}
