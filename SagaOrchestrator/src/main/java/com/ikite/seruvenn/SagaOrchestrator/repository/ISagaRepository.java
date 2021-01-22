package com.ikite.seruvenn.SagaOrchestrator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ikite.seruvenn.SagaOrchestrator.model.Saga;

public interface ISagaRepository extends CrudRepository<Saga, Integer>{

	@Query(value=QueryConstants.SAGA_SELECT_BY_KOD, nativeQuery = true)
	List<Saga> findByKod(String kod);
	
}
