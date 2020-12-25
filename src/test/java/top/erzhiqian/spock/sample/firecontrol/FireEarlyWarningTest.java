package top.erzhiqian.spock.sample.firecontrol;

import org.junit.Test;

import static org.junit.Assert.*;

public class FireEarlyWarningTest {

    @Test
    public void fireAlarmScenario() {
        FireEarlyWarning fireEarlyWarning = new FireEarlyWarning();
        int triggeredFireSensors = 1;
        fireEarlyWarning.feedData(triggeredFireSensors);
        WarningStatus status = fireEarlyWarning.getCurrentStatus();
        assertTrue("Alarm Sound", status.isAlarmActive());
        assertFalse("No notifications", status.isFireDepartmentNotified());
    }

    /**
     * bad test sample
     */
    @Test
    public void sensorsAreTriggered() {
        FireEarlyWarning fireEarlyWarning = new FireEarlyWarning();

        // another stimulus phase
        fireEarlyWarning.feedData(1);
        WarningStatus status = fireEarlyWarning.getCurrentStatus();

        assertTrue("Alarm Sound", status.isAlarmActive());
        assertFalse("No notifications", status.isFireDepartmentNotified());

        // another stimulus phase,this is bad practice
        fireEarlyWarning.feedData(2);

        assertTrue("Alarm Sound", status.isAlarmActive());
        assertTrue("Fire department is notified", status.isFireDepartmentNotified());
    }
}