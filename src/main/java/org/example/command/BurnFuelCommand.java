package org.example.command;

import org.example.exception.CommonCommandException;
import org.example.model.FuelBurnable;

/**
 * Сжигание топлива
 */
public class BurnFuelCommand implements Command {

    private final FuelBurnable fuelBurnable;

    public BurnFuelCommand(FuelBurnable fuelBurnable) {
        this.fuelBurnable = fuelBurnable;
    }

    @Override
    public void execute() {
        if (fuelBurnable == null) {
            throw new CommonCommandException("Object for fuel burn is null");
        }

        int resultFuelLevel = fuelBurnable.getFuelLevel() - fuelBurnable.getFuelBurnVelocity();

        if (resultFuelLevel < 0) {
            throw new CommonCommandException("Fuel too low");
        }

        fuelBurnable.setFuelLevel(resultFuelLevel);
    }
}
