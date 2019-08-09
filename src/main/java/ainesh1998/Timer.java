package ainesh1998;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *  The timer, starts when the user makes a move after the cube has been scrambled.
 */

public class Timer extends AnimationTimer {
    boolean hasStarted;
    private long startTime;
    private double currentTime;
    StringProperty timerString;

    Timer() {
        timerString = new SimpleStringProperty();
        hasStarted = false;
    }

    @Override
    public void handle(long now) {
        if (!hasStarted) {
            startTime = now;
            hasStarted = true;
        }

        currentTime = (now-startTime)/Math.pow(10, 9);
        setTimerString(currentTime);
    }

    void startTimer() {
        start();
    }

    void resetTimer() {
        stop();
        hasStarted = false;
        timerString.setValue("DNF");
    }

    double stopTimer() {
        stop();
        hasStarted = false;
        return currentTime;
    }

    private void setTimerString(double time) {
        if (time < 60) {
            String string = String.format("%.2f", time);
            timerString.setValue(string);
        }
        else {
            int minutes = (int) time/60;
            double seconds = time - minutes*60;
            String secondsString = seconds < 10 ? "0" + String.format("%.2f", seconds) : String.format("%.2f", seconds);
            timerString.setValue(minutes + ":" + secondsString);
        }
    }
}
