package t7;


public class AppController {

	private View view;

	public AppController(View view) {
		this.view = view;
	}

	public void enableFileChooser() {
		this.view.enableFileChooser();
	}

}
