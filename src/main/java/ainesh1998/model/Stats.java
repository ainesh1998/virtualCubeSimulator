package ainesh1998.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *   Stores times and calculates averages etc.
 */

public class Stats {

    private ArrayList<Double> times;
    private double bestSingle;
    private double bestAo5;
    private double bestAo12;

    public Stats() {
        times = new ArrayList<>();
        resetBests();
    }

    public void addTime(double time) {
        times.add(time);

        // Update bests
        if (time < bestSingle) bestSingle = time;

        double tempAo5 = getCurrentAo5();
        double tempAo12 = getCurrentAo12();
        if (tempAo5 < bestAo5) bestAo5 = tempAo5;
        if (tempAo12 < bestAo12) bestAo12 = tempAo12;
    }

    public void deleteTime() {

    }

    public void clearSession() {
        times.clear();
        resetBests();
    }

    public double getBestTime() {
        return bestSingle;
    }

    public double getCurrentTime() {
        if (times.size() == 0) return Double.POSITIVE_INFINITY;
        return times.get(times.size() - 1);
    }

    public double getBestAo5() {
        return bestAo5;
    }

    public double getCurrentAo5() {
        return calculateCurrentAvg(5);
    }

    public double getBestAo12() {
        return bestAo12;
    }

    public double getCurrentAo12() {
        return calculateCurrentAvg(12);
    }

    /*
         PRIVATE METHODS
     */
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
        bestSingle = Double.POSITIVE_INFINITY;
        bestAo5 = Double.POSITIVE_INFINITY;
        bestAo12 = Double.POSITIVE_INFINITY;
    }
}
