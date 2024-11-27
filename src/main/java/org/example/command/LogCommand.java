package org.example.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Команда логгирования исключений
 */
public class LogCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogCommand.class);

    private final Command command;
    private final Exception exception;

    public LogCommand(Command command, Exception exception) {
        this.command = command;
        this.exception = exception;
    }

    @Override
    public void execute() {
        LOGGER.error("There is exception was thrown while executing command={}, exception={}",
                command.getClass().getSimpleName(), exception.getMessage());
    }
}
