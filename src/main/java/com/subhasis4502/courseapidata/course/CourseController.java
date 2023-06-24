package com.subhasis4502.courseapidata.course;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.subhasis4502.courseapidata.topic.Topic;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/courses/topics/{topicId}")
    public List<Course> getAllCourses(@PathVariable String topicId) {
        try {
            return courseService.getAllCourses(topicId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.singletonList(new Course("500", "Error", "Error getting courses", ""));
        }
    }

    @RequestMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable String courseId) {
        try {
            return courseService.getCourse(courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Course("500", "Error", "Error getting this course", "");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/courses/{topicId}")
    public ResponseEntity<String> addCourse(@PathVariable String topicId, @RequestBody Course course) {
        try {
            course.setTopic(new Topic(topicId, "", ""));
            courseService.addCourse(course);
            return ResponseEntity.ok("Lesson added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add course. Error: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/courses/{topicId}")
    public Course updateCourse(@RequestBody Course course, @PathVariable String topicId) {
        try{
            course.setTopic(new Topic(topicId, "", ""));
            return courseService.updateCourse(course);
        } catch(Exception e) {
            e.printStackTrace();
            return new Course("500", "Error", "Error updating course", topicId);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/courses/{courseId}")
    public String deleteCourse(@PathVariable String courseId) {
        try {
            courseService.deleteCourse(courseId);
            return "Course deleted successfully";
        } catch (Exception e) {
            return "Error deleting course. Error: " + e.getMessage();
        }
    }
}
