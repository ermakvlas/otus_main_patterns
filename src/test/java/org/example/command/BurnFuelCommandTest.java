package org.example.command;

import org.example.exception.CommonCommandException;
import org.example.model.FuelBurnable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BurnFuelCommandTest {

    private final FuelBurnable fuelBurnable = mock(FuelBurnable.class);;
    private final BurnFuelCommand burnFuelCommand = new BurnFuelCommand(fuelBurnable);

    @Test
    public void given_fuel_burnable_with_enough_level_then_move() {
        when(fuelBurnable.getFuelLevel()).thenReturn(1);
        when(fuelBurnable.getFuelBurnVelocity()).thenReturn(1);
        burnFuelCommand.execute();

        verify(fuelBurnable, times(1)).setFuelLevel(eq(0));
    }

    @Test
    public void given_fuel_burnable_with_not_enough_level_then_not_move() {
        when(fuelBurnable.getFuelLevel()).thenReturn(4);
        when(fuelBurnable.getFuelBurnVelocity()).thenReturn(5);
        assertThatThrownBy(burnFuelCommand::execute).isInstanceOf(CommonCommandException.class);
    }
}