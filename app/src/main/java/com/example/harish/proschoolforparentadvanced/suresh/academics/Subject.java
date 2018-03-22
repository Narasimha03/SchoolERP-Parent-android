package com.example.harish.proschoolforparentadvanced.suresh.academics;

/**
 * Created by JANI on 07-06-2017.
 */

public class Subject {
    private String name, code, type;

    public Subject(String name,String code) {
        this.name = name;
        this.code = code;
        //this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
