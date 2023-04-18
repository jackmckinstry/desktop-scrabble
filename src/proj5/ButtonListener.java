package proj5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
	private Controller controller;
	
	public ButtonListener(Controller c) {
		controller = c;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.toString().contains("Finish Turn")) {
			controller.handleEvent(new FinishTurn());
		} else if (src.toString().contains("Exchange Letters")) {
			// controller.handleEvent(new ExchangeTiles());
		}
		else if (src.toString().contains("Two")) {
			controller.handleEvent(new StartGame(2));
		}
		else if (src.toString().contains("Three")) {
			controller.handleEvent(new StartGame(3));
		}
		else if (src.toString().contains("Four")) {
			controller.handleEvent(new StartGame(4));
		}
	}
}
