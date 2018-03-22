package com.example.harish.proschoolforparentadvanced.suresh.assignments;

/**
 * Created by JANI on 03-06-2017.
 */

public class Assignments {
    private String id, assign_id, name, section_id, lesson_id, due_date, description;

    public Assignments(String id, String assign_id, String name, String section_id, String lesson_id, String due_date, String description) {
        this.id = id;
        this.assign_id = assign_id;
        this.name = name;
        this.section_id = section_id;
        this.lesson_id = lesson_id;
        this.due_date = due_date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssign_id() {
        return assign_id;
    }

    public void setAssign_id(String assign_id) {
        this.assign_id = assign_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
