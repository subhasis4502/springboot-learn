package com.subhasis4502.courseapidata.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses(String topicId) {
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(topicId)
            .forEach(courses::add);
        return courses;
    }

    public Course getCourse(String id) {
        return courseRepository.findById(id).get();
    }

    public void addCourse(Course Course) {
        courseRepository.save(Course);
    }

    public Course updateCourse(Course Course) {
        return courseRepository.save(Course);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }
}
