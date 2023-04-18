package proj5;

import java.util.ArrayList;

public class TileBag {
	private ArrayList<Tile> tiles;
	
	public TileBag() {
		tiles = SimpleBagFactory.createFullBag();
	}
	
	Tile pickTile() {
		if (tiles.isEmpty()) {
			return null;
		} else {
			return tiles.remove((int) (Math.random() * tiles.size()));
		}
	}
	
	public boolean isEmpty() {
		return tiles.isEmpty();
	}
	
	void returnTile(Tile t) {
		tiles.add(t);
	}
}
