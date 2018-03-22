package com.example.harish.proschoolforparentadvanced.suresh.examinations;

/**
 * Created by JANI on 16-06-2017.
 */

public class ExamSchedule {
    private String id, exam_id, school_id, exam_title, exam_class, from_date, status;

    public ExamSchedule(String id, String exam_id, String school_id, String exam_title, String exam_class, String from_date, String status) {
        this.id = id;
        this.exam_id = exam_id;
        this.school_id = school_id;
        this.exam_title = exam_title;
        this.exam_class = exam_class;
        this.from_date = from_date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getExam_title() {
        return exam_title;
    }

    public void setExam_title(String exam_title) {
        this.exam_title = exam_title;
    }

    public String getExam_class() {
        return exam_class;
    }

    public void setExam_class(String exam_class) {
        this.exam_class = exam_class;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
