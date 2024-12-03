package org.example.command;

import org.example.exception.CommonCommandException;
import org.example.model.FuelCheckable;

/**
 * Проверка уровня топлива
 */
public class CheckFuelCommand implements Command {

    private final FuelCheckable fuelCheckable;

    public CheckFuelCommand(FuelCheckable fuelCheckable) {
        this.fuelCheckable = fuelCheckable;
    }

    @Override
    public void execute() {
        if (fuelCheckable == null) {
            throw new CommonCommandException("Object for fuel check is null");
        }
        if (fuelCheckable.getFuelLevel() < fuelCheckable.getFuelBurnVelocity()) {
            throw new CommonCommandException("Fuel too low");
        }
    }
}
