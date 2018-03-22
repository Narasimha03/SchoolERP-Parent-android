package com.example.harish.proschoolforparentadvanced.suresh.noticeboard;

/**
 * Created by harish on 07-11-2017.
 */

public class AdminDashboardEventClass {

    private String adminEventDate;
    private String adminEeventText;

    public AdminDashboardEventClass() {
    }

    public AdminDashboardEventClass(String adminEventDate, String adminEeventText) {
        this.adminEventDate = adminEventDate;
        this.adminEeventText = adminEeventText;
    }

    public String getAdminEventDate() {
        return adminEventDate;
    }

    public void setAdminEventDate(String adminEventDate) {
        this.adminEventDate = adminEventDate;
    }

    public String getAdminEventText() {
        return adminEeventText;
    }

    public void setAdminEventText(String adminEeventText) {
        this.adminEeventText = adminEeventText;
    }

}
