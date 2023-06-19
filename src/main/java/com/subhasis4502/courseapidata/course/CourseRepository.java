package com.subhasis4502.courseapidata.course;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {

    public List<Course> findByName(String name);  // We dont need to write the method the JPA will do that for us
    
    public List<Course> findByTopicId(String topicId);

}
