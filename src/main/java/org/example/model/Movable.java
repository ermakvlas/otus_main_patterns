package org.example.model;

/**
 * Прямолинейно равномерно движущийся на плоскости объект
 */
public interface Movable {

    Coordinates getPosition();

    Coordinates getVelocity();

    void setPosition(Coordinates coordinates);
}
