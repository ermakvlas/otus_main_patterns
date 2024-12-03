package org.example.command;

import java.util.List;

/**
 * Движение, обусловленное сжиганием топлива
 */
public class FuelPoweredMoveCommand extends MacroCommand {

    public FuelPoweredMoveCommand(CheckFuelCommand checkFuelCommand,
                                  MoveCommand moveCommand,
                                  BurnFuelCommand burnFuelCommand) {
        super(List.of(checkFuelCommand, moveCommand, burnFuelCommand));
    }

}