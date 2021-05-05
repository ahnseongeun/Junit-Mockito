package com.test_java.lecture10_확장모델;

import com.test_java.lecture2.StudyStatus;

public class Study {

    private StudyStatus studyStatus = StudyStatus.DRAFT;

    private int limit;

    private String name;

    public Study(int limit, String name){
        this.limit = limit;
        this.name = name;
    };

    public Study(int limit){
        //if( limit < 0)
        //    throw new IllegalArgumentException("limit은 0보다 커야 합니다.");
        this.limit = limit;
    };

    public StudyStatus getStatus() {
        return this.studyStatus;
    }

    public int getLimit() {
        return this.limit;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return "Study{" +
                "studyStatus=" + studyStatus +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                '}';
    }
}
