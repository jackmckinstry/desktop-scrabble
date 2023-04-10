package proj5;

public abstract class View {
	protected Controller controller;
	
	public View(Controller c) {
		controller = c;
	}
	public abstract void update();
}
