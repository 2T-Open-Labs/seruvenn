package com.ikite.seruvenn.Commons.message;

public enum MessageArchetype {

	COMMAND("COMMAND"),
	EVENT("EVENT");

	private final String fieldDescription;

    private MessageArchetype(String value) {
        fieldDescription = value;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

	
}
