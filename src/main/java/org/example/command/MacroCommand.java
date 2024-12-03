package org.example.command;

import org.example.exception.CommonCommandException;

import java.util.List;

/**
 * Макрокоманда (список команд)
 */
public class MacroCommand implements Command {

    protected final List<Command> commands;

    public MacroCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        try {
            commands.forEach(Command::execute);
        } catch (Exception e) {
            throw new CommonCommandException(e);
        }
    }
}
