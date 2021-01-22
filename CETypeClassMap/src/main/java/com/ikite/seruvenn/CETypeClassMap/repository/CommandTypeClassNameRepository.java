package com.ikite.seruvenn.CETypeClassMap.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ikite.seruvenn.CETypeClassMap.model.CommandTypeClassName;

public interface CommandTypeClassNameRepository extends CrudRepository<CommandTypeClassName, Integer>{

	@Query(value="select * from command_type_class_name where command_type=?1", nativeQuery = true)
	List<CommandTypeClassName> findByCommandType(String commandType);
	
}
