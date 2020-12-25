package top.erzhiqian.spock.sample.coolant;

public class TemperatureReadings {
    private long sensorOneData;
    private long sensorTwoData;
    private long sensorThreeData;

    public long getSensorOneData() {
        return sensorOneData;
    }

    public void setSensorOneData(long sensorOneData) {
        this.sensorOneData = sensorOneData;
    }

    public long getSensorTwoData() {
        return sensorTwoData;
    }

    public void setSensorTwoData(long sensorTwoData) {
        this.sensorTwoData = sensorTwoData;
    }

    public long getSensorThreeData() {
        return sensorThreeData;
    }

    public void setSensorThreeData(long sensorThreeData) {
        this.sensorThreeData = sensorThreeData;
    }
}
