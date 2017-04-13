package BrowserWindow;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class BrowserWindow {
	Stage window;
	WebView webView;

	public BrowserWindow(Stage s, WebView w) {
		window = s;
		webView = w;
	}

	public void setSize(int width, int height) {
		window.setWidth(width);
		window.setHeight(height);
	}

	public void loadUrl(String url) {
		webView.getEngine().load(
			BrowserWindow.class.getResource(url).toExternalForm()
		);
	}

}