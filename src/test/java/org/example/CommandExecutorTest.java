package org.example;

import org.example.command.*;
import org.example.exception.handlers.RepeatThanLogExceptionHandlerStrategy;
import org.example.exception.handlers.RepeatTwiceThanLogExceptionHandlerStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CommandExecutorTest {

    @Test
    public void given_command_throw_exception_when_one_repeat_strategy_then_correct_handling() {
        CommandQueue commandQueue = spy(new CommandQueueImpl());
        CommandExecutor commandExecutor = new CommandExecutor(commandQueue, new RepeatThanLogExceptionHandlerStrategy());

        Command exceptionCommand = () -> {
            throw new RuntimeException();
        };
        commandQueue.addLast(exceptionCommand);

        commandExecutor.process();

        ArgumentCaptor<Command> commandCaptor = ArgumentCaptor.forClass(Command.class);
        verify(commandQueue, times(3)).addLast(commandCaptor.capture());
        List<Command> commandsAdded = commandCaptor.getAllValues();
        assertThat(commandsAdded.get(0)).isEqualTo(exceptionCommand);
        assertThat(commandsAdded.get(1).getClass()).isEqualTo(RepeatCommand.class);
        assertThat(commandsAdded.get(2).getClass()).isEqualTo(LogCommand.class);
    }

    @Test
    public void given_command_throw_exception_when_two_repeat_strategy_then_correct_handling() {
        CommandQueue commandQueue = spy(new CommandQueueImpl());
        CommandExecutor commandExecutor = new CommandExecutor(commandQueue, new RepeatTwiceThanLogExceptionHandlerStrategy());

        Command exceptionCommand = () -> {
            throw new RuntimeException();
        };
        commandQueue.addLast(exceptionCommand);

        commandExecutor.process();

        ArgumentCaptor<Command> commandCaptor = ArgumentCaptor.forClass(Command.class);
        verify(commandQueue, times(4)).addLast(commandCaptor.capture());
        List<Command> commandsAdded = commandCaptor.getAllValues();
        assertThat(commandsAdded.get(0)).isEqualTo(exceptionCommand);
        assertThat(commandsAdded.get(1).getClass()).isEqualTo(RepeatCommand.class);
        assertThat(commandsAdded.get(2).getClass()).isEqualTo(TwiceRepeatCommand.class);
        assertThat(commandsAdded.get(3).getClass()).isEqualTo(LogCommand.class);
    }
}