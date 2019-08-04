package ainesh1998;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *  The timer, starts when the user makes a move after the cube has been scrambled.
 */

public class Timer extends AnimationTimer {
    boolean hasStarted;
    Label timerLabel;
//    DoubleProperty timeSeconds;
    private long startTime;
    private double currentTime;

    Timer() {
        hasStarted = false;
//        timeSeconds = new SimpleDoubleProperty(0.00);
        timerLabel = new Label("0.00");
//        timerLabel.setLayoutX(185);
//        timerLabel.setLayoutY(450);

        // Configure the Label
//        timerLabel.textProperty().bind(timeSeconds.asString("%.2f"));
        timerLabel.setFont(new Font("Courier", 50));
        timerLabel.setVisible(false);
    }

    @Override
    public void handle(long now) {
        if (!hasStarted) {
            startTime = now;
            hasStarted = true;
        }

        double time = (now-startTime)/Math.pow(10, 9);
        currentTime = time;
//        timeSeconds.set(time);
//        Integer seconds = (int) time;
//        String secondsString = seconds < 10 ? String.format("%01d", seconds) : seconds.toString();
//
//        Integer centiseconds = (int) ((time - seconds) * 100);
//        String centiString = centiseconds < 10 ? String.format("%01d", centiseconds) : centiseconds.toString();

        if (time < 60) {
            String timeString = String.format("%.2f", time);
            timerLabel.setText(timeString);
        }
        else {
            int minutes = (int) time/60;
            double seconds = time - minutes*60;
            String secondsString = seconds < 10 ? "0" + String.format("%.2f", seconds) : String.format("%.2f", seconds);
            timerLabel.setText(minutes + ":" + secondsString);
        }
    }

    void startTimer() {
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setVisible(true);
        start();
    }

    void resetTimer() {
        stop();
        hasStarted = false;
        timerLabel.setVisible(false);
    }

    double stopTimer() {
        stop();
        hasStarted = false;
        return currentTime;
    }

    void startInspection() {
        timerLabel.setTextFill(Color.RED);
    }
}
