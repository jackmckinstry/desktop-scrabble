package proj5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonListener implements ActionListener {
	private Controller controller;
	private TileListener tileListener;
	
	public ButtonListener(Controller c, TileListener t) {
		controller = c;
		tileListener = t;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.toString().contains("Place Tiles")) {
			tileListener.deselectAll();
			controller.handleEvent(new PlaceTiles());
		} else if (src.toString().contains("Exchange Tiles")) {
			ArrayList<Cell> toExchange = tileListener.getSelectedCells();
			controller.handleEvent(new ExchangeTiles(toExchange));
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
