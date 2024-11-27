package org.example.exception.handlers;

import org.example.command.Command;

/**
 * Стратегия выбора обработчика исключения
 */
public interface ExceptionHandlerStrategy {

    String getExceptionHandler(Exception exception, Command command);
}
