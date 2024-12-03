package org.example.model;

/**
 * Вращающийся вокруг своей оси объект
 */
public interface Rotatable {

    int getDirection();

    void setDirection(int direction);

    int getAngularVelocity();

    int getDirectionsNum();
}
