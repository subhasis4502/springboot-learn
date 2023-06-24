package com.subhasis4502.courseapidata.course;

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
    public ResponseEntity<?> getAllCourses(@PathVariable String topicId) {
        try {
            return ResponseEntity.ok(courseService.getAllCourses(topicId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error getting courses");
        }
    }

    @RequestMapping("/courses/{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable String courseId) {
        try {
            return ResponseEntity.ok(courseService.getCourse(courseId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error getting this course");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/courses/{topicId}")
    public ResponseEntity<String> addCourse(@PathVariable String topicId, @RequestBody Course course) {
        try {
            course.setTopic(new Topic(topicId, "", ""));
            courseService.addCourse(course);
            return ResponseEntity.ok("Course added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add course. Error: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/courses/{topicId}")
    public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable String topicId) {
        try{
            course.setTopic(new Topic(topicId, "", ""));
            courseService.updateCourse(course);
            return ResponseEntity.ok("Course updated successfully");
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error updating course");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/courses/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable String courseId) {
        try {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok("Course deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting course. Error: " + e.getMessage());
        }
    }
}
