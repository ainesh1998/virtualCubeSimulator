package ainesh1998;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Sets up the scene and majority of the UI.
 *
 */

/* TODO: Add inspection
   TODO: Add stats
   TODO: Add confirmation when clearing session
 */

public class App extends Application
{

    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("app.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();

        Scene scene = new Scene(root, 600, 600, Color.BLACK);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> controller.keyPressed(event));
        stage.setTitle("3x3x3 Cube Simulator");
        stage.setScene(scene);
        stage.show();

    }
}
