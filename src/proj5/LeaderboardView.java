package proj5;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


class PlayerComparator implements Comparator<Player> {
    public int compare(Player p1, Player p2) {
        return p2.getTotalPoints() - p1.getTotalPoints();
    }
}

public class LeaderboardView extends View {
	private ScrabbleModel model;
	private JFrame frame;
	
	public LeaderboardView(Controller c, ScrabbleModel m) {
		super(c);
		model = m;
		frame = new JFrame();
		frame.setTitle("Scrabble");
	}
	
	// rendering leaderboard needs to be outside of view's constructor so it can pull the player info at the end of the game
	public void renderLeaderboard() {
		JPanel headerPanel = new JPanel();
		JLabel headerLabel = new JLabel("<html>Game Over!<br><br></html>");
		headerLabel.setFont(new Font("Arial", Font.PLAIN, 48));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(headerLabel);
		
		ArrayList<Player> sortedPlayers = model.getPlayers();
		Collections.sort(sortedPlayers, new PlayerComparator());
		
		JPanel scorePanel = new JPanel();
		JLabel scoreLabel = new JLabel("<html>");
		for (Player p : sortedPlayers) {
			scoreLabel.setText(scoreLabel.getText() + p.name + ": " + p.getTotalPoints() + "<br><br>");
		}
		scoreLabel.setText(scoreLabel.getText() + "</html>");
		
		scoreLabel.setFont(new Font("Arial", Font.PLAIN, 36));
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(scoreLabel);
		
		// TODO add smaller version of board to game over screen?
		
		// render window
		frame.setLayout(new BorderLayout());
		frame.add(headerPanel, BorderLayout.PAGE_START);
		frame.add(scorePanel,BorderLayout.CENTER);
		frame.setSize(1000, 1000);
	}
	
	public void update() {
		if (model.gameEnded()) {
			this.renderLeaderboard();
			frame.setVisible(true);
		} else {
			frame.setVisible(false);
		}
	}
}
