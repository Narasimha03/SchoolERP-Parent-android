package com.example.harish.proschoolforparentadvanced.suresh.studentprofile;

/**
 * Created by harish on 2/22/2018.
 */

public class StudentGraphModel {
    public StudentGraphModel(String studentId, String employeeId, String studentName, String totalCount, String totalPresent, String totalAbsent, String totalLeave, String sPresent, String sAbsent, String sOnleave, String sPresentPercent, String sAbsentPercent, String sOnLeavePercent, String imageDisplay) {
        this.studentId = studentId;
        this.employeeId = employeeId;
        this.studentName = studentName;
        this.totalCount = totalCount;
        this.totalPresent = totalPresent;
        this.totalAbsent = totalAbsent;
        this.totalLeave = totalLeave;
        this.sPresent = sPresent;
        this.sAbsent = sAbsent;
        this.sOnleave = sOnleave;
        this.sPresentPercent = sPresentPercent;
        this.sAbsentPercent = sAbsentPercent;
        this.sOnLeavePercent = sOnLeavePercent;
        this.imageDisplay=imageDisplay;
    }

    public String getImageDisplay() {
        return imageDisplay;
    }

    public void setImageDisplay(String imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    public String getStudentId() {
        return studentId;

    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getTotalPresent() {
        return totalPresent;
    }

    public void setTotalPresent(String totalPresent) {
        this.totalPresent = totalPresent;
    }

    public String getTotalAbsent() {
        return totalAbsent;
    }

    public void setTotalAbsent(String totalAbsent) {
        this.totalAbsent = totalAbsent;
    }

    public String getTotalLeave() {
        return totalLeave;
    }

    public void setTotalLeave(String totalLeave) {
        this.totalLeave = totalLeave;
    }

    public String getsPresent() {
        return sPresent;
    }

    public void setsPresent(String sPresent) {
        this.sPresent = sPresent;
    }

    public String getsAbsent() {
        return sAbsent;
    }

    public void setsAbsent(String sAbsent) {
        this.sAbsent = sAbsent;
    }

    public String getsOnleave() {
        return sOnleave;
    }

    public void setsOnleave(String sOnleave) {
        this.sOnleave = sOnleave;
    }

    public String getsPresentPercent() {
        return sPresentPercent;
    }

    public void setsPresentPercent(String sPresentPercent) {
        this.sPresentPercent = sPresentPercent;
    }

    public String getsAbsentPercent() {
        return sAbsentPercent;
    }

    public void setsAbsentPercent(String sAbsentPercent) {
        this.sAbsentPercent = sAbsentPercent;
    }

    public String getsOnLeavePercent() {
        return sOnLeavePercent;
    }

    public void setsOnLeavePercent(String sOnLeavePercent) {
        this.sOnLeavePercent = sOnLeavePercent;
    }

    String studentId,studentName,totalCount,totalPresent,totalAbsent,totalLeave,sPresent,sAbsent,sOnleave,sPresentPercent,sAbsentPercent,sOnLeavePercent, imageDisplay, employeeId;
}
