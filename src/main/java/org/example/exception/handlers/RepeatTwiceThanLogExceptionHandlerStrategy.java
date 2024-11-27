package org.example.exception.handlers;

import org.example.command.Command;
import org.example.command.TwiceRepeatCommand;

/**
 * 2 ретрая + логгирование ошибки
 */
public class RepeatTwiceThanLogExceptionHandlerStrategy implements ExceptionHandlerStrategy {

    @Override
    public String getExceptionHandler(Exception exception, Command command) {
        if (command instanceof TwiceRepeatCommand) {
            return LoggingExceptionHandler.class.getSimpleName();
        } else {
            return RepeatExceptionHandler.class.getSimpleName();
        }
    }
}
