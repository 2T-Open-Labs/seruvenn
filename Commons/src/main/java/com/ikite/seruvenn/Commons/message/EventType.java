package com.ikite.seruvenn.Commons.message;

public enum EventType {
	
		PARENT_SUCCESSFUL("PARENT_SUCCESSFUL"),
		SUB1_SUCCESSFUL("SUB1_SUCCESSFUL"),
		SUB2_SUCCESSFUL("SUB2_SUCCESSFUL"),
		SUB3_SUCCESSFUL("SUB3_SUCCESSFUL"),
		PARENT_ROLLBACK_OK("PARENT_ROLLBACK_OK"),
		SUB1_ROLLBACK_OK("SUB1_ROLLBACK_OK"),
		SUB2_ROLLBACK_OK("SUB2_ROLLBACK_OK"),
		SUB3_ROLLBACK_OK("SUB3_ROLLBACK_OK"),
		GENERIC_ROLLBACK("GENERIC_ROLLBACK");
	
		private final String fieldDescription;
	
	    private EventType(String value) {
	        fieldDescription = value;
	    }
	
	    public String getFieldDescription() {
	        return fieldDescription;
	    }

}
