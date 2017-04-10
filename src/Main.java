import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import java.io.File;
import org.omg.CORBA.SystemException;
import netscape.javascript.JSObject;
import FileSystem.FileSystem;
import BrowserWindow.BrowserWindow;

public class Main extends Application {

    String appData;
    String userRoot;
    String os;
    Stage main;
    FileSystem fs;
    BrowserWindow window;

    public static void main(String[] args) { launch(args); }

    @Override public void start(Stage stage) {
        os = System.getProperty("os.name");

        userRoot = System.getProperty("user.home");
        if (os.startsWith("Windows")) {
            appData = userRoot + "/AppData/local/Pheonix"; // If we are on windows, go to appdata
        }
        if (os.startsWith("Mac OS X")) {
            appData = userRoot + "/Library/Application Support/Pheonix"; // If we are on mac, go to application support
        }
        createDirectory(appData); // Ensure that we have the Pheonix data path by creating one if it does not exist

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webView.getEngine().load(
          Main.class.getResource("/index.html").toExternalForm()            
        );

        stage.setScene(new Scene(webView));
        stage.setMaximized(true);
        stage.show();

        fs = new FileSystem(appData, userRoot);
        window = new BrowserWindow();

        JSObject window = (JSObject) webEngine.executeScript("window");
        window.setMember("fs", fs);
        window.setMember("app", new AppMain());
        window.setMember("BrowserWindow", window);

        main = stage;
    }

    /*
    * Takes a path then checks if it exists
    * If it doesn't, one is created
    */
    public void createDirectory(String path) {
        File dir = new File(appData);
        boolean exists = dir.exists();

        if (exists != true) {
            dir.mkdir();
        }
    }

    public class AppMain {

        public void print(String content) {
            System.out.println(content);
        }

    }

}