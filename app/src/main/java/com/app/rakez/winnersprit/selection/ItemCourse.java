package com.app.rakez.winnersprit.selection;

/**
 * Created by RAKEZ on 11/27/2017.
 */

public class ItemCourse {
    private String courseId;
    private String courseName;

    public ItemCourse(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
