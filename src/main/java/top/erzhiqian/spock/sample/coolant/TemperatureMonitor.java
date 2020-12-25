package top.erzhiqian.spock.sample.coolant;

public class TemperatureMonitor {
    private final TemperatureReader reader;
    private TemperatureReadings lastReadings;
    private TemperatureReadings currentReadings;

    public TemperatureMonitor(final TemperatureReader reader) {
        this.reader = reader;
    }

    public boolean isTemperatureNormal() {
        boolean firstSensorOK = sensorOk(lastReadings.getSensorOneData(), currentReadings.getSensorOneData());
        boolean secondSensorOK = sensorOk(lastReadings.getSensorTwoData(), currentReadings.getSensorTwoData());
        boolean thirdSensorOK = sensorOk(lastReadings.getSensorThreeData(), currentReadings.getSensorThreeData());
        return firstSensorOK && secondSensorOK && thirdSensorOK;
    }

    private boolean sensorOk(long last, long current) {
        return Math.abs(last - current) < 20;
    }

    public void readSensor() {
        lastReadings = currentReadings;
        currentReadings = reader.getCurrentReadings();
    }
}
