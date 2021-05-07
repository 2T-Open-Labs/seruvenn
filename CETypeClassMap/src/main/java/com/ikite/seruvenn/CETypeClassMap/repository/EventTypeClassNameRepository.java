package com.ikite.seruvenn.CETypeClassMap.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ikite.seruvenn.CETypeClassMap.model.EventTypeClassName;

public interface EventTypeClassNameRepository extends CrudRepository<EventTypeClassName, Integer>{

	@Query(value="select * from EVENT_TYPE_CLASS_NAME where EVENT_TYPE=?1", nativeQuery = true)
	List<EventTypeClassName> findByEventType(String eventType);
	
}
