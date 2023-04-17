package proj5;

import java.util.ArrayList;

public class SimpleBagFactory {
	private static final char[] LETTERS = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	private static final int[] VALUES = {0, 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
	private static final int[] AMOUNTS = {2, 9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
	
	public static ArrayList<Tile> createFullBag() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		
		for (int i = 0; i < LETTERS.length; i++) {
			for (int j = 0; j < AMOUNTS[i]; j++) {
				tiles.add(new Tile(LETTERS[i], VALUES[i]));
			}
		}
		
		return tiles;
	}
}
