INSERT INTO saga (id, kod, STATUS) VALUES (996, "111", 1);

INSERT INTO saga_state (id, command_type, reverse_command, saga_id)
VALUES (995, "PARENT_COMMAND", 0, 996);
INSERT INTO saga_state (id, command_type, event_type, reverse_command, saga_id)
VALUES (996, "SUB1_COMMAND", "PARENT_SUCCESSFUL", 0, 996);
INSERT INTO saga_state (id, command_type, event_type, reverse_command, saga_id)
VALUES (997, "SUB2_COMMAND", "PARENT_SUCCESSFUL", 0, 996);
INSERT INTO saga_state (id, command_type, event_type, reverse_command, saga_id)
VALUES (998, "SUB3_COMMAND", "PARENT_SUCCESSFUL", 0, 996);

INSERT INTO saga_state (id, command_type, event_type, reverse_command, saga_id)
VALUES (1000, "PARENT_ROLLBACK_COMMAND", "PARENT_SUCCESSFUL", 1, 996);
INSERT INTO saga_state (id, command_type, event_type, reverse_command, saga_id)
VALUES (1001, "SUB1_ROLLBACK_COMMAND", "SUB1_SUCCESSFUL", 1, 996);
INSERT INTO saga_state (id, command_type, event_type, reverse_command, saga_id)
VALUES (1002, "SUB2_ROLLBACK_COMMAND", "SUB2_SUCCESSFUL", 1, 996);
INSERT INTO saga_state (id, command_type, event_type, reverse_command, saga_id)
VALUES (1003, "SUB3_ROLLBACK_COMMAND", "SUB3_SUCCESSFUL", 1, 996);

INSERT INTO command_type_class_name (id, class_name, command_type)
VALUES (995, "com.ikite.seruvenn.Commons.carriers.Foo", "PARENT_COMMAND");
INSERT INTO command_type_class_name (id, class_name, command_type)
VALUES (996, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB2_COMMAND");
INSERT INTO command_type_class_name (id, class_name, command_type)
VALUES (997, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB1_COMMAND");
INSERT INTO command_type_class_name (id, class_name, command_type)
VALUES (998, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB3_COMMAND");

INSERT INTO command_type_class_name (id, class_name, command_type)
VALUES (1000, "com.ikite.seruvenn.Commons.carriers.Foo", "PARENT_ROLLBACK_COMMAND");
INSERT INTO command_type_class_name (id, class_name, command_type)
VALUES (1001, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB2_ROLLBACK_COMMAND");
INSERT INTO command_type_class_name (id, class_name, command_type)
VALUES (1002, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB1_ROLLBACK_COMMAND");
INSERT INTO command_type_class_name (id, class_name, command_type)
VALUES (1003, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB3_ROLLBACK_COMMAND");


INSERT INTO event_type_class_name (id, class_name, event_type)
VALUES (995, "com.ikite.seruvenn.Commons.carriers.Foo", "PARENT_SUCCESSFUL");
INSERT INTO event_type_class_name (id, class_name, event_type)
VALUES (996, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB2_SUCCESSFUL");
INSERT INTO event_type_class_name (id, class_name, event_type)
VALUES (997, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB1_SUCCESSFUL");
INSERT INTO event_type_class_name (id, class_name, event_type)
VALUES (998, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB3_SUCCESSFUL");

INSERT INTO event_type_class_name (id, class_name, event_type)
VALUES (1004, "com.ikite.seruvenn.Commons.carriers.Foo", "GENERIC_ROLLBACK");

INSERT INTO event_type_class_name (id, class_name, event_type)
VALUES (1000, "com.ikite.seruvenn.Commons.carriers.Foo", "PARENT_ROLLBACK_OK");
INSERT INTO event_type_class_name (id, class_name, event_type)
VALUES (1001, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB2_ROLLBACK_OK");
INSERT INTO event_type_class_name (id, class_name, event_type)
VALUES (1002, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB1_ROLLBACK_OK");
INSERT INTO event_type_class_name (id, class_name, event_type)
VALUES (1003, "com.ikite.seruvenn.Commons.carriers.Foo", "SUB3_ROLLBACK_OK");