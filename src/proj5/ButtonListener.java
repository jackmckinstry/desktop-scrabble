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
	}
}
