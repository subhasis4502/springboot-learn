package com.subhasis4502.courseapidata.topic;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> getAllTopics() {
        try {
            return ResponseEntity.ok(topicService.getAllTopics());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error getting topics");
        }
    }

    @RequestMapping("/topics/{id}")
    public ResponseEntity<?> getTopic(@PathVariable String id) {
        try {
            return ResponseEntity.ok(topicService.getTopic(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error getting this topic");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics")
    public ResponseEntity<String> addTopic(@RequestBody Topic topic) {
        try {
            topicService.addTopic(topic);
            return ResponseEntity.ok("Topic added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add topic. Error: " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
    public ResponseEntity<String> updateTopic(@RequestBody Topic topic, @PathVariable String id) {
        try {
            topicService.updateTopic(id, topic);
            return ResponseEntity.ok("Topic updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add topic. Error: " + e.getMessage());

        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
    public ResponseEntity<String> deleteTopic(@PathVariable String id) {
        try {
            topicService.deleteTopic(id);
            return ResponseEntity.ok("Topic deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting topic. Error: " + e.getMessage());
        }
    }
}
