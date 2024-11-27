package org.example.exception.handlers;

import org.example.command.Command;
import org.example.command.CommandQueue;
import org.example.command.RepeatCommand;
import org.example.command.TwiceRepeatCommand;

/**
 * Обработчик исключения, повторяющий команду, в рамках выполнения которой было выброошено исключение.
 * Для повторной попытки выполнения отдельный тип команды.
 */
public class RepeatExceptionHandler implements ExceptionHandler {

    private final CommandQueue commandQueue;

    public RepeatExceptionHandler(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Override
    public void handle(Exception exception, Command command) {
        if (command instanceof RepeatCommand) {
            commandQueue.addLast(new TwiceRepeatCommand(command));
        } else {
            commandQueue.addLast(new RepeatCommand(command));
        }
    }
}
