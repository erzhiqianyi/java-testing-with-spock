package top.erzhiqian.spock.sample.coolant

import spock.lang.Specification

class TemperatureMonitorSpec extends Specification {

    def "if current temperature difference is with limits everything is ok"() {
        given: "that temperature readings are with limits "
        TemperatureReadings prev = new TemperatureReadings(sensorOneData: 20,
                sensorTwoData: 40, sensorThreeData: 80)
        TemperatureReadings current = new TemperatureReadings(sensorOneData: 30,
                sensorTwoData: 45, sensorThreeData: 73)
        TemperatureReader reader = Stub(TemperatureReader)
        reader.getCurrentReadings() >>> [prev, current]

        TemperatureMonitor monitor = new TemperatureMonitor(reader);

        when: "we ask the status of temperature control"
        monitor.readSensor()
        monitor.readSensor()

        then: "everything should be ok"
        monitor.isTemperatureNormal()
    }


    def "if current temperature difference is more than 20 degree the alarm should sound"() {
        given: "that temperature readings are not with limits "
        TemperatureReadings prev = new TemperatureReadings(sensorOneData: 20,
                sensorTwoData: 40, sensorThreeData: 80)
        TemperatureReadings current = new TemperatureReadings(sensorOneData: 30,
                sensorTwoData: 10, sensorThreeData: 73)
        TemperatureReader reader = Stub(TemperatureReader)
        reader.getCurrentReadings() >>> [prev, current]

        TemperatureMonitor monitor = new TemperatureMonitor(reader);

        when: "we ask the status of temperature control"
        monitor.readSensor()
        monitor.readSensor()

        then: "the alarm should be sound"
        !monitor.isTemperatureNormal()
    }

}
