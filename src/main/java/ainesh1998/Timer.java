package ainesh1998;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Timer extends AnimationTimer {
    boolean hasStarted;
    Label timerLabel;
    DoubleProperty timeSeconds;
    private long startTime;

    Timer() {
        hasStarted = false;
        timeSeconds = new SimpleDoubleProperty(0.00);
        timerLabel = new Label();
        timerLabel.setLayoutX(185);
        timerLabel.setLayoutY(450);

        // Configure the Label
        timerLabel.textProperty().bind(timeSeconds.asString("%.2f"));
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setStyle("-fx-font-size: 4em;");
        timerLabel.setVisible(false);
    }

    @Override
    public void handle(long now) {
        if (!hasStarted) {
            startTime = now;
            hasStarted = true;
        }

        double time = (now-startTime)/Math.pow(10, 9);
        timeSeconds.set(time);
//        System.out.println(String.format("%.2f", time));
    }

    void startTimer() {
        timerLabel.setVisible(true);
        start();
    }

    void resetTimer() {
        stop();
        hasStarted = false;
        timerLabel.setVisible(false);
    }

    void stopTimer() {
        stop();
        hasStarted = false;

    }
}
