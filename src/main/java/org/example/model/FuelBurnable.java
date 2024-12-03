package org.example.model;

/**
 * Расходующий топливо объект
 */
public interface FuelBurnable {

    int getFuelLevel();

    void setFuelLevel(int level);

    int getFuelBurnVelocity();
}
