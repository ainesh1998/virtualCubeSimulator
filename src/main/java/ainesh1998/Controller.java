package ainesh1998;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Controller {

    @FXML
    private Canvas cubeCanvas;

    @FXML
    private Label timerLabel;

    private Cube cube;
    private Timer timer;
    private Stats stats;

    public Controller() {
        cube = new Cube();
        timer = new Timer();
    }

    @FXML
    public void initialize() {
        draw();
    }

    private void draw() {
        cubeCanvas.getGraphicsContext2D().setFill(Color.BLACK);
        cubeCanvas.getGraphicsContext2D().clearRect(0, 0, cubeCanvas.getWidth(), cubeCanvas.getHeight());
        cubeCanvas.getGraphicsContext2D().fillRect(0, 0, cubeCanvas.getWidth(), cubeCanvas.getHeight());
        drawCube();
    }

    private void drawCube() {
        ArrayList<char[]> cubeState = cube.getState();

        // draw U face
        for (int i = 0; i < 9; i++) {
            int x = 150 + (33*i % 99);
            int y = 48 + (33 * (i/3));
            cubeCanvas.getGraphicsContext2D().setFill(charToColour(cubeState.get(0)[i]));
            cubeCanvas.getGraphicsContext2D().fillRect(x, y, 30, 30);
        }

        // draw L, F, R, B faces
        for (int j = 1; j < 5; j++) {
            for (int i = 0; i < 9; i++) {
                int x = j*100 + (33*i % 99) + (j*2 - 4) - 50;
                int y = 150 + (33 * (i/3));
                cubeCanvas.getGraphicsContext2D().setFill(charToColour(cubeState.get(j)[i]));
                cubeCanvas.getGraphicsContext2D().fillRect(x, y, 30, 30);
            }
        }

        // draw D face
        for (int i = 0; i < 9; i++) {
            int x = 150 + (33*i % 99);
            int y = 252 + (33 * (i/3));
            cubeCanvas.getGraphicsContext2D().setFill(charToColour(cubeState.get(5)[i]));
            cubeCanvas.getGraphicsContext2D().fillRect(x, y, 30, 30);
        }
    }

//    @FXML
    void keyPressed(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if (cube.moves.containsKey(code)) {
            cube.moves.get(code).run();
            if (cube.isScrambled && !timer.hasStarted) {
                cube.isScrambled = false;
                timer.startTimer();
            }

            if (timer.hasStarted && cube.isSolved()) {
                double time = timer.stopTimer();
//                listView.getItems().addAll(new Label(String.format("%.2f", time)));
//                stats.addTime(time);
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
//                        listView.getItems().addAll(new Label("DNF"));
//                        stats.addTime(Double.POSITIVE_INFINITY);
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
