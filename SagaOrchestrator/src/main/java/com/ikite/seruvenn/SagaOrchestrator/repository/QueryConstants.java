package com.ikite.seruvenn.SagaOrchestrator.repository;

public class QueryConstants {

	public static final String SAGA_SELECT_BY_KOD = "select * from SAGA where KOD=?1 and STATUS=1 order by ID desc";
	
}
