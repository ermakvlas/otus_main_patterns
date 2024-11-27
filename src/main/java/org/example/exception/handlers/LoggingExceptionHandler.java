package org.example.exception.handlers;

import org.example.command.Command;
import org.example.command.CommandQueue;
import org.example.command.LogCommand;

/**
 * Обработчик исключений, создающий команду логгирования и добавляющий ее в очередь
 */
public class LoggingExceptionHandler implements ExceptionHandler {

    private final CommandQueue commandQueue;

    public LoggingExceptionHandler(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Override
    public void handle(Exception exception, Command command) {
        commandQueue.addLast(new LogCommand(command, exception));
    }
}
