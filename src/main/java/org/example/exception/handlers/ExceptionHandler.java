package org.example.exception.handlers;

import org.example.command.Command;

public interface ExceptionHandler {

    void handle(Exception exception, Command command);
}
