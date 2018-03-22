package com.example.harish.proschoolforparentadvanced.suresh.dashboard;

/**
 * Created by harish on 3/14/2018.
 */

public class TimeTableDashBoardModel {
    public TimeTableDashBoardModel(String time, String subject) {
        this.time = time;
        this.subject = subject;
    }

    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    String subject;
}
