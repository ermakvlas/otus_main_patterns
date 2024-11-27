package org.example;

import org.example.command.Command;
import org.example.command.CommandQueue;
import org.example.exception.handlers.CommandExceptionHandler;
import org.example.exception.handlers.ExceptionHandlerStrategy;

/**
 * Выполнитель команд
 */
public class CommandExecutor {

    private final CommandQueue commandQueue;
    private final CommandExceptionHandler exceptionHandler;

    public CommandExecutor(CommandQueue commandQueue, ExceptionHandlerStrategy exceptionHandlerStrategy) {
        this.commandQueue = commandQueue;
        this.exceptionHandler = new CommandExceptionHandler(commandQueue, exceptionHandlerStrategy);
    }

    public void process() {
        Command command;
        while ((command = commandQueue.getFirst()) != null) {
            try {
                command.execute();
            } catch (Exception exception) {
                exceptionHandler.handle(exception, command);
            }
        }
    }
}
