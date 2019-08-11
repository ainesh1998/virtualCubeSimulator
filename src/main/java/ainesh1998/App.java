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
 * Starts the JavaFX application and sets up the scene.
 *
 */

/* TODO: Add numbers in listview
   TODO: Make avg times get displayed when clicked
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

        // Set up scene
        Scene scene = new Scene(root, 600, 600, Color.BLACK);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, controller::keyPressed);
        stage.setTitle("3x3x3 Cube Simulator");
        stage.setScene(scene);
        stage.show();
    }
}
