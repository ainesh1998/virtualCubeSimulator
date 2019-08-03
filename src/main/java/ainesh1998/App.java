package ainesh1998;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * Hello world!
 *
 */

public class App extends Application
{
    private GraphicsContext g;
    private Cube cube;
    private Timer timer;

    public static void main( String[] args ) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        // Initialise variables
        cube = new Cube();
        timer = new Timer();
        Canvas canvas = new Canvas(500, 470);
        g = canvas.getGraphicsContext2D();

        // Set up scene
        Group root = new Group();
        Scene scene = new Scene(root, 500, 600, Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("3x3x3 Cube Simulator");
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPrefWidth(scene.getWidth());
        vb.getChildren().addAll(canvas, timer.timerLabel);
        root.getChildren().addAll(vb);

        // Set up user actions
        scene.setOnKeyPressed(this ::keyPressed);

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
            int x = 200 + (33*i % 99);
            int y = 98 + (33 * (i/3));
            g.setFill(charToColour(cubeState.get(0)[i]));
            g.fillRect(x, y, 30, 30);
        }

        // draw L, F, R faces
        for (int j = 1; j < 4; j++) {
            for (int i = 0; i < 9; i++) {
                int x = j*100 + (33*i % 99) + (j*2 - 4);
                int y = 200 + (33 * (i/3));
                g.setFill(charToColour(cubeState.get(j)[i]));
                g.fillRect(x, y, 30, 30);
            }
        }

        // draw D face
        for (int i = 0; i < 9; i++) {
            int x = 200 + (33*i % 99);
            int y = 302 + (33 * (i/3));
            g.setFill(charToColour(cubeState.get(4)[i]));
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
                System.out.println("timer has started!");
            }

            if (timer.hasStarted && cube.isSolved()) {
                timer.stopTimer();
                System.out.println("timer has stopped because you have solved the cube!");
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
                    cube.resetCube();
                    timer.resetTimer();
                    System.out.println("DNF");
                    break;
                case SPACE:
                    if (!cube.isScrambled && !timer.hasStarted) {
                        cube.randomMovesScramble();
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
