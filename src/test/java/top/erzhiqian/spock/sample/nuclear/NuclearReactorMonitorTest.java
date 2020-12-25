package top.erzhiqian.spock.sample.nuclear;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NuclearReactorMonitorTest {

    private final int triggeredFireSensors;
    private final List<Float> radiationDataReadings;
    private final int pressure;

    private final boolean expectedAlarmStatus;
    private final boolean expectedShutdownCommand;
    private final int expectedMinutesToEvacuate;

    public NuclearReactorMonitorTest(int pressure, int triggeredFireSensors,
                              List<Float> radiationDataReadings, boolean expectedAlarmStatus,
                              boolean expectedShutdownCommand, int expectedMinutesToEvacuate) {

        this.triggeredFireSensors = triggeredFireSensors;
        this.radiationDataReadings = radiationDataReadings;
        this.pressure = pressure;
        this.expectedAlarmStatus = expectedAlarmStatus;
        this.expectedShutdownCommand = expectedShutdownCommand;
        this.expectedMinutesToEvacuate = expectedMinutesToEvacuate;

    }


    @Test
    public void nuclearReactorScenario() {
        NuclearReactorMonitor monitor = new NuclearReactorMonitor();
        monitor.feedFireSensorData(triggeredFireSensors);
        monitor.feedRadiationData(radiationDataReadings);
        monitor.feedPressureInBar(pressure);

        NuclearReactorStatus status = monitor.getCurrentStatus();
        assertEquals("Expected no alarm", expectedAlarmStatus, status.isAlarmActive());
        assertEquals("No notifications", expectedShutdownCommand, status.isShutDownNeeded());
        assertEquals("No evacuation minutes", expectedMinutesToEvacuate, status.getEvacuationMinutes());
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {150, 0, new ArrayList<Float>(), false, false, -1},
                {150, 1, new ArrayList<Float>(), true, false, -1},
                {150, 3, new ArrayList<Float>(), true, true, -1},
                {150, 0, Arrays.asList(110.4f, 0.3f, 0.0f), true,
                        true, 1},
                {150, 0, Arrays.asList(45.3f, 10.3f, 47.7f), false,
                        false, -1},
                {155, 0, Arrays.asList(0.0f, 0.0f, 0.0f), true, false,
                        -1},
                {170, 0, Arrays.asList(0.0f, 0.0f, 0.0f), true, true,
                        3},
                {180, 0, Arrays.asList(110.4f, 0.3f, 0.0f), true,
                        true, 1},
                {500, 0, Arrays.asList(110.4f, 300f, 0.0f), true,
                        true, 1},
                {30, 0, Arrays.asList(110.4f, 1000f, 0.0f), true,
                        true, 1},
                {155, 4, Arrays.asList(0.0f, 0.0f, 0.0f), true, true,
                        -1},
                {170, 1, Arrays.asList(45.3f, 10.3f, 47.7f), true,
                        true, 3},});
    }
}