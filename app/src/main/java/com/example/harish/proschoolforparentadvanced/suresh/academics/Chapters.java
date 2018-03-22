package com.example.harish.proschoolforparentadvanced.suresh.academics;

/**
 * Created by JANI on 16-06-2017.
 */

public class Chapters {
    String id, lesson_id, subject_id, title, code, noTopics, desc, status;

    public Chapters(String id, String lesson_id, String subject_id, String title, String code, String noTopics, String desc, String status) {
        this.id = id;
        this.lesson_id = lesson_id;
        this.subject_id = subject_id;
        this.title = title;
        this.code = code;
        this.noTopics = noTopics;
        this.desc = desc;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNoTopics() {
        return noTopics;
    }

    public void setNoTopics(String noTopics) {
        this.noTopics = noTopics;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
