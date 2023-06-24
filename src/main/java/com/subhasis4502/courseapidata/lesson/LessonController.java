package com.subhasis4502.courseapidata.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.subhasis4502.courseapidata.course.Course;

@RestController
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @RequestMapping("/lessons/course/{courseId}")
    public ResponseEntity<?> getAllLessons(@PathVariable String courseId) {
        try {
            return ResponseEntity.ok(lessonService.getAllLessons(courseId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error getting lessons");
        }
    }

    @RequestMapping("/lessons/{lessonId}")
    public Lesson getLesson(@PathVariable String lessonId) {
        try {
            return lessonService.getLesson(lessonId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Lesson("500", "Error", "Error getting this lesson", "", "");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/lessons/{courseId}/{topicId}")
    public ResponseEntity<String> createLesson(@RequestBody Lesson lesson,
            @PathVariable String courseId,
            @PathVariable String topicId) {
        try {
            lesson.setCourse(new Course(courseId, "", "", topicId));
            lessonService.addLesson(lesson);
            return ResponseEntity.ok("Lesson added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add lesson. Error: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/lessons/{topicId}/{courseId}")
    public ResponseEntity<String> updateLesson(@RequestBody Lesson lesson, @PathVariable String topicId, @PathVariable String courseId) {
        try {
            lesson.setCourse(new Course(courseId, "", "", topicId));
            return ResponseEntity.ok("Topic updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to add lesson. Error: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/lessons/{lessonId}")
    public ResponseEntity<String> deleteLesson(@PathVariable String lessonId) {
        try {
            lessonService.deleteLesson(lessonId);
            return ResponseEntity.ok("Lesson deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting lesson. Error: " + e.getMessage());
        }
    }
}
