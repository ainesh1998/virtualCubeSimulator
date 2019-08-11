package ainesh1998;

import ainesh1998.model.Cube;
import ainesh1998.model.Stats;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Optional;

/**
 *   The Controller of app.fxml - handles the dynamic UI.
 */

public class Controller {

    @FXML
    private Canvas cubeCanvas;

    @FXML
    private Label timerLabel;

    @FXML
    private ListView<String> timeList;

    @FXML private Label currBest;
    @FXML private Label bestBest;
    @FXML private Label currAo5;
    @FXML private Label bestAo5;
    @FXML private Label currAo12;
    @FXML private Label bestAo12;

    private Cube cube;
    private Timer timer;
    private Stats stats;

    public Controller() {
        cube = new Cube();
        timer = new Timer();
        stats = new Stats();
    }

    @FXML
    public void initialize() {
        timerLabel.textProperty().bind(timer.timerString);
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

    void keyPressed(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if (cube.moves.containsKey(code)) {
            cube.moves.get(code).run();
            timerStartedOrStopped();
        }

        else if (cube.rotations.containsKey(code)) cube.rotations.get(code).run();

        else if (code == KeyCode.ESCAPE) stopSolve();
        else if (code == KeyCode.SPACE) startInspection();
        else if (code == KeyCode.BACK_SPACE) clearSession();

        draw();
    }

    private void setStats() {
        currBest.setText(doubleToString(stats.getCurrentTime()));
        bestBest.setText(doubleToString(stats.getBestTime()));
        currAo5.setText(doubleToString(stats.getCurrentAo5()));
        bestAo5.setText(doubleToString(stats.getBestAo5()));
        currAo12.setText(doubleToString(stats.getCurrentAo12()));
        bestAo12.setText(doubleToString(stats.getBestAo12()));
    }

    private void timerStartedOrStopped() {
        if (cube.isScrambled && !timer.hasStarted) {
            cube.isScrambled = false;
            timer.startTimer();
            timerLabel.setTextFill(Color.WHITE);
        }

        if (!cube.isScrambled && timer.hasStarted && cube.isSolved()) {
            timer.stopTimer();
            timeList.getItems().add(timer.getFinalTimeString());
            stats.addTime(timer.getFinalTime());
            setStats();
        }
    }

    private void startInspection() {
        if (!cube.isScrambled && !timer.hasStarted) {
            cube.randomMovesScramble();
            timer.startInspection();
            timerLabel.setTextFill(Color.RED);
        }
    }

    private void stopSolve() {
        if (cube.isScrambled || timer.hasStarted) {
            cube.resetCube();
            timer.resetTimer();
            stats.addTime(Double.POSITIVE_INFINITY);
            timerLabel.setTextFill(Color.WHITE);
            timeList.getItems().add("DNF");
            setStats();
        }
    }

    private void clearSession() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Clear session?");
        String s = "Are you sure you would like to clear this session?";
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            stats.clearSession();
            timeList.getItems().clear();
            setStats();
        }
    }

    /*
          HELPER METHODS
     */
    private String doubleToString(double time) {
        if (time == Double.POSITIVE_INFINITY) return "DNF";

        if (time < 60) return String.format("%.2f", time);

        int minutes = (int) time/60;
        double seconds = time - minutes*60;
        String secondsString = seconds < 10 ? "0" + String.format("%.2f", seconds) : String.format("%.2f", seconds);
        return minutes + ":" + secondsString;
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
