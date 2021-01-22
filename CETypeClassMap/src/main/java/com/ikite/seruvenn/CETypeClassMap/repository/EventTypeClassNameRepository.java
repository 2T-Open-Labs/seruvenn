package com.ikite.seruvenn.CETypeClassMap.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ikite.seruvenn.CETypeClassMap.model.EventTypeClassName;

public interface EventTypeClassNameRepository extends CrudRepository<EventTypeClassName, Integer>{

	@Query(value="select * from event_type_class_name where event_type=?1", nativeQuery = true)
	List<EventTypeClassName> findByEventType(String eventType);
	
}
