package proj5;

/*
 * OO Pattern: Factory
 * The Simple Factory pattern is implemented for the creation of a Board with the proper cells containing word/letter bonuses.
 */
public class SimpleBoardFactory {
	public static Cell[][] createEmptyBoard() {
		Cell[][] cellArray = new Cell[15][15];

		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				// calculates cell position reflected onto Q1 on an 8x8 grid
				int q1id = 8*(x<=7 ? x : 14-x) + (y<=7 ? y : 14-y);
				
				// creates cell based on letter/word multiplier
				switch (q1id) {
					case 0, 7, 56: // triple word multiplier
						cellArray[x][y] = new Cell(1,3);
						break;
					case 9, 18, 27, 36, 63: // double word multiplier
						cellArray[x][y] = new Cell(1,2);
						break;
					case 13, 41, 45: // triple letter multiplier
						cellArray[x][y] = new Cell(3,1);
						break;
					case 3, 22, 24, 31, 50, 54, 59: // double letter multiplier
						cellArray[x][y] = new Cell(2,1);
						break;
					default:
						cellArray[x][y] = new Cell(1,1);
						break;
				}
			}
		}
		
		return cellArray;
	}
}
