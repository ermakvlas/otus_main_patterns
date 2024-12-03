package org.example.command;

import org.example.exception.CommonCommandException;
import org.example.model.FuelCheckable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class CheckFuelCommandTest {

    private final FuelCheckable fuelCheckable = mock(FuelCheckable.class);
    private final CheckFuelCommand checkFuelCommand = new CheckFuelCommand(fuelCheckable);

    @Test
    public void given_enough_fuel_when_checking_level_then_no_exception() {
        when(fuelCheckable.getFuelLevel()).thenReturn(2);
        when(fuelCheckable.getFuelBurnVelocity()).thenReturn(2);
        checkFuelCommand.execute();

        verify(fuelCheckable, times(1)).getFuelLevel();
        verify(fuelCheckable, times(1)).getFuelBurnVelocity();
    }

    @Test
    public void given_not_enough_fuel_when_checking_level_then_exception_thrown() {
        when(fuelCheckable.getFuelLevel()).thenReturn(2);
        when(fuelCheckable.getFuelBurnVelocity()).thenReturn(3);

        assertThatThrownBy(checkFuelCommand::execute).isInstanceOf(CommonCommandException.class);
    }
}