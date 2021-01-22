package com.ikite.seruvenn.Commons.message;

public enum CommandType {

	PARENT_COMMAND("PARENT_COMMAND"),
	SUB1_COMMAND("SUB1_COMMAND"),
	SUB2_COMMAND("SUB2_COMMAND"),
	SUB3_COMMAND("SUB3_COMMAND"), 
	PARENT_ROLLBACK_COMMAND("PARENT_ROLLBACK_COMMAND"),
	SUB1_ROLLBACK_COMMAND("SUB1_ROLLBACK_COMMAND"),
	SUB2_ROLLBACK_COMMAND("SUB2_ROLLBACK_COMMAND"),
	SUB3_ROLLBACK_COMMAND("SUB3_ROLLBACK_COMMAND");

	private final String fieldDescription;

    private CommandType(String value) {
        fieldDescription = value;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

	
}
