package org.example.exception.handlers;

import org.example.command.Command;
import org.example.command.CommandQueue;
import org.example.exception.UnknownExceptionHandlerException;

import java.util.HashMap;
import java.util.Map;

public class CommandExceptionHandler implements ExceptionHandler {

    private final ExceptionHandlerStrategy exceptionHandlerStrategy;
    private final Map<String, ExceptionHandler> exceptionHandlers;

    public CommandExceptionHandler(CommandQueue commandQueue, ExceptionHandlerStrategy exceptionHandlerStrategy) {
        this.exceptionHandlerStrategy = exceptionHandlerStrategy;
        this.exceptionHandlers = new HashMap<>();
        this.exceptionHandlers.put(LoggingExceptionHandler.class.getSimpleName(), new LoggingExceptionHandler(commandQueue));
        this.exceptionHandlers.put(RepeatExceptionHandler.class.getSimpleName(), new RepeatExceptionHandler(commandQueue));
    }

    @Override
    public void handle(Exception exception, Command command) {
        ExceptionHandler exceptionHandler = exceptionHandlers.get(exceptionHandlerStrategy.getExceptionHandler(exception, command));
        if (exceptionHandler == null) {
            throw new UnknownExceptionHandlerException("There is no exception handler for " + exception.getClass().getSimpleName());
        }
        exceptionHandler.handle(exception, command);
    }
}
