package org.example.exception.handlers;

import org.example.command.Command;
import org.example.command.RepeatCommand;

/**
 * 1 ретрай + логгировние ошибки
 */
public class RepeatThanLogExceptionHandlerStrategy implements ExceptionHandlerStrategy {

    @Override
    public String getExceptionHandler(Exception exception, Command command) {
        if (command instanceof RepeatCommand) {
            return LoggingExceptionHandler.class.getSimpleName();
        } else {
            return RepeatExceptionHandler.class.getSimpleName();
        }
    }
}
