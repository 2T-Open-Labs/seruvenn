package com.ikite.seruvenn.SagaOrchestrator.repository;

public class QueryConstants {

	public static final String SAGA_SELECT_BY_KOD = "select * from saga where kod=?1 and status=1 order by id desc";
	
}
