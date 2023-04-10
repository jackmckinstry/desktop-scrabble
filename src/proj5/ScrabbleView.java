package proj5;

import javax.swing.*;
import java.awt.GridLayout;

public class ScrabbleView extends View {
	private ScrabbleModel model;
	JFrame f = new JFrame();
	
	public ScrabbleView(Controller c, ScrabbleModel m) {
		super(c);
		model = m;
	}
	
	public void update() {
		JPanel panel = new JPanel(new GridLayout(15, 15));
		JButton cells[][] = new JButton[15][15];
		
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				Cell c = model.board.getCell(x, y);
				cells[x][y] = new JButton(c.tileLetter());
				panel.add(cells[x][y]);
			}
		}
		
		panel.setSize(750, 750);
		f.add(panel);
		
		f.setSize(1000, 1000);
		f.setLayout(null);
		f.setVisible(true);
	}
}
