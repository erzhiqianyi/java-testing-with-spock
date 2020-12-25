package top.erzhiqian.spock.sample.firecontrol

import spock.lang.Specification

class FireEarlyWarningSpec extends Specification {
    def "If all sensors are inactive everything is ok"() {
        given: "that all  fire sensors are off"
        FireEarlyWarning fireEarlyWarning = new FireEarlyWarning()
        int triggeredFireSensors = 0

        when: "we ask the status of fire control"
        fireEarlyWarning.feedData(triggeredFireSensors)
        WarningStatus status = fireEarlyWarning.getCurrentStatus()

        then: "No alarm/notification should be triggered"
        !status.isAlarmActive()
        !status.isFireDepartmentNotified()

    }

    def "If one sensor is active the alarm should sound as a precaution"() {
        given: "that only one fire sensor is active"
        FireEarlyWarning fireEarlyWarning = new FireEarlyWarning()
        int triggeredFireSensors = 1

        when: "we ask the status of fire control"
        fireEarlyWarning.feedData(triggeredFireSensors)
        WarningStatus status = fireEarlyWarning.getCurrentStatus()

        then: "only the  alarm should be triggered"
        status.isAlarmActive()
        !status.isFireDepartmentNotified()

    }


    def "If more than  one sensor is active then we have a fire"() {
        given: "that only one fire sensor is active"
        FireEarlyWarning fireEarlyWarning = new FireEarlyWarning()
        int triggeredFireSensors = 1

        when: "we ask the status of fire control"
        fireEarlyWarning.feedData(triggeredFireSensors)
        WarningStatus status = fireEarlyWarning.getCurrentStatus()

        then: "only the  alarm should be triggered"
        status.isAlarmActive()
        !status.isFireDepartmentNotified()

    }


}
