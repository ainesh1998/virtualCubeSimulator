package ainesh1998;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * Sets up the scene and majority of the UI.
 *
 */

/* TODO: Add back face
   TODO: Add inspection
   TODO: Add stats
   TODO: Add confirmation when clearing session
 */

public class App extends Application
{
    private GraphicsContext g;
    private Cube cube;
    private Timer timer;
    private ListView<Label> listView;
    private Stats stats;

    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Initialise variables
        cube = new Cube();
        timer = new Timer();
        stats = new Stats();

        // Set up canvas
        Canvas canvas = new Canvas(500, 470);
        g = canvas.getGraphicsContext2D();

        // Set up scene
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("3x3x3 Cube Simulator");

        // Set up ListView
        listView = new ListView<>();
//        listView.setCellFactory(stringListView -> new CenteredListViewCell());

        // VBox - for the canvas and timer
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPrefWidth(scene.getWidth()-100);
        vb.getChildren().addAll(canvas, timer.timerLabel);

        // HBox - holds the VBox on the left, and the ListView (of times) on the right
        HBox hb = new HBox(vb, listView);
//        hb.set
        // add alignment stuff here and add listview (initialise it first)

        root.getChildren().addAll(hb);

        // Set up user actions
        root.addEventFilter(KeyEvent.KEY_PRESSED, this::keyPressed);

        // Display the scene
        draw();
        stage.show();
    }

    private void draw() {
        g.setFill(Color.BLACK);
        g.clearRect(0, 0, 500, 470);
        g.fillRect(0, 0, 500, 470);
        drawCube();
    }

    private void drawCube() {
        ArrayList<char[]> cubeState = cube.getState();

        // draw U face
        for (int i = 0; i < 9; i++) {
            int x = 150 + (33*i % 99);
            int y = 98 + (33 * (i/3));
            g.setFill(charToColour(cubeState.get(0)[i]));
            g.fillRect(x, y, 30, 30);
        }

        // draw L, F, R, B faces
        for (int j = 1; j < 5; j++) {
            for (int i = 0; i < 9; i++) {
                int x = j*100 + (33*i % 99) + (j*2 - 4) - 50;
                int y = 200 + (33 * (i/3));
                g.setFill(charToColour(cubeState.get(j)[i]));
                g.fillRect(x, y, 30, 30);
            }
        }

        // draw D face
        for (int i = 0; i < 9; i++) {
            int x = 150 + (33*i % 99);
            int y = 302 + (33 * (i/3));
            g.setFill(charToColour(cubeState.get(5)[i]));
            g.fillRect(x, y, 30, 30);
        }
    }

    private void keyPressed(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if (cube.moves.containsKey(code)) {
            cube.moves.get(code).run();
            if (cube.isScrambled && !timer.hasStarted) {
                cube.isScrambled = false;
                timer.startTimer();
            }

            if (timer.hasStarted && cube.isSolved()) {
                double time = timer.stopTimer();
                listView.getItems().addAll(new Label(String.format("%.2f", time)));
                stats.addTime(time);
            }
        }

        else {
            switch (code) {
                case Y:
                    cube.xCw();
                    break;
                case B:
                    cube.xCcw();
                    break;
                case A:
                    cube.yCcw();
                    break;
                case SEMICOLON:
                    cube.yCw();
                    break;
                case Q:
                    cube.zCcw();
                    break;
                case P:
                    cube.zCw();
                    break;
                case ESCAPE:
                    if (cube.isScrambled || timer.hasStarted) {
                        cube.resetCube();
                        timer.resetTimer();
                        listView.getItems().addAll(new Label("DNF"));
                        stats.addTime(Double.POSITIVE_INFINITY);
                    }

                    break;
                case SPACE:
                    if (!cube.isScrambled && !timer.hasStarted) {
                        cube.randomMovesScramble();
                        timer.startInspection();
                    }
                    break;
                default:
                    break;
            }
        }

        draw();
    }

    private Color charToColour(char c) {
        switch (c) {
            case 'Y':
                return Color.YELLOW;
            case 'W':
                return Color.WHITE;
            case 'G':
                return Color.GREEN;
            case 'B':
                return Color.BLUE;
            case 'R':
                return Color.RED;
            case 'O':
                return Color.ORANGE;
            default:
                return Color.BLACK;
        }
    }

}
