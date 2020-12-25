package top.erzhiqian.spock.sample.firecontrol;

public class WarningStatus {
    private boolean alarmActive;

    private boolean fireDepartmentNotified;

    public WarningStatus(boolean alarmActive, boolean fireDepartmentNotified) {
        this.alarmActive = alarmActive;
        this.fireDepartmentNotified = fireDepartmentNotified;
    }

    public boolean isAlarmActive() {
        return alarmActive;
    }


    public boolean isFireDepartmentNotified() {
        return fireDepartmentNotified;
    }

}
