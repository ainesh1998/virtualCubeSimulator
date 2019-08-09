package ainesh1998;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.Collections;

public class Stats {

    private ArrayList<Double> times;

    Stats() {
        times = new ArrayList<>();
    }

    void addTime(double time) {
        times.add(time);
    }

    void deleteTime() {

    }

    void clearSession() {
        times.clear();
    }

    double getBestTime() {
        if (times.size() == 0) return Double.POSITIVE_INFINITY;
        ArrayList<Double> sorted = times;
        Collections.sort(sorted);
        return sorted.get(0);
    }

    double getCurrentTime() {
        if (times.size() == 0) return Double.POSITIVE_INFINITY;
        return times.get(times.size() - 1);
    }

    double getBestAvg5() {
        return calculateBestAvg(5);
    }

    double getCurrentAvg5() {
        return calculateCurrentAvg(5);
    }

    double getBestAvg12() {
        return calculateBestAvg(12);
    }

    double getCurrentAvg12() {
        return calculateCurrentAvg(12);
    }

    /*
         PRIVATE METHODS
     */
    private double calculateBestAvg(int count) {
        double currentBest = Double.POSITIVE_INFINITY;
        for (int i = 0; i < times.size() - (count - 1); i++) {
            ArrayList<Double> temp = new ArrayList<>();

            for (int j = i; j < i + count; j++) {
                temp.add(times.get(j));
            }

            Collections.sort(temp);
            double tempAvg = 0.0;

            for (int k = 1; k < count-1; k++) {
                tempAvg += temp.get(k);
            }

            tempAvg = tempAvg/(count-2);
            if (tempAvg < currentBest) currentBest = tempAvg;
        }
        return currentBest;
    }

    private double calculateCurrentAvg(int count) {
        if (times.size() < count) {
            return Double.POSITIVE_INFINITY;
        }

        ArrayList<Double> temp = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            temp.add(times.get(times.size() - i - 1));
        }
        Collections.sort(temp);
        double tempAvg = 0.0;

        for (int k = 1; k < count-1; k++) {
            tempAvg += temp.get(k);
        }

        tempAvg = tempAvg/(count-2);
        return tempAvg;
    }
}
