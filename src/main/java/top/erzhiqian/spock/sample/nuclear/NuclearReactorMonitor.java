package top.erzhiqian.spock.sample.nuclear;

import java.util.ArrayList;
import java.util.List;

public class NuclearReactorMonitor {


    private static final Integer MIN_ALARM_PRESSURE = 150;

    private static final Integer MIN_SHUTDOWN_PRESSURE = 160;

    private static final Float MAX_SAFE_RADIATION_METER = 100f;

    private int triggeredFireSensors = 0;


    private List<Float> radiationStatus = new ArrayList<>();


    private int currentPressure = MIN_ALARM_PRESSURE;

    public void feedFireSensorData(int triggeredFireSensors) {
        this.triggeredFireSensors = triggeredFireSensors;
    }

    public void feedRadiationData(List<Float> radiationStatus) {
        this.radiationStatus = radiationStatus;
    }

    public void feedPressureInBar(int bar) {
        this.currentPressure = bar;
    }


    public NuclearReactorStatus getCurrentStatus() {
        boolean alarmStatus = getAlarmStatus();
        boolean shutDownNeeded = shouldShtDown();
        int evacuationMinutes = evacuationMinutes();
        return new NuclearReactorStatus(alarmStatus, shutDownNeeded, evacuationMinutes);
    }

    private int evacuationMinutes() {
        int evacuationMinutes = -1;
        if (radiationPresent()) {
            evacuationMinutes = 1;
        } else if (currentPressure > MIN_SHUTDOWN_PRESSURE) {
            evacuationMinutes = 3;
        }
        return evacuationMinutes;
    }

    private boolean shouldShtDown() {
        return (currentPressure > MIN_SHUTDOWN_PRESSURE) || radiationPresent() || (triggeredFireSensors > 1);
    }

    private boolean getAlarmStatus() {
        return triggeredFireSensors > 0 || currentPressure > MIN_ALARM_PRESSURE || radiationPresent();
    }

    private boolean radiationPresent() {
        return radiationStatus.stream().anyMatch(item -> item.compareTo(MAX_SAFE_RADIATION_METER) > 0);
    }

}
