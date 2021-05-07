package com.ikite.seruvenn.CETypeClassMap.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ikite.seruvenn.CETypeClassMap.model.CommandTypeClassName;

public interface CommandTypeClassNameRepository extends CrudRepository<CommandTypeClassName, Integer>{

	@Query(value="select * from COMMAND_TYPE_CLASS_NAME where COMMAND_TYPE=?1", nativeQuery = true)
	List<CommandTypeClassName> findByCommandType(String commandType);
	
}
