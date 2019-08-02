package ainesh1998;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
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

    public static void main( String[] args ) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        cube = new Cube();
        Canvas canvas = new Canvas(500, 500);
        Group root = new Group(canvas);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this :: keyPress);
        stage.setTitle("3x3x3 Cube Simulator");
        stage.setScene(scene);
        g = canvas.getGraphicsContext2D();
        draw();
        stage.show();
    }

    private void draw() {
        g.clearRect(0, 0, 500, 500);
        g.fillRect(0, 0, 500, 500);
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

    private void keyPress(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case I:
                cube.rightCw();
                break;
            case K:
                cube.rightCcw();
                break;
            case E:
                cube.leftCcw();
                break;
            case D:
                cube.leftCw();
                break;
            case F:
                cube.upCcw();
                break;
            case J:
                cube.upCw();
                break;
            case G:
                cube.frontCcw();
                break;
            case H:
                cube.frontCw();
                break;
            case S:
                cube.downCw();
                break;
            case L:
                cube.downCcw();
                break;
            case W:
                cube.backCw();
                break;
            case O:
                cube.backCcw();
                break;
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
            case SPACE:
                cube.randomMovesScramble();
                break;
            case ESCAPE:
                cube.resetCube();
            default:
                break;
        }

        if (cube.isSolved()) System.out.println("solved");
        drawCube();
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
