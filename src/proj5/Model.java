package proj5;

import java.util.ArrayList;

public abstract class Model {
	private ArrayList<View> views;
	
	public Model() {
		views = new ArrayList<View>();
	}
	
	public void attatch(View v) {
		views.add(v);
	}
	public void detach(View v) {
		views.remove(v);
	}
	public void updateViews() {
		for (View v : views) {
			v.update();
		}
	}
}
