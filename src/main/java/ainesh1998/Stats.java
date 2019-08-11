package ainesh1998;

import java.util.ArrayList;
import java.util.Collections;

/**
 *   Stores times and calculates averages etc.
 */

public class Stats {

    private ArrayList<Double> times;
    private double bestBest;
    private double bestAo5;
    private double bestAo12;

    Stats() {
        times = new ArrayList<>();
        resetBests();
    }

    void addTime(double time) {
        times.add(time);

        // Update bests
        if (time < bestBest) bestBest = time;

        double tempAo5 = getCurrentAo5();
        double tempAo12 = getCurrentAo12();
        if (tempAo5 < bestAo5) bestAo5 = tempAo5;
        if (tempAo12 < bestAo12) bestAo12 = tempAo12;
    }

    void deleteTime() {

    }

    void clearSession() {
        times.clear();
        resetBests();
    }

    double getBestTime() {
        return bestBest;
    }

    double getCurrentTime() {
        if (times.size() == 0) return Double.POSITIVE_INFINITY;
        return times.get(times.size() - 1);
    }

    double getBestAo5() {
        return bestAo5;
    }

    double getCurrentAo5() {
        return calculateCurrentAvg(5);
    }

    double getBestAo12() {
        return bestAo12;
    }

    double getCurrentAo12() {
        return calculateCurrentAvg(12);
    }

    /*
         PRIVATE METHODS
     */
    private double calculateBestAvg(int count) {
        double currentBest = Double.POSITIVE_INFINITY;
        for (int i = 0; i < times.size() - count + 1; i++) {
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

    private void resetBests() {
        bestBest = Double.POSITIVE_INFINITY;
        bestAo5 = Double.POSITIVE_INFINITY;
        bestAo12 = Double.POSITIVE_INFINITY;
    }
}
