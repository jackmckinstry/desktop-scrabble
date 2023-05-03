package proj5;

import java.util.ArrayList;

/*
 * OO Pattern: Observer
 * As part of MVC, The Observer pattern exists in a Subject/Observer relationship between the Model and the View(s).
 */

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
