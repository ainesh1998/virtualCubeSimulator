package ainesh1998;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the Stats class.
 */

public class StatsTest
{

    @Test
    public void testRandomTimesReturnsCorrectStats()
    {
        Stats stats = new Stats();
        stats.addTime(6.34);
        stats.addTime(7.88);
        stats.addTime(9.20);
        stats.addTime(10.14);
        stats.addTime(1.55);
        stats.addTime(4.73);
        stats.addTime(Double.POSITIVE_INFINITY);
        stats.addTime(14.33);
        stats.addTime(20.00);
        stats.addTime(1.41);
        stats.addTime(6.88);
        stats.addTime(14.38);
        stats.addTime(Double.POSITIVE_INFINITY);

        assertTrue(stats.getBestTime() == 1.41);
        assertTrue(stats.getCurrentTime() == Double.POSITIVE_INFINITY);
        assertTrue(Math.abs(stats.getBestAo5() - 7.27) < 0.01 );
        assertTrue(Math.abs(stats.getCurrentAo5() - 13.75) < 0.01);
        assertTrue(Math.abs(stats.getBestAo12() - 9.54) < 0.01);
        assertTrue(stats.getCurrentAo12() == Double.POSITIVE_INFINITY);
    }
}
