package proj5;

public class Player {
	public InventorySlot tileInventory[];
	private int totalPoints;
	public String name;
	
	public Player(String n) {
		// initialize empty inventory
		tileInventory = new InventorySlot[7];
		for (int i = 0; i < 7; i++) {
			tileInventory[i] = new InventorySlot();
		}
		
		totalPoints = 0;
		name = n;
	}
	
	public void addPoints(int points) {
		totalPoints += points;
	}
	public int getTotalPoints() {
		return totalPoints;
	}
}
