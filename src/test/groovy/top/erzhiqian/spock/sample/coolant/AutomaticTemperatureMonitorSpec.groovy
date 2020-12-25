package top.erzhiqian.spock.sample.coolant

import spock.lang.Specification

class AutomaticTemperatureMonitorSpec extends Specification {
    def "if current temperature difference is more than 20 degree the alarm should sound"() {
        given: "that temperature readings are not with limits "
        TemperatureReadings prev = new TemperatureReadings(sensorOneData: 20,
                sensorTwoData: 40, sensorThreeData: 80)
        TemperatureReadings current = new TemperatureReadings(sensorOneData: 30,
                sensorTwoData: 10, sensorThreeData: 73)
        TemperatureReader reader = Stub(TemperatureReader)
        reader.getCurrentReadings() >>> [prev, current]

        ReactorControl  control = Mock(ReactorControl)
        AutomaticTemperatureMonitor monitor = new AutomaticTemperatureMonitor(reader,control)

        when: "we ask the status of temperature control"
        monitor.readSensor()
        monitor.readSensor()

        then: "the alarm should be sound"
        0 * control.shutdownReactor()
        1 * control.activateAlarm()
    }


    def "if current temperature difference is more than 50 degree the reactor shuts down"() {
        given: "that temperature readings are not with limits "
        TemperatureReadings prev = new TemperatureReadings(sensorOneData: 20,
                sensorTwoData: 40, sensorThreeData: 80)
        TemperatureReadings current = new TemperatureReadings(sensorOneData: 30,
                sensorTwoData: 10, sensorThreeData: 160)
        TemperatureReader reader = Stub(TemperatureReader)
        reader.getCurrentReadings() >>> [prev, current]

        ReactorControl  control = Mock(ReactorControl)
        AutomaticTemperatureMonitor monitor = new AutomaticTemperatureMonitor(reader,control)

        when: "we ask the status of temperature control"
        monitor.readSensor()
        monitor.readSensor()

        then: "the alarm should be sound"
        1 * control.shutdownReactor()
        1 * control.activateAlarm()
    }


    def "Testing of all 3 sensors with temperature that raise and fail"() {
        given: "various temperature readings"
        TemperatureReadings prev = new TemperatureReadings(sensorOneData: previousTemp[0],
                sensorTwoData: previousTemp[1], sensorThreeData: previousTemp[2])
        TemperatureReadings current = new TemperatureReadings(sensorOneData: currrentTemp[0],
                sensorTwoData: currrentTemp[1], sensorThreeData: currrentTemp[2])
        TemperatureReader reader = Stub(TemperatureReader)
        reader.getCurrentReadings() >>> [prev, current]

        ReactorControl  control = Mock(ReactorControl)
        AutomaticTemperatureMonitor monitor = new AutomaticTemperatureMonitor(reader,control)

        when: "we ask the status of temperature control"
        monitor.readSensor()
        monitor.readSensor()

        then: "the alarm should be sound"
        shutDown * control.shutdownReactor()
        alarm * control.activateAlarm()
        where: "possible temperature are:"
        previousTemp| currrentTemp       ||  alarm |shutDown
        [20, 30, 40]| [25, 15, 43.2]     ||     0  | 0
        [20, 30, 40]| [13.3, 37.8, 39.2] ||     0  | 0
        [20, 30, 40]| [50, 15, 43.2]     ||     1  | 0
        [20, 30, 40]| [-20, 15, 43.2]    ||     1  | 0
        [20, 30, 40]| [100, 15, 43.2]    ||     1  | 1
        [20, 30, 40]| [-80, 15, 43.2]    ||     1  | 1
        [20, 30, 40]| [20, 55, 43.2]     ||     1  | 0
        [20, 30, 40]| [20, 8  , 43.2]    ||     1  | 0
        [20, 30, 40]| [21, 100, 43.2]    ||     1  | 1
        [20, 30, 40]| [22, -40, 43.2]    ||     1  | 1
        [20, 30, 40]| [20, 35, 76]       ||     1  | 0
        [20, 30, 40]| [20, 31  ,13.2]    ||     1  | 0
        [20, 30, 40]| [21, 33, 97]       ||     1  | 1
        [20, 30, 40]| [22, 39, -22]      ||     1  | 1
    }



}
