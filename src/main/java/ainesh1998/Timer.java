package ainesh1998;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *  The timer, starts when the user makes a move after the cube has been scrambled.
 */

public class Timer extends AnimationTimer {
    boolean hasStarted;
    private boolean isInspection;
    private int penalty;  // 0 is none, 1 is +2, 2 is DNF
    private long startTime;
    private double currentTime;
    StringProperty timerString;

    Timer() {
        timerString = new SimpleStringProperty();
        hasStarted = false;
        isInspection = true;
        penalty = 0;
    }

    @Override
    public void handle(long now) {
        currentTime = (now-startTime)/Math.pow(10, 9);

        if (isInspection) {
            if (currentTime > 15 && currentTime < 17) {
                penalty = 1;
                timerString.setValue("+2");
            }

            else if (currentTime > 17) {
                penalty = 2;
                timerString.setValue("DNF");
            }

            else {
                int temp = 15 - (int) currentTime;
                timerString.setValue(String.format("%d", temp));
            }

        }

        else setTimerString(currentTime);
    }

    void startTimer() {
        start();
        startTime = System.nanoTime();
        hasStarted = true;
        isInspection = false;
    }

    void resetTimer() {
        stop();
        hasStarted = false;
        timerString.setValue("DNF");
    }

    void stopTimer() {
        stop();
        hasStarted = false;
    }

    void startInspection() {
        start();
        startTime = System.nanoTime();
        isInspection = true;
    }


    String getFinalTimeString() {
        if (penalty == 0) return timerString.getValue();
        if (penalty == 1) return timerString.getValue() + " +2";
        return "DNF";
    }

    double getFinalTime() {
        if (penalty == 0) return currentTime;
        if (penalty == 1) return currentTime+2;
        return Double.POSITIVE_INFINITY;
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
