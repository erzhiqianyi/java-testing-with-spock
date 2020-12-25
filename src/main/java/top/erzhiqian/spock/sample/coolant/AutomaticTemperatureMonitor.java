package top.erzhiqian.spock.sample.coolant;

public class AutomaticTemperatureMonitor {
    private static final Long ALARM_DEGREES = 20L;
    private static final Long SHUTDOWN_DEGREES = 50L;

    private final TemperatureReader reader;

    private TemperatureReadings lastReadings;

    private TemperatureReadings currentReadings;

    private final ReactorControl reactorControl;

    public AutomaticTemperatureMonitor(TemperatureReader reader, ReactorControl reactorControl) {
        this.reader = reader;
        this.reactorControl = reactorControl;
    }

    private boolean isTemperatureDiffMoreThan(long degrees) {
        boolean firstSensorTriggered = sensorTriggered(
                lastReadings.getSensorOneData(), currentReadings.getSensorOneData(), degrees);
        boolean secondSensorTriggered = sensorTriggered(
                lastReadings.getSensorTwoData(), currentReadings.getSensorTwoData(), degrees);
        boolean thirdSensorTriggered = sensorTriggered(
                lastReadings.getSensorThreeData(), currentReadings.getSensorThreeData(), degrees);
        return firstSensorTriggered || secondSensorTriggered || thirdSensorTriggered;
    }

    private boolean sensorTriggered(long lastData, long currentData, long degrees) {
        return Math.abs(lastData - currentData) > degrees;
    }

    public void readSensor() {
        lastReadings = currentReadings;
        currentReadings = reader.getCurrentReadings();
        // The first time we do nothing until a second reading is present
        if (lastReadings == null) {
            return;
        }

        if (isTemperatureDiffMoreThan(ALARM_DEGREES)) {
            reactorControl.activateAlarm();
        }
        if (isTemperatureDiffMoreThan(SHUTDOWN_DEGREES)) {
            reactorControl.shutdownReactor();
        }
    }
}
