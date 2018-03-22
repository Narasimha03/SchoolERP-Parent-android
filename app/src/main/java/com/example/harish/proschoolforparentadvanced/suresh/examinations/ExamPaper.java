package com.example.harish.proschoolforparentadvanced.suresh.examinations;

/**
 * Created by JANI on 17-06-2017.
 */

public class ExamPaper {
    private String id, exam_paper_id, sub_id, exam_sch_id, exam_paper_title, date, start_time, end_time, max_marks, status;

    public ExamPaper(String id, String exam_paper_id, String sub_id, String exam_sch_id,
                     String exam_paper_title, String date, String start_time, String end_time, String max_marks, String status) {
        this.id = id;
        this.exam_paper_id = exam_paper_id;
        this.sub_id = sub_id;
        this.exam_sch_id = exam_sch_id;
        this.exam_paper_title = exam_paper_title;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.max_marks = max_marks;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExam_paper_id() {
        return exam_paper_id;
    }

    public void setExam_paper_id(String exam_paper_id) {
        this.exam_paper_id = exam_paper_id;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getExam_sch_id() {
        return exam_sch_id;
    }

    public void setExam_sch_id(String exam_sch_id) {
        this.exam_sch_id = exam_sch_id;
    }

    public String getExam_paper_title() {
        return exam_paper_title;
    }

    public void setExam_paper_title(String exam_paper_title) {
        this.exam_paper_title = exam_paper_title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getMax_marks() {
        return max_marks;
    }

    public void setMax_marks(String max_marks) {
        this.max_marks = max_marks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
