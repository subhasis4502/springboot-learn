package com.subhasis4502.courseapidata.topic;

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


@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topics")
    public List<Topic> getAllTopics() {
        try {
            return topicService.getAllTopics();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.singletonList(new Topic("500", "Error", "Error getting courses"));
        }
    }

    @RequestMapping("/topics/{id}")
    public Topic getTopic(@PathVariable String id) {
        try {
            return topicService.getTopic(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Topic("500", "Error", "Error getting this course");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics")
    public ResponseEntity<String> addTopic(@RequestBody Topic topic) {
        try {
            topicService.addTopic(topic);
            return ResponseEntity.ok("Lesson added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add course. Error: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
    public ResponseEntity<String> updateTopic(@RequestBody Topic topic, @PathVariable String id) {
        try {
            topicService.updateTopic(id, topic);
            return ResponseEntity.ok("Lesson added successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add course. Error: " + e.getMessage());

        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
    public ResponseEntity<String> deleteTopic(@PathVariable String id) {
        try {
            topicService.deleteTopic(id);
            return ResponseEntity.ok("Course deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting course. Error: " + e.getMessage());
        }
    }
}
