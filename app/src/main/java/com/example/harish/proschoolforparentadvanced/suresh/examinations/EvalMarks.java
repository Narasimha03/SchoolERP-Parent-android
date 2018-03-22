package com.example.harish.proschoolforparentadvanced.suresh.examinations;

/**
 * Created by JANI on 17-06-2017.
 */

public class EvalMarks {
    private String id, paper_result_id, exam_paper_id, student_id, marks, percentage, conduct, comment, date, status, exam, subject;

    public EvalMarks(String id, String paper_result_id, String exam_paper_id, String student_id, String marks, String percentage,
                     String conduct, String comment, String date, String status, String exam, String subject) {
        this.id = id;
        this.paper_result_id = paper_result_id;
        this.exam_paper_id = exam_paper_id;
        this.student_id = student_id;
        this.marks = marks;
        this.percentage = percentage;
        this.conduct = conduct;
        this.comment = comment;
        this.date = date;
        this.status = status;
        this.exam = exam;
        this.subject = subject;
    }

    public String getExam(){
        return exam;
    }

    public void setExam(String exam){
        this.exam = exam;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaper_result_id() {
        return paper_result_id;
    }

    public void setPaper_result_id(String paper_result_id) {
        this.paper_result_id = paper_result_id;
    }

    public String getExam_paper_id() {
        return exam_paper_id;
    }

    public void setExam_paper_id(String exam_paper_id) {
        this.exam_paper_id = exam_paper_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getConduct() {
        return conduct;
    }

    public void setConduct(String conduct) {
        this.conduct = conduct;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
