package com.subhasis4502.courseapidata.lesson;

import com.subhasis4502.courseapidata.course.Course;

public class Lesson {
    private String id;
    private String name;
    private String description;
    private Course course;

    public Lesson() {
    }

    public Lesson(String id, String name, String description, String courseId, String topicId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.course = new Course(courseId, "", "", topicId);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
