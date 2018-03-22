package com.example.harish.proschoolforparentadvanced.suresh.noticeboard;

/**
 * Created by harish on 07-11-2017.
 */

public class AdminDashboardNoticeBoardClass {

    private String adminNoticeDate;
    private String adminNoticeTime;
    private String adminNoticeSub;
    private String adminNoticeText;

    public AdminDashboardNoticeBoardClass(String adminNoticeSub) {
        this.adminNoticeSub = adminNoticeSub;
    }

    public AdminDashboardNoticeBoardClass() {
    }

    public AdminDashboardNoticeBoardClass(String adminNoticeDate, String adminNoticeTime, String adminNoticeSub, String adminNoticeText) {
        this.adminNoticeDate = adminNoticeDate;
        this.adminNoticeTime = adminNoticeTime;
        this.adminNoticeSub = adminNoticeSub;
        this.adminNoticeText = adminNoticeText;
    }

    public String getAdminNoticeDate() {
        return adminNoticeDate;
    }

    public void setAdminNoticeDate(String adminNoticeDate) {
        this.adminNoticeDate = adminNoticeDate;
    }

    public String getAdminNoticeSub() {
        return adminNoticeSub;
    }

    public void setadminNoticeSub(String adminNoticeSub) {
        this.adminNoticeSub = adminNoticeSub;
    }

    public String getAdminNoticeText() {
        return adminNoticeText;
    }

    public void setAdminNoticeText(String adminNoticeText) {
        this.adminNoticeText = adminNoticeText;
    }

    public String getAdminNoticeTime() {
        return adminNoticeTime;
    }

    public void setAdminNoticeTime(String adminNoticeTime) {
        this.adminNoticeTime = adminNoticeTime;
    }

}
