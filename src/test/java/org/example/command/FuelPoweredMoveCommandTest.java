package org.example.command;

import org.example.exception.CommonCommandException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class FuelPoweredMoveCommandTest {

    private final CheckFuelCommand checkFuelCommand = mock(CheckFuelCommand.class);
    private final MoveCommand moveCommand = mock(MoveCommand.class);
    private final BurnFuelCommand burnFuelCommand = mock(BurnFuelCommand.class);
    private final FuelPoweredMoveCommand fuelPoweredMoveCommand = new FuelPoweredMoveCommand(checkFuelCommand, moveCommand, burnFuelCommand);

    @Test
    public void given_move_command_when_all_simple_command_executed_then_no_exception_is_thrown() {
        fuelPoweredMoveCommand.execute();

        verify(checkFuelCommand, times(1)).execute();
        verify(moveCommand, times(1)).execute();
        verify(burnFuelCommand, times(1)).execute();
    }

    @Test
    public void given_move_command_when_some_simple_command_execution_failed_then_exception_thrown() {
        doThrow(new RuntimeException()).when(moveCommand).execute();
        assertThatThrownBy(fuelPoweredMoveCommand::execute).isInstanceOf(CommonCommandException.class);

        verify(checkFuelCommand, times(1)).execute();
        verify(moveCommand, times(1)).execute();
        verify(burnFuelCommand, times(0)).execute();
    }
}